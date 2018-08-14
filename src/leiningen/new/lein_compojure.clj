(ns leiningen.new.lein-compojure
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "lein-compojure"))

(defn lein-compojure
  [name]
  (let [data {:name name
              :humanized-name (clojure.string/capitalize name)
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' lein-compojure project.")
    (->files data
             [".gitignore" (render "gitignore" data)]
             ["CHANGELOG.md" (render "CHANGELOG.md" data)]
             ["Procfile" (render "Procfile" data)]
             ["README.md" (render "README.md" data)]
             ["dev-src/user.clj" (render "user.clj" data)]
             ["profiles.clj" (render "profiles.clj" data)]
             ["project.clj" (render "project.clj" data)]
             ["resources/public/.gitkeep" (render "gitkeep" data)]
             ["resources/templates/index.html.selmer" (render "index.html.selmer" data)]
             ["resources/templates/layout.html.selmer" (render "layout.html.selmer" data)]
             ["scripts/run-tmux" (render "run-tmux" data)]
             ["src/{{sanitized}}/env.clj" (render "env.clj" data)]
             ["src/{{sanitized}}/handler.clj" (render "handler.clj" data)]
             ["test/{{sanitized}}/handler_test.clj" (render "handler_test.clj" data)])))
