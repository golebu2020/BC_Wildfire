#! /usr/bin.env bash

def major, minor, patch
def gv

pipeline{
    agent any
    environment{
        WORKSPACE = pwd()
    }
    stages{
        stage("init"){
            steps{
                script{
                    gv = load 'script.groovy'
                }
            }
        }

        stage("test"){
            steps{
                script{
                    echo "Testing..."
                    gv.testing()
                }
            }
        }

        stage("build and push"){
            steps{
                script{
                    echo "Building..."
                    gv.buildPush()

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