#!/bin/bash

## this script depends on xmlstarlet
set -e

SCRIPT=$(readlink -f "${BASH_SOURCE:-$0}")
SCRIPT_DIR_PATH=$(dirname ${SCRIPT})
CI_DIR_PATH=$(dirname ${SCRIPT_DIR_PATH})
ROOT_PATH=$(dirname ${CI_DIR_PATH})

DEPENDENCIES_FILE="${ROOT_PATH}"/ci/maven/dependencies.properties

VERSION=$(props value "${DEPENDENCIES_FILE}" opcal-cloud-starter-parent.version)

echo "opcal cloud version is [${VERSION}]"
# update opcal-cloud-starter-parent version
xmlstarlet edit -P -L -O -u "/_:project/_:parent/_:version" -v "${VERSION}" "${ROOT_PATH}"/pom.xml

"${ROOT_PATH}"/mvnw -U clean compile >> /dev/null 2>&1

# get spring boot version from opcal-cloud-starter-parent
NUMS=($(echo "${VERSION}" | tr '.' '\n'))
SPRING_BOOT_VERSION="${NUMS[0]}.${NUMS[1]}.${NUMS[2]}"

echo "spring boot version is [${SPRING_BOOT_VERSION}]"

"${ROOT_PATH}"/mvnw versions:set-property -Dproperty=spring-boot.version -DnewVersion=${SPRING_BOOT_VERSION} >> /dev/null 2>&1


# get spring cloud version from opcal-cloud-starter-parent
#SPRING_CLOUD_VERSION=$(${ROOT_PATH}/mvnw help:evaluate -Dexpression=spring-cloud.version | grep "^[^\\[]" |grep -v Download |grep -v Progress)

#echo "spring cloud version is [${SPRING_CLOUD_VERSION}]"

#${ROOT_PATH}/mvnw versions:set-property -Dproperty=spring-cloud.version -DnewVersion=${SPRING_CLOUD_VERSION} >> /dev/null 2>&1