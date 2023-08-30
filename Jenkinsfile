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
                    tag = "${major}.${minor}.${patch}"
                    sh "bash ./test.sh ${tag}"
                }
            }
        }

        stage("build and push"){
            steps{
                script{
                    echo "Building..."
                    withCredentials([usernamePassword(credentialsId:'github-credentials', usernameVariable: 'USR', passwordVariable: 'PASS')]){
                        sh "docker tag bc_wildfire_web:${tag} golebu2023/image-registry:bc_wildfire_web-${tag}"
                        sh "docker tag bc_wildfire_ui:${tag} golebu2023/image-registry:bc_wildfire_ui-${tag}"
                        sh "docker push golebu2023/image-registry:bc_wildfire_web-${tag}"
                        sh "docker push golebu2023/image-registry:bc_wildfire_ui-${tag}"
                    }
                }
            }
        }

        stage("increment version"){
            steps{
                script{
                    echo "incrementing..."
                    def file = readFile("${WORKSPACE}/version.xml")
                    def matcher = file.split(",")
                    major = matcher[0]
                    minor = matcher[1]
                    patch = matcher[2]

                    echo "${major}.${minor}.${patch}"
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