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
    <dt>lint project source files</dt>
    <dd><pre>lein lint</pre></dd>
    <dt>deploy a production release to Heroku</dt>
    <dd>
        <ol>
            <li>Document added, removed, fixed, etc. in <pre>CHANGELOG.md</pre></li>
            <li>Update the version entry in <pre>project.clj</pre></li>
            <li><pre>git add . && git commit</pre></li>
            <li><pre>git tag -a v<VERSION> -m "Tag version <VERSION>"</pre></li>
            <li><pre>git push origin --tags && git push</pre></li>
            <li><pre>lein release</pre></li>
        </ol>
    </dd>
</dl>

## Configuration Variables
Configuration is managed via the [lein-environ](https://github.com/weavejester/environ) plugin. This allows you to have a local configuration (`profiles.clj`) for development / test and use environment variables for production on Heroku. Here is a sample `profiles.clj` that should be placed in this project's root folder:

```
{:profiles-dev
 {:env
  {:app-secret "secret"
   :ring-env "development"}}
 :profiles-test
 {:env
  {:app-secret "secret"
   :ring-env "test"}}}
```

The `profiles.clj` is ignored by Git so it is safe to put secrets into it. In production, environment variables are set as normal either through the Heroku CLI or dashboard. The `{{ name }}.env` namespace will extract the variables from the environment, perform any checking (i.e. check for required values, proper types, etc.) and make them available to the application. The following configuration variables are supported:

<table>
    <thead>
        <tr>
            <th><pre>profiles.clj</pre> Name</th>
            <th>Heroku Name</th>
            <th>Required</th>
            <th>Type Conversion</th>
            <th>Default Value</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>app-secret</td>
            <td>APP_SECRET</td>
            <td>Yes</td>
            <td>String</td>
            <td>None</td>
            <td>Application secret used for encrypting session information.</td>
        </tr>
        <tr>
            <td>port</td>
            <td>PORT</td>
            <td>Only in production</td>
            <td>Integer</td>
            <td>Depends on environment</td>
            <td>In development, an available port will automatically be selected. For production, this value must be specified.</td>
        </tr>
        <tr>
            <td>ring-env</td>
            <td>RING_ENV</td>
            <td>Yes</td>
            <td>String</td>
            <td>None</td>
            <td>Mode in which the application will run. Valid values are <strong>development</strong>, <strong>test</strong> and <strong>production</strong></td>
        </tr>
    </tbody>
</table>
