#! /usr/bin/env bash


def majorTag
def minorTag
def patchTag
def file

pipeline{
    agent any
    environment{
        WORKSPACE=pwd()
        UI_REG = 'golebu2023/image-registry:bc_wildfire_ui'
        WEB_REG = 'golebu2023/image-registry:bc_wildfire_web'
    }
    stages{
        stage("test"){
            steps{
                script{
                    echo "##########################Imnplementing linting and testing for web#############################"
                    sh " docker-compose run web sh -c 'python manage.py wait_for_db && python manage.py test' "
                }
            }
        }

        stage("increment patch tag"){
            steps{
                script{
                    echo "##########################Imcrementing Patch Tag#############################"
                    file = readFile("${WORKSPACE}/version.xml")
                    def matcher = file.split(",")
                    majorTag = matcher[0]
                    minorTag = matcher[1]
                    patchTag = matcher[2]
                    
                    patchTag = patchTag as Integer
                    patchTag++
                }
            }
        }

        stage("build and push"){
            steps{
                script{
                    // docker push golebu2023/image-registry:tagname
                    echo "#########################Building Image and pushing to Container Repo################################################"
                    
                    def TAG = "${majorTag}.${minorTag}.${patchTag}"
                    sh "bash ./sh_command.sh ${TAG}"
                    sh "docker-compose build"
                    withCredentials([usernamePassword('credentialsId':'dockerhub-credentials', usernameVariable:'USER', passwordVariable: 'PASS')]){
                        sh "echo ${PASS} | docker login --username ${USER} --password-stdin"
                       
                        sh """
                            docker tag bc_wildfire_web:${TAG} ${WEB_REG}-${TAG} && 
                            docker tag bc_wildfire_ui:${TAG} ${UI_REG}-${TAG} &&
                            docker push ${WEB_REG}-${TAG} &&
                            docker push ${UI_REG}-${TAG} &&
                            docker rmi bc_wildfire_web:${TAG} bc_wildfire_ui:${TAG}
                        """ 
                    }  
                    writeFile(file: "${WORKSPACE}/version.xml", text: "${majorTag},${minorTag},${patchTag}", encoding: "UTF-8")
                }
            }
        }

        stage("deploy"){
            steps{
                script{
                    echo "Deploying image to AWS EC2..."
                }
            }
        }

        stage("push update commit"){
            steps{
                script{
                    echo "##########################Pushing the updated commit to github...#############################"
                    withCredentials([usernamePassord('credentialsId': 'github-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')]){
                        sh """
                            git config --global user.email 'jenkins-server@gmail.com' &&
                            git config --global user.name 'jenkins-server' &&
                            git commit -am 'jenkins push commit update' &&
                            git remote set-url origin https://${USER}:${PASS}@github.com/golebu2020/BC_Wildfire.git &&
                            git push origin HEAD:jenkins-pipeline 
                        """
                    }
                }
            }
        }
    }
}