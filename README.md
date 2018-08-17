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
* [Selmer](https://github.com/yogthos/Selmer) templating with default [Bootstrap](https://getbootstrap.com/docs/3.3/) layout and [FontAwesome](https://fontawesome.com/) support
* Optional PostgreSQL support with [HugSQL](https://www.hugsql.org/) and migrations with [ragtime](https://github.com/weavejester/ragtime)
* Optional SASS support

## Usage

`lein new bnadlerjr/lein-compojure PROJECT_NAME [+postgresql]`

## Clojars

1. Document added, removed, fixed, etc. in CHANGELOG
1. Update the version in `project.clj`
1. `git add . && git commit`
1. `git tag -a vx.x.x -m "Tag version x.x.x"`
1. `git push --tags && git push`
1. `lein deploy clojars`

## TODO:
* optional cljs support
* investigate how mount could fit in
* exception email support; middleware for prod --> maybe segment which can send info to bugsnag or another service? -- actually segment only sends identify to bugsnag, specific bugsnag middleware is still needed (https://github.com/omartell/bugsnag-client-clj/blob/master/src/bugsnag_client/core.clj)
* optional MongoDB support w/ Monger
* optional user mgmt. support (users table, queries, templates, login, registration, password reset, forgot password)
* optional user mgmt. support for OAuth (user table, queries, user creation, login)
* look into using hooks for DB functions (https://github.com/technomancy/robert-hooke/)
* CircleCI support
* better 500 and 404 error handling (default pages? middleware for 500?)

## License

Copyright © 2018 Bob Nadler, Jr.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
