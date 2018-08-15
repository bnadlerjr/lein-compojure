# Change Log
All notable changes to this project will be documented in this file. This change log follows the conventions of [keepachangelog.com](http://keepachangelog.com/).

## [Unreleased]
### Added
- Example site and api routes with tests
- javax servlet as a development dependency
- Script for starting a tmux session w/ windows for workspace, server, etc.
- Templating helpers with flash support
- Start / Stop functions
- Use basic heroku secure middleware in production
- Docs on how to use prone, run a focused test
- Support for logfmt
- `lein server` also start an nREPL that can be connected to with `lein repl :connect`

### Fixed
- Use correct test assertion order in handler test

### Changed
- Don't automatically open a browser window when running `lein server`
- Separate app namespace from routes
- Use HTTP Basic Auth by default

### Deprecated
### Removed
- `app_secret` env variable references
- Remove env namespace, just use environ everywhere instead

### Fixed
- Incorrect name in project / Clojars

### Security

## [0.1.0] - 2018-08-11
### Added
- Initial version

[Unreleased]: https://github.com/bnadlerjr/lein-compojure/compare/0.1.0...HEAD
[0.1.0]: https://github.com/bnadlerjr/lein-compojure/tree/v0.1.0
