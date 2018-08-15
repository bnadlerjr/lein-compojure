(ns {{name}}.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [environ.core :refer [env]]
            [{{name}}.templating :as template]))

(defroutes site-routes
  (GET "/" []
       (template/render "index.html.selmer"
                        {:env (select-keys env [:ring-env :port])}))

  (route/not-found "Not Found")
  (route/resources "/public"))
