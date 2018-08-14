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

## Clojars

1. Document added, removed, fixed, etc. in CHANGELOG
1. Update the version in `project.clj`
1. `git add . && git commit`
1. `git tag -a vx.x.x -m "Tag version x.x.x"`
1. `git push --tags && git push`
1. `lein deploy clojars`

## TODO:
* start / stop functions
* javax dependency
* flash support
* CSRF token support
* document how to use prone
* document how to focus a single autotest
* Tmux script
* HTTP Basic Auth by default
* use basic heroku secure middleware in production

## License

Copyright © 2018 Bob Nadler, Jr.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
