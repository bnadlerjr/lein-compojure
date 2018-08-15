(ns {{name}}.app
  "Defines application and how it's started / stopped."
  (:require [environ.core :refer [env]]
            [org.httpkit.server :as http-kit]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [{{name}}.routes :refer [site-routes]])
  (:gen-class))

;; Application Ring handler
(def handler
  (if (not= "production" (env :ring-env "production"))
    (wrap-defaults site-routes site-defaults)
    (wrap-defaults site-routes
                   (-> site-defaults
                       (assoc :proxy true)
                       (assoc-in [:session :cookie-attrs :secure] true)
                       (assoc-in [:session :cookie-name] "{{name}}-session")
                       (assoc-in [:security :ssl-redirect] true)))))

(defn init
  "Initializes application."
  []
  (selmer/set-resource-path! (clojure.java.io/resource "templates/")))

(defn stop
  "Stops application and cleans up."
  [])

(defn -main
  "Entry point of the application."
  []
  (.addShutdownHook (Runtime/getRuntime) (Thread. stop))
  (init)
  (http-kit/run-server handler {:port (Integer/parseInt (env :port "3000"))}))
