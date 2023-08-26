#! /usr/bin/env bash

pipeline{
    agent any
    environment{
        TAG="1.0.4"
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
                    echo "The Workspace is: ${WORKSPACE}"
                    sh "cat ${WORKSPACE}/version.xml"
                    def file = readFile("${WORKSPACE}/version.xml")
                    def majorTag = file[0]
                    def minorTag = file[1]
                    def patchTag = file[2]

                    echo "major tag is ${majorTag}"
                    echo "minor tag is ${minorTag}"
                    echo "patcg tag is ${patchTag}"
                }
            }
        }

        stage("build and push"){
            steps{
                script{
                    // docker push golebu2023/image-registry:tagname
                    echo "#########################Building image################################################"
                    sh "docker-compose build"

                    withCredentials([usernamePassword('credentialsId':'dockerhub-credentials', usernameVariable:'USER', passwordVariable: 'PASS')]){
                        sh "echo ${PASS} | docker login --username ${USER} --password-stdin"
                        sh "bash ./sh_command.sh ${TAG}"
                    }
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