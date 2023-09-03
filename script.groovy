#!/usr/bin/env groovy

def testBuild(){

    echo "incrementing..."
    def file = readFile("${env.WORKSPACE}/version.xml")
    def matcher = file.split(",")
    major = matcher[0]
    minor = matcher[1]
    patch = matcher[2]
    tagName = "${major}.${minor}.${patch}"
    sh "bash ./test.sh ${tagName}"

    sshing="ssh -o StrictHostKeyChecking=no root@165.232.147.254"
    dockerRegistry="golebu2023/image-registry"

    this.caller()
}

def caller(){
    echo "Oyee!"
}


def buildPush(){

    withCredentials([usernamePassword(credentialsId:'dockerhub-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')]){
        sh "echo ${PASS} | docker login --username ${USER} --password-stdin"

        sh "docker tag bc_wildfire_web:${tagName} ${dockerRegistry}:bc_wildfire_web-${tagName}"
        sh "docker tag bc_wildfire_ui:${tagName} ${dockerRegistry}:bc_wildfire_ui-${tagName}"
        sh "docker push ${dockerRegistry}:bc_wildfire_web-${tagName}"
        sh "docker push ${dockerRegistry}:bc_wildfire_ui-${tagName}"
        this.deleteImages()

    }
}

def deleteImages(){
    def process = "docker images -aq".execute()
    process.waitFor()

    if (process.exitValue() == 0) {
        def imageIds = process.text.trim()
        if (imageIds) {
            def removeProcess = "docker rmi -f $imageIds".execute()
            removeProcess.waitFor()

            if (removeProcess.exitValue() == 0) {
                println("Successfully removed Docker images.")
            } else {
                println("Failed to remove Docker images.")
                println(removeProcess.err.text)
            }
        } else {
            println("No Docker images found to remove.")
        }
    } else {
        println("Failed to list Docker images.")
        println(process.err.text)
    }
}


def incrementVersion(){
    def patchTemp = patch as Integer
    patchTemp++
    writeFile(file: "${env.WORKSPACE}/version.xml", text: "${major},${minor},${patchTemp}", encoding: "UTF-8")
}


def deploy(){
    echo "Deploying app......"
    def deployTag = "${major}.${minor}.${patch}"
    def runSSH = "bash ./docker-compose-prod-tag.sh ${deployTag}"


    sshagent(['deploy-key']) {
        // sh "scp docker-compose-prod-tag.sh docker-compose-prod.yaml .env.prod root@137.184.172.232:/root"
        sh 'scp -o StrictHostKeyChecking=no docker-compose-prod-tag.sh docker-compose-prod.yaml .env.prod root@165.232.147.254:/root'
        sh "${deleteContainers()}"
        sh "${sshing} ${runSSH}"

    }
}

def deleteContainers(){

    def process = 'docker ps -aq'.execute()
    process.waitFor()

    if (process.exitValue() == 0) {
        def containerIds = process.text.trim()
        if (containerIds) {
            def removeProcess = "docker rm -vf $containerIds".execute()
            removeProcess.waitFor()

            if (removeProcess.exitValue() == 0) {
                println("Successfully removed Docker containers.")
            } else {
                println("Failed to remove Docker containers.")
                println(removeProcess.err.text)
            }
        } else {
            println("No Docker containers found to remove.")
        }
    } else {
        println("Failed to list Docker containers.")
        println(process.err.text)
    }

}


def updateCommit(){
    withCredentials([usernamePassword(credentialsId:'github-personal-access', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
        echo "Updating commit version"
        sh "git config --global user.email 'jenkins-server@gmail.com'"
        sh "git config --global user.name 'jenkins-server'"
        sh "git add ."
        sh "git commit -am 'ci:jenkins-server'"
        sh "git remote set-url origin https://${USER}:${PASS}@github.com/golebu2020/BC_Wildfire.git"
        sh "git push origin HEAD:production_pipeline"
    }
}


return this