# lein-compojure

A Leiningen template for compojure projects.

## Features
Generates a compojure project with the following default features:
* Basic REPL user namespace with doc and refresh support
* Continuous testing with [lein-test-refresh](https://github.com/jakemcc/lein-test-refresh)
* Environment variable management with [lein-environ](https://github.com/weavejester/environ)
* Heroku deployment via the [lein-heroku](https://github.com/heroku/lein-heroku) plugin
* Integration with [lein-ring](https://github.com/weavejester/lein-ring) server
* [HTTP Kit](http://www.http-kit.org/) web server in production
* [Prone](https://github.com/magnars/prone) for exception reporting middleware during development
* [Selmer](https://github.com/yogthos/Selmer) templating with default [Bootstrap](https://getbootstrap.com/docs/3.3/) layout

## Usage

`lein new bnadlerjr/lein-compojure <PROJECT_NAME>`

## License

Copyright © 2018 Bob Nadler, Jr.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
