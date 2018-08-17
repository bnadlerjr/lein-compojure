(ns user
  (:require [clojure.java.javadoc :refer [javadoc]]
            {{#postgresql?}}
            [clojure.java.io :as io]
            {{/postgresql?}}
            [clojure.repl :refer [doc]]
            [clojure.tools.namespace.repl :refer [refresh]])
  {{#postgresql?}}
  (:import [java.util Date TimeZone]
           java.text.SimpleDateFormat){{/postgresql?}})
{{#postgresql?}}

(defn- timestamp
  "Generates a timestamp string that can be used in database migration
  filenames."
  []
  (let [fmt (doto
              (SimpleDateFormat. "yyyyMMddHHmmss")
              (.setTimeZone (TimeZone/getTimeZone "UTC")))]
    (.format fmt (Date.))))

(defn create-migration
  "Creates blank 'up' and 'down' database migration files with the given name."
  [name]
  (let [migration-name (str (timestamp) "-" name)
        migration-dir (clojure.java.io/resource "migrations/")]
    (doseq [file-ext [".up.sql" ".down.sql"]]
      (.createNewFile (io/file migration-dir (str migration-name file-ext))))))
{{/postgresql?}}
