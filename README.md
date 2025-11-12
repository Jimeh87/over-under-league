# NBA Over/Under League [![CircleCI](https://dl.circleci.com/status-badge/img/gh/Jimeh87/over-under-league/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/Jimeh87/over-under-league/tree/master)
Used to track standings for an over under league I run with some local basketball enthusiasts 

## Site

[https://overunderleague.com](http://overunderleague.com)

## Development notes

Run locally using docker: `./mvnw clean install && docker run -p 8080:8080 --rm -it $(docker build -q .)`

Deploy steps: Push to master -> circle ci builds and publishes docker image -> Manually go into digital ocean and deploy app
