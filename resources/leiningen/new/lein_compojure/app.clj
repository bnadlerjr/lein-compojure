(ns {{name}}.app
  "Defines application and how it's started / stopped."
  (:require [logfmt.core :as log]
            [environ.core :refer [env]]
            [org.httpkit.server :as http-kit]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [selmer.parser :as selmer]
            [{{name}}.auth :refer [wrap-auth-middleware]]
            [{{name}}.routes :refer [site-routes]])
  (:gen-class))

;; Ring application defaults. Adds secure session and SSL redirect when in
;; production mode.
(def application-defaults
  (if (not= "production" (env :ring-env "production"))
    site-defaults
    (-> site-defaults
        (assoc :proxy true)
        (assoc-in [:session :cookie-attrs :secure] true)
        (assoc-in [:session :cookie-name] "{{name}}-session")
        (assoc-in [:security :ssl-redirect] true))))

;; Application Ring handler
(def handler
  (-> (wrap-defaults site-routes application-defaults)
      wrap-auth-middleware))

(defn init
  "Initializes application."
  []
  (log/set-dev-mode! (not= "production" (env :ring-env "production")))
  (log/info "Starting {{humanized-name}}" (select-keys env [:ring-env :port])))

(defn stop
  "Stops application and cleans up."
  []
  (log/info "{{humanized-name}} stopped"))

(defn -main
  "Entry point of the application."
  []
  (.addShutdownHook (Runtime/getRuntime) (Thread. stop))
  (init)
  (http-kit/run-server handler {:port (Integer/parseInt (env :port "3000"))}))
