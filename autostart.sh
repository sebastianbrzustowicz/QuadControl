#!/bin/bash

docker pull tensorflow/serving

docker stop collision-detection-ai
docker rm collision-detection-ai

docker run -p 8501:8501 -d --name=collision-detection-ai --mount type=bind,source="$(pwd)"/tfmodel,target=/models/tfmodel/1 -e MODEL_NAME=tfmodel -t tensorflow/serving

#mvn clean install

#mvn spring-boot:run

# below line for running without making a package
#mvn exec:java -Dexec.mainClass="dev.sebastianbrzustowicz.Main"

# comment below line if JAR is deployed
mvn clean package

java -jar target/QuadControl-1.0-SNAPSHOT.jar