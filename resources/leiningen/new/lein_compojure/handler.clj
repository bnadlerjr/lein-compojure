(ns {{name}}.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [org.httpkit.server :as http-kit]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults site-defaults]]
            [selmer.parser :as selmer]
            [{{name}}.env :refer [app-env]])
  (:gen-class))

(selmer/set-resource-path! (clojure.java.io/resource "templates/"))

(defroutes site-routes
  (GET "/" []
       (selmer/render-file "index.html.selmer" {:env app-env}))
  (route/not-found "Not Found")
  (route/resources "/public"))

(defroutes api-routes
  (context "/api" []
    (GET "/app-env" []
         {:status 200
          :headers {}
          :body (str app-env)})
    (POST "/echo" req
          {:status 200
           :headers {}
           :body (str req)})
    (route/not-found
      {:status 404
       :headers {}
       :body nil})))

(def site
  (wrap-defaults site-routes site-defaults))

(def api
  (wrap-defaults api-routes api-defaults))

(def app
  (fn [request]
    (if (clojure.string/starts-with? (:uri request) "/api")
      (api request)
      (site request))))

(defn -main []
  (http-kit/run-server app {:port (app-env :port)}))
