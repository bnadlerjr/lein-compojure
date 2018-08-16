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

;; Application Ring handler
(def handler
  (if (not= "production" (env :ring-env "production"))
    (wrap-auth-middleware (wrap-defaults site-routes site-defaults))
    (wrap-auth-middleware
      (wrap-defaults site-routes
                     (-> site-defaults
                         (assoc :proxy true)
                         (assoc-in [:session :cookie-attrs :secure] true)
                         (assoc-in [:session :cookie-name] "{{name}}-session")
                         (assoc-in [:security :ssl-redirect] true))))))

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
