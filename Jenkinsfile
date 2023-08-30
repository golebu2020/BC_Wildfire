#! /usr/bin.env bash

def gv
def major = 0
def minor = 0
def patch = 0

pipeline{
    agent any
    environment{
        WORKSPACE = pwd()
    }
    stages{
        stage("test"){
            steps{
                script{
                    echo "Testing..."
                    sh " docker-compose run web sh -c 'python manage.py wait_for_db && python manage.py test' --build-arg TAG=${major}.${minor}.${patch}"
                }
            }
        }

        stage("build and push"){
            steps{
                script{
                    echo "Building..."
                    

                }
            }
            
        }

        stage("increment version"){
            steps{
                script{
                    echo "incrementing..."
                    gv.incrementVersion()
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