#!/usr/bin/env bash

export TAG=$1
docker tag bc_wildfire_web:${TAG} golebu2023/image-registry:bc_wildfire_web-${TAG}
docker tag bc_wildfire_ui:${TAG} golebu2023/image-registry:bc_wildfire_ui-${TAG}
docker push golebu2023/image-registry:bc_wildfire_web-${TAG}
docker push golebu2023/image-registry:bc_wildfire_ui-${TAG} 
docker rmi bc_wildfire_web:${TAG} bc_wildfire_ui:${TAG} 