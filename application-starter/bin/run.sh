cd .. && docker-compose up -d
echo docker ps
cd ../transaction-producer && mvn clean install && java -jar target/transaction-producer-0.0.1-SNAPSHOT.jar