(ns {{name}}.templating
  "Helpers for dealing with Selmer templates."
  (:require [compojure.response :refer [Renderable]]
            [ring.util.anti-forgery :as ring]
            [selmer.filters]
            [selmer.parser]))

;; These filters make it easier to iterate over maps on templates. For example:
;;
;; <dl>
{{=<% %>=}}
;; {% for item in my-map %}
;;     <dt>{{ item|key }}</dt>
;;     <dd>{{ item|val }}</dd>
;; {% endfor %}
<%={{ }}=%>
;; </dl>
(selmer.filters/add-filter! :key key)
(selmer.filters/add-filter! :val val)

;; The CSRF tag should be added to all web forms.
(selmer.parser/add-tag!
  :csrf-tag
  (fn [_ _] (ring/anti-forgery-field)))

;; Custom class that renders a selmer template and automatically associates
;; the request flash with the response.
(deftype SelmerPage
  [status template params]
  Renderable
  (render [_ request]
    {:status status
     :headers {"Content-Type" "text/html; charset=utf-8"}
     :body (->> (assoc params :flash (:flash request))
                (selmer.parser/render-file template))}))

(defn render
  "Renders a selmer template."
  ([template]
   (render-template 200 template {}))
  ([template params]
   (render-template 200 template {}))
  ([status template params]
    (SelmerPage. status template params)))
