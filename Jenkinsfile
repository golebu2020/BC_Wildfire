#! /usr/bin/env bash

pipeline{
    agent any
    environment{
        TAG="1.0.4"
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