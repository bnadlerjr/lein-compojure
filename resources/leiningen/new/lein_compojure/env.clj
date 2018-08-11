(ns {{name}}.env
  (:require [environ.core]))

(def app-env
  {:app-secret (environ.core/env :app-secret)
   :port (Integer/parseInt (environ.core/env :port "3000"))
   :ring-env (environ.core/env :ring-env)})
