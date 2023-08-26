#! /usr/bin/env bash

pipeline{
    agent any
    stages{
        stage("test"){
            steps{
                script{
                    echo "##########################Imnplementing linting and testing#############################"
                    sh "docker-compose run web sh -c 'python manage.py test'"
                }
            }
        }

        stage("build"){
            steps{
                script{
                    echo "Building image..."
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