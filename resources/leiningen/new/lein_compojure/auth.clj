(ns {{name}}.auth
  "Application authentication and authorization via the Buddy library."
  (:require [buddy.auth :refer [authenticated? throw-unauthorized]]
            [buddy.auth.backends :as backends]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
            [environ.core :refer [env]]))

;; HTTP Basic Auth
(def backend (backends/basic {:realm "{{humanized-name}}"
                              :authfn verify-basic-auth}))

(defn verify-basic-auth
  "Verifies basic auth username and password."
  [request {:keys [username password]}]
  (and (not (nil? username))
       (not (nil? password))
       (= (:http-basic-auth-username env) username)
       (= (:http-basic-auth-password env) password)))

(defn wrap-authorization-check
  "Wraps the given handler and throws an error if the request is not
  authorized."
  [handler]
  (fn [request]
    (if (authenticated? request)
      (handler request)
      (throw-unauthorized))))

(defn wrap-auth-middleware
  "Wraps the given handler with Buddy auth middleware."
  [handler]
  (-> handler
      wrap-authorization-check
      (wrap-authorization backend)
      (wrap-authentication backend)))
