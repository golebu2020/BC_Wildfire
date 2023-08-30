#! /usr/bin.env bash

pipeline{
    agent any
    stages{
        stage("test"){
            steps{
                script{
                    echo "Testing..."
                    sh " docker-compose run web sh -c 'python manage.py wait_for_db && python manage.py test' "
                }
            }
            
        }

        stage("build"){
            steps{
                script{
                    echo "Building..."
                }
            }
            
        }

        stage("deploy"){
            steps{
                script{
                    echo "Deploying..."
                }
            }
            
        }
    }
}