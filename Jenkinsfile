#!/usr/bin/env bash

def gv
def major
def minor
def patch
def tagName
def sshing
def dockerRegistry

pipeline{
    agent any
    environment{
        WORKSPACE = pwd()
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
                    echo "Testing and building......"
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
                    echo "Deploying....."
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