#!/bin/bash

set -ev

echo "Starting deploy..."

tar -czvf over-under-league.tar.gz -C ./target over-under-league.jar
scp -o StrictHostKeyChecking=no over-under-league.tar.gz "${PROD_USER}@${PROD_SERVER_IP_ADDRESS}:"
ssh -o StrictHostKeyChecking=no "${PROD_USER}@${PROD_SERVER_IP_ADDRESS}" 'tar -xvf over-under-league.tar.gz -C /srv/over-under-league/ && chmod 755 /srv/over-under-league/over-under-league.jar && sudo /bin/systemctl restart over-under-league'

echo "Deploy complete!"
