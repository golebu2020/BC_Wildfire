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

        stage("increment version"){
            script{
                echo "incrementing..."
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