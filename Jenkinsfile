#!/usr/bin/env bash

def gv
def major
def minor 
def patch
def tagName

pipeline{
    agent any
    environment{
        WORKSPACE = pwd()
    }
    stages{
        stage("test and building"){
            steps{
                script{
                    echo "Teesting and building....."
                    echo "incrementing..."
                    def file = readFile("${env.WORKSPACE}/version.xml")
                    def matcher = file.split(",")
                    major = matcher[0]
                    minor = matcher[1]
                    patch = matcher[2]
                    tagName = "${major}.${minor}.${patch}"
                    sh "bash ./test.sh ${tagName}"
                }
            }
        }

        stage("build and push"){
            steps{
                script{
                    echo "Building...."
                    withCredentials([usernamePassword(credentialsId:'dockerhub-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')]){
                        sh "echo ${PASS} | docker login --username ${USER} --password-stdin"
                        sh "docker tag bc_wildfire_web:${tagName} golebu2023/image-registry:bc_wildfire_web-${tagName}"
                        sh "docker tag bc_wildfire_ui:${tagName} golebu2023/image-registry:bc_wildfire_ui-${tagName}"
                        sh "docker push golebu2023/image-registry:bc_wildfire_web-${tagName}"
                        sh "docker push golebu2023/image-registry:bc_wildfire_ui-${tagName}"
                    }
                }
            }
        }

        stage("increment version"){
            steps{
                script{
                    patch = patch as Integer
                    patch++
                    writeFile(file: "${env.WORKSPACE}/version.xml", text: "${major},${minor},${patch}", encoding: "UTF-8")
                }
            }
        }
        
        stage("deploy"){
            steps{
                script{
                    echo "Deploying app...."
                }
            }
        }

        stage("update commit"){
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId:'github-personal-access', usernameVariable: 'USER', passwordVariable: 'PASS')]){
                        echo "Updating commit version"
                        sh "git config --global user.email 'jenkins-server@gmail.com'"
                        sh "git config --global user.name 'jenkins-server'"

                        sh "git add ."
                        sh "git commit -am 'ci:jenkins-server'"
                        sh "git remote set-url origin https://${USER}:${PASS}@github.com/golebu2020/BC_Wildfire.git"
                        sh "git push origin HEAD:jenkins-pipeline"
                    }
                    
                }
            }
        }
    }
}