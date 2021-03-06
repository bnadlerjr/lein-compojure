(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :main ^:skip-aot {{name}}.app
  :min-lein-version "2.5.3"
  :source-paths ["src"]
  :resource-paths ["resources"]
  :target-path "target/%s"
  :test-paths ["test"]
  :uberjar-name "{{name}}-standalone.jar"

  :license {:name "The MIT License"
            :url  "https://opensource.org/licenses/MIT"}

  :aliases {"autotest" ["do" ["with-profile" "test" "test-refresh"]]
            "lint" ["do" ["ancient"] ["kibit"] ["eastwood"]]
            {{#postgresql?}}
            "generate-migration" ["run" "-m" "user/create-migration"]
            "migrate" ["run" "-m" "{{sanitized}}.db/migrate"]
            "rollback" ["run" "-m" "{{sanitized}}.db/rollback"]
            {{/postgresql?}}
            "release" ["do" ["clean"] ["uberjar"] ["heroku" "deploy"]]
            "server" ["do" ["ring" "server-headless"]]}

  :dependencies [[bnadlerjr/logfmt "0.1.0"]
                 [buddy/buddy-auth "2.1.0"]
                 [buddy/buddy-core "1.5.0"]
                 {{#postgresql?}}
                 [com.layerware/hugsql "0.4.9"]
                 {{/postgresql?}}
                 [compojure "1.6.1"]
                 [environ "1.1.0"]
                 [http-kit "2.3.0"]
                 [org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.logging "0.4.1"]
                 {{#postgresql?}}
                 [org.postgresql/postgresql "42.2.4"]
                 [ragtime "0.7.2"]
                 {{/postgresql?}}
                 [ring/ring-defaults "0.3.2"]
                 [selmer "1.11.9"]]

  :plugins [[jonase/eastwood "0.2.9"]
            [lein-ancient "0.6.15"]
            [lein-environ "1.1.0"]
            [lein-heroku "0.5.3"]
            {{#sass?}}
            [lein-sass "0.5.0"]
            {{/sass?}}
            [lein-kibit "0.1.6"]]

  :heroku {:app-name "{{ name }}"
           :include-files ["target/uberjar/{{ name }}-standalone.jar"]}

  :test-refresh {:changes-only false
                 :notify-command ["terminal-notifier" "-title" "{{name}} Tests" "-message"]
                 :quiet true}

  :profiles
  {:project-dev
   {:dependencies [[javax.servlet/servlet-api "2.5"]
                   [org.clojure/tools.namespace "0.2.11"]
                   [org.clojure/tools.nrepl "0.2.13"]
                   [pjstadig/humane-test-output "0.8.3"]
                   [prone "1.6.0"]
                   [ring-basic-auth-test "0.1.0"]
                   [ring/ring-mock "0.3.2"]]

    :injections [(require 'pjstadig.humane-test-output)
                 (pjstadig.humane-test-output/activate!)
                 (require 'selmer.parser)
                 (selmer.parser/cache-off!)]

    :repl-options {:init-ns user}

    :ring {:handler {{name}}.app/handler
           :init {{name}}.app/init
           :destroy {{name}}.app/stop
           :nrepl {:start? true}
           :stacktrace-middleware prone.middleware/wrap-exceptions}
    {{#sass?}}

    :sass {:src "resources/sass"
           :output-directory "resources/public/css"}
    {{/sass?}}

    :source-paths ["dev-src"]

    :plugins [[com.jakemccrary/lein-test-refresh "0.23.0"]
              [lein-ring "0.12.4"]]}

   ;; Leave :profiles-dev and :profiles-test blank; their attributes will be
   ;; pulled from profiles.clj, but we need to keep these blank placeholders.
   ;; See the [environ docs][1] for more info.
   ;;
   ;; [1]: https://github.com/weavejester/environ#usage
   :profiles-dev {}
   :profiles-test {}
   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

   :dev [:project-dev :profiles-dev]
   :test [:project-dev :profiles-test]
   :uberjar {:aot :all
             :omit-source true}})
