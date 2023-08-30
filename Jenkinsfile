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
        stage("init"){
            steps{
                script{
                    gv = load 'script.groovy'
                }
            }
        }
        stage("test and building"){
            steps{
                script{
                    echo "Testing and building....."
                    gv.testBuild()
                }
            }
        }

        stage("build and push"){
            steps{
                script{
                    echo "Building...."
                    gv.buildPush()
                }
            }
        }

        stage("increment version"){
            steps{
                script{
                    echo "incrementing version"
                    gv.incrementVersion()
                }
            }
        }

        stage("deploy"){
            steps{
                script{
                    echo "Deploying..."
                    gv.deploy()
                }
            }
        }

        stage("update commit"){
            steps{
                script{
                    echo "updating commits..."
                    gv.updateCommit()
                }
            }
        }
    }
}