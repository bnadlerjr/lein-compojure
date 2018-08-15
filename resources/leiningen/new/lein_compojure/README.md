# {{ humanized-name }}

TODO: Write a general description of the project.

## Table of Contents
* [Pre-Requisites](#pre-requisites)
* [Initial Setup](#initial-setup)
* [Workflow](#workflow)
* [Configuration Variables](#configuration-variables)

## Pre-requisites
* [Clojure](https://clojure.org/guides/getting_started)
* [Leiningen](https://leiningen.org/)
* [Heroku Toolbelt](https://devcenter.heroku.com/articles/heroku-cli)

Both Clojure and Leiningen require [OpenJDK](http://openjdk.java.net/install/). Version 8 is recommended.

## Initial Setup
1. Clone this repository
2. Create a `profiles.clj` file in the project's root. See the [Configuration Variables](#configuration-variables) section for more information.
3. Run `lein deps` in the project's root to install dependencies.

## Workflow
### I want to...
<dl>
    <dt>start the development server</dt>
    <dd><pre>lein server</pre></dd>

    <dt>start a REPL session</dt>
    <dd><pre>lein repl</pre></dd>

    <dt>watch source files for changes and automcatically run tests</dt>
    <dd><pre>lein autotest</pre></dd>

    <dt>watch source files for changes and automatically run a <strong>single</strong> test</dd>
    <dd>
        With the below code, only <code>test-addition</code> will run until the <code>:test-refresh/focus</code> marker is removed from it.
        <pre>
(deftest ^:test-refresh/focus test-addition
  (is (= 2 (+ 1 1))))

(deftest test-subtraction
  (is (= 0 (- 10 9 1))))
        </pre>
    </dd>

    <dt>insert a <a href="https://github.com/magnars/prone">prone</a> debug breakpoint</dt>
    <dd>
        <pre>
(ns example
  (:require [prone.debug :refer [debug]]))

(defn myhandler [req]
  ;; ...
  (let [person (lookup-person (:id (:params req)))]
    (debug)))
        </pre>
    </dd>

    <dt>lint project source files</dt>
    <dd><pre>lein lint</pre></dd>

    <dt>deploy a production release to Heroku</dt>
    <dd>
        <ol>
            <li>Document added, removed, fixed, etc. in <code>CHANGELOG.md</code></li>
            <li>Update the version entry in <code>project.clj</code></li>
            <li><code>git add . && git commit</code></li>
            <li><code>git tag -a vNEW.VERSION.NUMBER -m "Tag version NEW.VERSION.NUMBER"</code></li>
            <li><code>git push origin --tags && git push</code></li>
            <li><code>lein release</code></li>
        </ol>
    </dd>
</dl>

## Configuration Variables
Configuration is managed via the [lein-environ](https://github.com/weavejester/environ) plugin. This allows you to have a local configuration (`profiles.clj`) for development / test and use environment variables for production on Heroku. Here is a sample `profiles.clj` that should be placed in this project's root folder:

```
{:profiles-dev
 {:env
  {:ring-env "development"}}
 :profiles-test
 {:env
  {:ring-env "test"}}}
```

The `profiles.clj` is ignored by Git so it is safe to put secrets into it. In production, environment variables are set as normal either through the Heroku CLI or dashboard. The `{{ name }}.env` namespace will extract the variables from the environment, perform any checking (i.e. check for required values, proper types, etc.) and make them available to the application. The following configuration variables are supported:

<table>
    <thead>
        <tr>
            <th><code>profiles.clj</code> Name</th>
            <th>Heroku Name</th>
            <th>Required</th>
            <th>Type Conversion</th>
            <th>Default Value</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>port</td>
            <td>PORT</td>
            <td>Yes</td>
            <td>Integer</td>
            <td>Depends on environment</td>
            <td>In development, an available port will automatically be selected. For production, this value is provided by Heroku.</td>
        </tr>
        <tr>
            <td>ring-env</td>
            <td>RING_ENV</td>
            <td>Yes</td>
            <td>String</td>
            <td>production</td>
            <td>Mode in which the application will run. Valid values are <strong>development</strong>, <strong>test</strong> and <strong>production</strong></td>
        </tr>
    </tbody>
</table>
