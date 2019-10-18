#!/bin/bash

#scp ${TRAVIS_BUILD_DIR}/over-under-league.jar
echo "Starting deploy..."

for entry in "${TRAVIS_BUILD_DIR}"/*
do
  echo "$entry"
done