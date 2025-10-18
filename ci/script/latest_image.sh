#!/bin/bash

docker pull ghcr.io/opcal/eclipse-temurin:21-jre
docker pull nacos/nacos-server:v3.1.0-slim
docker pull ghcr.io/opcal/sentinel-dashboard:latest
docker pull hashicorp/consul:latest
docker pull mysql:8.0
docker pull apache/seata-server:2.5.0
docker pull zookeeper:3.9.4