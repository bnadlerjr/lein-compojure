(ns {{name}}.app-test
  (:require [clojure.test :refer [deftest is testing]]
            [ring.mock.request :as mock]
            [{{name}}.app :refer [handler]]))

(deftest site-routes-test
  (testing "main route"
    (let [response (handler (mock/request :get "/"))]
      (is (= 200 (:status response) 200))))

  (testing "not-found route"
    (let [response (handler (mock/request :get "/invalid"))]
      (is (= 404 (:status response) 404)))))
