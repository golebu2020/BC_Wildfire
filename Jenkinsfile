#! /usr/bin/env bash

pipeline{
    agent any
    stages{
        stage("test"){
            steps{
                script{
                    echo "Imnplementing linting and testing..."
                }
            }
        }

        stage("build"){
            steps{
                script{
                    echo "Building image..."
                }
            }
        }

        stage("deploy"){
            steps{
                script{
                    echo "Deploying image to AWS EC2..."
                }
            }
        }
    }
}