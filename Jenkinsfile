#! /usr/bin/env bash

pipeline{
    agent any
    stages{
        stage("test"){
            steps{
                script{
                    echo "##########################Imnplementing linting and testing for web#############################"
                    sh "docker-compose run web sh -c 'python manage.py wait_for_db && python manage.py test'"
                }
            }
        }

        stage("build and push"){
            steps{
                script{
                    echo "#########################Building image################################################"
                    sh "docker-compose build"
                    
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