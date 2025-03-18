#!/bin/bash

SCRIPT=`readlink -f "${BASH_SOURCE:-$0}"`
SCRIPT_DIR_PATH=`dirname ${SCRIPT}`
CI_DIR_PATH=`dirname ${SCRIPT_DIR_PATH}`
ROOT_PATH=`dirname ${CI_DIR_PATH}`

${ROOT_PATH}/mvnw -U clean package -Dmaven.test.skip=true

mkdir -p /tmp/artifact

cp ${ROOT_PATH}/nacos-demos/nd-service-a/target/nd-service-a.jar /tmp/artifact
cp ${ROOT_PATH}/nacos-demos/nd-service-b/target/nd-service-b.jar /tmp/artifact
cp ${ROOT_PATH}/nacos-demos/nd-service-c/target/nd-service-c.jar /tmp/artifact

cp ${ROOT_PATH}/seata-nacos-demos/account-service/target/account-service.jar /tmp/artifact
cp ${ROOT_PATH}/seata-nacos-demos/order-service/target/order-service.jar /tmp/artifact
cp ${ROOT_PATH}/seata-nacos-demos/storage-service/target/storage-service.jar /tmp/artifact
cp ${ROOT_PATH}/seata-nacos-demos/shop-service/target/shop-service.jar /tmp/artifact

cp ${ROOT_PATH}/seata-zk-demos/sz-account-service/target/sz-account-service.jar /tmp/artifact
cp ${ROOT_PATH}/seata-zk-demos/sz-order-service/target/sz-order-service.jar /tmp/artifact
cp ${ROOT_PATH}/seata-zk-demos/sz-storage-service/target/sz-storage-service.jar /tmp/artifact
cp ${ROOT_PATH}/seata-zk-demos/sz-shop-service/target/sz-shop-service.jar /tmp/artifact

cp ${ROOT_PATH}/seata-consul-demos/sc-account-service/target/sc-account-service.jar /tmp/artifact
cp ${ROOT_PATH}/seata-consul-demos/sc-order-service/target/sc-order-service.jar /tmp/artifact
cp ${ROOT_PATH}/seata-consul-demos/sc-storage-service/target/sc-storage-service.jar /tmp/artifact
cp ${ROOT_PATH}/seata-consul-demos/sc-shop-service/target/sc-shop-service.jar /tmp/artifact

