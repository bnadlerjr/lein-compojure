(ns {{name}}.db
  (:require [environ.core :refer [env]]
            [hugsql.core :as hugsql]
            [logfmt.core :as log]
            [ragtime.jdbc :as jdbc]
            [ragtime.repl :as repl])
  (:gen-class))

;; Create functions from SQL
(hugsql/def-db-fns "queries.sql")

;; Handle to the DB
(def db (:database-url env))

(defn load-config
  "Loads migration configuration."
  []
  {:datastore (jdbc/sql-database db)
   :migrations (jdbc/load-resources "migrations")})

(defn migrate
  "Migrates the DB."
  []
  (repl/migrate (load-config)))

(defn rollback
  "Rolls back the last migration."
  []
  (repl/rollback (load-config)))

(defn handle-db-exception
  "Handle DB related exceptions, i.e. uniqueness constraint error."
  [exception]
  (let [message (.getMessage exception)]
    ;; TODO:
    ; (when (re-find "some DB specfic message text" message)
      ; (throw (ex-info "description of DB error" {})))
    (throw exception)))

(defn -main
  "Entry point that runs migrations. Used by Heroku."
  []
  (log/info "Running any necessary database migrations")
  (migrate))
