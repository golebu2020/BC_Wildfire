#!/usr/bin/env groovy

def testBuild(){
    def file = readFile("${env.WORKSPACE}/version.xml")
    def matcher = file.split(",")
    major = matcher[0]
    minor = matcher[1]
    patch = matcher[2]
    tagName = "${major}.${minor}.${patch}"
    sh "bash ./test.sh ${tagName}"
}


def push(){
    withCredentials([usernamePassword(credentialsId:'dockerhub-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')]){
        sh "echo ${PASS} | docker login --username ${USER} --password-stdin"
        sh "docker system prune --all"
        sh "docker tag bc_wildfire_web:${tagName} ${env.WEBREG}-${tagName}"
        sh "docker tag bc_wildfire_ui:${tagName} ${env.UIREG}-${tagName}"
        sh "docker push ${env.WEBREG}-${tagName}"
        sh "docker push ${env.UIREG}-${tagName}"
        sh "docker image prune -a -f"
    }
}


def incrementVersion(){
    def patchTemp = patch as Integer
    patchTemp++
    writeFile(file: "${env.WORKSPACE}/version.xml", text: "${major},${minor},${patchTemp}", encoding: "UTF-8")
}


def deploy(){
    echo "Deploying app..."
    def deployTag = "${major}.${minor}.${patch}"
    def runSSH = "bash ./docker-compose-prod-tag.sh ${deployTag}"
    def deleteImageContainer = "docker rm -vf \$(docker ps -aq) && docker image prune -a -f"

    sshagent(['deploy-key']) {
        sh 'scp -o StrictHostKeyChecking=no docker-compose-prod-tag.sh docker-compose-prod.yaml .env.prod root@165.232.147.254:/root'
        sh "${REMOTE_ACCESS} ${deleteImageContainer}"
        sh "${REMOTE_ACCESS} ${runSSH}"

        patch = patch as Integer
        if (patch > 0){
            sh "${REMOTE_ACCESS} docker rmi ${env.UIREG}-${major}.${minor}.${patch-1}"
            sh "${REMOTE_ACCESS} docker rmi ${env.WEBREG}-${major}.${minor}.${patch-1}"
        }
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
        sh "git push origin HEAD:staging_branch"
    }
}


return this
