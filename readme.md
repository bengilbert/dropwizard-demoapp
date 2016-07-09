***Welcome***

This is a demo app written for a coding test.  I've put this on github as it showcases a few techniques that might be useful to others::
 
 * Using docker containers within java junit tests using TestContainers. 
 * Testing an inprocess dropwizard application using WireMock and overriding the dropwizard config to point to the wiremock endpoint
 * Testing dropwizard Resources using ResourceTestRules

You need docker to build this application.  I'm using the beta version of docker for osx (Version 1.12.0-rc2-beta16 (build: 9493)).  If this doesn't work (you will know when) and you are on OSX then try defaulting back to using a docker-machine instead.

```
docker-machine create default  --driver=virtualbox
eval `docker-machine env default`
mvn clean verify
```

Finally if that doesn't work and you really want to see the application running then you can simply `@Ignore` the Integration tests to get the application running.

**Usage**

To run the application:

`./run.sh`

To change endpoints edit:

`src/main/resources/config.yml`

**Endpoints**

The endpoints are:

* `GET /api/companies` - returns a list of company ticker codes in JSON format
* `GET /api/companies/{ticker-code}` - returns a json fragment for the company

A very simple angular UI is at:

* http://localhost:8080/app/index.html

Although this is purely for illustrative purposes and shouldn't be used as a reference.

The app defaults to running on port 8080 as this is the standard dropwizard port.

**Caution**

This application currently depends on endpoints that are not hosted by myself.  They could be removed or changed at any time.  When this happens the application will not run, however the tests and examples will continue to function.
