#! /usr/bin/env bash


def majorTag
def minorTag
def patchTag
def file

pipeline{
    agent any
    environment{
        WORKSPACE=pwd()
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
                    file = readFile("${WORKSPACE}/version.xml")
                    def matcher = file.split(",")
                    majorTag = matcher[0]
                    minorTag = matcher[1]
                    patchTag = matcher[2]
                    
                    patchTag = patchTag as Integer
                    patchTag = patchTag + 1
                }
            }
        }

        stage("build and push"){
            steps{
                script{
                    // docker push golebu2023/image-registry:tagname
                    echo "#########################Building Image and pushing to Container Repo################################################"
                    TAG = "${majorTag}.${minorTag}.${patchTag}"

                    withCredentials([usernamePassword('credentialsId':'dockerhub-credentials', usernameVariable:'USER', passwordVariable: 'PASS')]){
                        sh "echo ${PASS} | docker login --username ${USER} --password-stdin"

                        sh """
                            docker-compose build --build-arg TAG=${TAG} &&
                            docker tag bc_wildfire_web:${TAG} ${WEB_REG}-${TAG} &&
                            docker tag bc_wildfire_ui:${TAG} ${UI_REG}-${TAG} &&
                            docker push ${WEB_REG}-${TAG} &&
                            docker push ${UI_REG}-${TAG} &&
                            docker rmi bc_wildfire_web:${TAG} bc_wildfire_ui:${TAG}
                           
                        """
                    }  
                    writeFile(file: "${WORKSPACE}/version.xml", text: "${TAG}", encoding: "UTF-8")
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
    }
}