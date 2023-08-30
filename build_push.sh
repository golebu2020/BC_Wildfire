#!/usr/bin/env bash

export TAG=$1
docker-compose build --build-arg TAG=${TAG}
docker tag bc_wildfire_web:${TAG} ${WEB_REG}-${TAG} 
docker tag bc_wildfire_ui:${TAG} ${UI_REG}-${TAG} 
docker push ${WEB_REG}-${TAG} 
docker push ${UI_REG}-${TAG} 
docker rmi bc_wildfire_web:${TAG} bc_wildfire_ui:${TAG}
