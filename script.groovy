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
}


def buildPush(){

    withCredentials([usernamePassword(credentialsId:'dockerhub-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')]){
        sh "echo ${PASS} | docker login --username ${USER} --password-stdin"
        sh "docker tag bc_wildfire_web:${tagName} golebu2023/image-registry:bc_wildfire_web-${tagName}"
        sh "docker tag bc_wildfire_ui:${tagName} golebu2023/image-registry:bc_wildfire_ui-${tagName}"
        sh "docker push golebu2023/image-registry:bc_wildfire_web-${tagName}"
        sh "docker push golebu2023/image-registry:bc_wildfire_ui-${tagName}"
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

    sshagent(['production_pipeline']) {
        sh "scp docker-compose-prod-tag.sh docker-compose-prod.yaml .env.prod ubuntu@3.99.131.248:/root"
        sh "ssh -o StrictHostKeyChecking=no root@128.199.4.224 ${runSSH}"
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
        sh "git push origin HEAD:jenkins-pipeline"
    }
}


return this
