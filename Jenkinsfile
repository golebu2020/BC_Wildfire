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
                    def version = "${major}.${minor}.${patch}"
                    sh "bash ./test.sh ${version}"
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