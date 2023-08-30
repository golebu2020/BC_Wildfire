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
                    gv.testBuild()
                }
            }
        }

        stage("build and push"){
            steps{
                script{
                    gv.buildPush()
                }
            }
        }

        stage("increment version"){
            steps{
                script{
                   gv.incrementVersion()
                }
            }
        }
        
        stage("deploy"){
            steps{
                script{
                    gv.deploy()
                }
            }
        }

        stage("update commit"){
            steps{
                script{
                    gv.updateCommit()
                }
            }
        }
    }
}