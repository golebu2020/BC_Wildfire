#!/usr/bin/env groovy

def gv, major, minor, patch, tagName, remoteAccess, uiReg, webReg

pipeline{
    agent any
    environment{
        WORKSPACE = pwd()
        REMOTE_ACCESS = "ssh -o StrictHostKeyChecking=no root@165.232.147.254"
        UIREG = "golebu2023/image-registry:bc_wildfire_ui"
        WEBREG = "golebu2023/image-registry:bc_wildfire_web"
    }
    stages{
        stage("initialize script"){
            steps{
                script{
                    echo "Initializing..."
                    gv = load 'script.groovy'
                }
            }
        }
        stage("test"){
            steps{
                script{
                    echo '''Testing and building...'''
                    gv.testBuild()
                }
            }
        }

        stage("build and push"){
            steps{
                script{
                    echo '''Building...'''
                    gv.push()
                }
            }
        }

        stage("increment version"){
            steps{
                script{
                    echo '''incrementing version...'''
                    gv.incrementVersion()
                }
            }
        }

        stage("deploy"){
            steps{
                script{
                    echo '''Deploying...'''
                    gv.deploy()
                }
            }
        }

        stage("update commit"){
            steps{
                script{
                    echo '''updating commits...'''
                    gv.updateCommit()
                }
            }
        }
    }
}