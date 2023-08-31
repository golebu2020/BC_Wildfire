#!/usr/bin/env bash

export TAG=$1

docker-compose -f docker-compose-prod.yaml up --build -d