(ns {{name}}.db-test
  (:require [clojure.test :refer [deftest is testing use-fixtures]]
            [clojure.java.jdbc :as jdbc]
            [{{name}}.db :refer :all]))

;; Dynamic var used for database transactions.
(declare ^:dynamic *txn*)

(defn setup-db
  "Sets up the database by running any pending migrations."
  [f]
  (println "Checking for pending migrations on" db)
  (migrate)
  (f))

(defn with-rollback
  "Roll back any database changes after execution of a `deftest`."
  [f]
  (jdbc/with-db-transaction [transaction db]
    (jdbc/db-set-rollback-only! transaction)
    (binding [*txn* transaction] (f))))

(use-fixtures :once setup-db)
(use-fixtures :each with-rollback)

;; Example test
;;
;; (deftest create-widget-test
;;   (let [widget (insert-widget *txn* {:name "My Widget" :price 10000})]
;;     (is (= 1 (count (fetch-all-widgets *txn*))))))
