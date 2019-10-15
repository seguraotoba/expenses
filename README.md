# expenses ![Build Status](https://travis-ci.org/seguraotoba/expenses.svg?branch=master)https://travis-ci.org/seguraotoba/expenses

Servi

## Getting Started

1. Start the application: `lein run`
2. Go to [localhost:8080](http://localhost:8080/) to see: `Hello World!`
3. Read your app's source code at src/expenses/service.clj. Explore the docs of functions
   that define routes and responses.
4. Run your app's tests with `lein midje`. Read the tests at test/expenses/service_test.clj.


## Configuration

To configure logging see config/logback.xml. By default, the app logs to stdout and logs/.
To learn more about configuring Logback, read its [documentation](http://logback.qos.ch/documentation.html).


## Developing your service

1. Start a new REPL: `lein repl`
2. Start your service in dev-mode: `(def dev-serv (run-dev))`
3. Connect your editor to the running REPL session.
   Re-evaluated code will be seen immediately in the service.

### [Docker](https://www.docker.com/) container support

1. Configure your service to accept incoming connections (edit service.clj and add  ::http/host "0.0.0.0" )
2. Build an uberjar of your service: `lein uberjar`
3. Build a Docker image: `sudo docker build -t expenses .`
4. Run your Docker image: `docker run -p 8080:8080 expenses`


## Links
* [Other Pedestal examples](http://pedestal.io/samples)
