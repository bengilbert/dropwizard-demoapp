#!/usr/bin/env bash
mvn clean verify
java -jar target/mergermarket-1.0-SNAPSHOT.jar server ./src/main/resources/config.yml
