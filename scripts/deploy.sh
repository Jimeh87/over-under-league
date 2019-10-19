#!/bin/bash

set -ev

#scp ${TRAVIS_BUILD_DIR}/over-under-league.jar
echo "Starting deploy..."

openssl aes-256-cbc -K $encrypted_eeb17145cacf_key -iv $encrypted_eeb17145cacf_iv -in prod_deploy_key.enc -out prod_deploy_key -d
eval "$(ssh-agent -s)"
chmod 600 ./prod_deploy_key
echo -e "Host $PROD_SERVER_IP_ADDRESS\n\tStrictHostKeyChecking no\n" >> ~/.ssh/config
ssh-add ./prod_deploy_key
tar -czvf over-under-league.tar.gz "${TRAVIS_BUILD_DIR}/target/over-under-league.jar"
scp -i ./prod_deploy_key over-under-league.tar.gz "${PROD_USER}@${PROD_SERVER_IP_ADDRESS}"
ssh -i ./prod_deploy_key "${PROD_USER}@${PROD_SERVER_IP_ADDRESS}" pwd
