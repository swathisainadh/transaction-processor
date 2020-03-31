#!/bin/bash
#Pull the required docker images and run them as daemon
cd .. && docker-compose up -d
#If any services are running on 8082 & 8083, it would kill those processes
kill $(lsof -t -i :8082)
kill $(lsof -t -i :8083)
cd ../transaction-producer && ./mvnw spring-boot:run &
cd ../transaction-consumer && ./mvnw spring-boot:run