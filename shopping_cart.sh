#!/usr/bin/env bash

# build - Builds the Application using the Maven command and copies the required for this application.
# run - Interactive Mode of Operation for performing transactions with the client.
# runBatch - Expects a file name to executes all the commands based on the commands in the file.

case $1 in
    build)
        echo "Building Application ..."
        mvn clean package
        cp -v libs/*.jar target
    ;;

    *)
        echo "Choose an option build/run/runBatch the application"
    ;;
esac
