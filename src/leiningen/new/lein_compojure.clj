(ns leiningen.new.lein-compojure
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "lein-compojure"))

(defn includes?
  [target collection]
  (not (nil? (some #{target} collection))))

(defn lein-compojure
  [name & options]
  (let [data {:name name
              :humanized-name (clojure.string/capitalize name)
              :sanitized (name-to-path name)
              :postgresql? (includes? "+postgresql" options)
              :sass? (includes? "+sass" options)}
        base [[".gitignore" (render "gitignore" data)]
              ["CHANGELOG.md" (render "CHANGELOG.md" data)]
              ["Procfile" (render "Procfile" data)]
              ["README.md" (render "README.md" data)]
              ["dev-src/user.clj" (render "user.clj" data)]
              ["profiles.clj" (render "profiles.clj" data)]
              ["project.clj" (render "project.clj" data)]
              ["resources/public/.gitkeep" (render "gitkeep" data)]
              ["resources/templates/index.html.selmer" (render "index.html.selmer" data)]
              ["resources/templates/layout.html.selmer" (render "layout.html.selmer" data)]
              ["scripts/run-tmux" (render "run-tmux" data) :executable true]
              ["src/{{sanitized}}/app.clj" (render "app.clj" data)]
              ["src/{{sanitized}}/auth.clj" (render "auth.clj" data)]
              ["src/{{sanitized}}/routes.clj" (render "routes.clj" data)]
              ["src/{{sanitized}}/templating.clj" (render "templating.clj" data)]
              ["test/{{sanitized}}/app_test.clj" (render "app_test.clj" data)]]
        postgresql [["resources/queries.sql" (render "queries.sql" data)]
                    ["resources/migrations/.gitkeep" (render "gitkeep" data)]
                    ["src/{{sanitized}}/db.clj" (render "db.clj" data)]
                    ["test/{{sanitized}}/db_test.clj" (render "db_test.clj" data)]]
        sass [["resources/public/css/.gitkeep" (render "gitkeep" data)]
              ["resources/sass/styles.scss" (render "styles.scss" data)]]]
    (main/info "Generating fresh Compojure project.")
    (apply ->files data
           (cond-> base
             (:postgresql? data) (concat postgresql)
             (:sass? data) (concat sass)))))
