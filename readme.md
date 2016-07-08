***Welcome***

This is a demo app written for a coding test.  It showcases using docker containers within java junit tests using TestContainers.

You need docker to build this application.  I'm using the beta version of docker for osx (Version 1.12.0-rc2-beta16 (build: 9493)).  If this doesn't work (you will know when) and you are on OSX then try defaulting back to using a docker-machine instead.

To run the application:

`./run.sh`

To change endpoints edit:

`src/main/resources/config.yml`

The endpoints are:

* GET /api/companies - returns a list of company ticker codes in JSON format
* GET /api/companies/{ticker-code} - returns a json fragment for the company

A very simple angular UI is at:

http://localhost:8080/app/index.html

The app defaults to running on port 8080 as this is the standard dropwizard port.