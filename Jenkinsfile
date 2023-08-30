#! /usr/bin.env bash

def gv
def major = 1
def minor = 0
def patch = 0
def tag

pipeline{
    agent any
    environment{
        WORKSPACE = pwd()
        
    }
    stages{
        stage("test and building"){
            steps{
                script{
                    echo "Teesting and building..."
                    def tag = "${major}.${minor}.${patch}"
                    sh "bash ./test.sh ${tag}"
                }
            }
        }

        stage("build and push"){
            steps{
                script{
                    echo "Building..."
                    withCredentials([usernamePassword(credentialsId:'github-credentials', usernameVariable: 'USR', passwordVariable: 'PASS')]){
                        // docker push golebu2023/image-registry:tagname
                        sh "docker tag bc_wildfire_web:${tag} golebu2023/image-registry:bc_wildfire_web-${tag}"
                        sh "docker tag bc_wildfire_ui:${tag} golebu2023/image-registry:bc_wildfire_ui-${tag}"
                    }
                }
            }
        }

        stage("increment version"){
            steps{
                script{
                    echo "incrementing..."
                }
            }
        }
        
        stage("deploy"){
            steps{
                script{
                    echo "Deploying app..."
                }
            }
        }

        stage("update commit"){
            steps{
                script{
                    echo "Updating commit version"
                }
            }
        }
    }
}