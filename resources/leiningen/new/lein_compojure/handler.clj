(ns {{name}}.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [org.httpkit.server :as http-kit]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [selmer.parser :as selmer]
            [{{name}}.env :refer [app-env]])
  (:gen-class))

(selmer/set-resource-path! (clojure.java.io/resource "templates/"))

(defroutes app-routes
  (GET "/" [] (selmer/render-file "index.html.selmer" {:env app-env}))
  (route/not-found "Not Found")
  (route/resources "/public"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main []
  (http-kit/run-server app {:port (app-env :port)}))
