(ns {{name}}.handler-test
  (:require [clojure.test :refer [deftest is testing]]
            [ring.mock.request :as mock]
            [{{name}}.handler :refer :all]))

(deftest site-routes-test
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= 200 (:status response) 200))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= 404 (:status response) 404)))))

(deftest api-routes-test
  (testing "get app env"
    (let [response (app (mock/request :get "/api/app-env"))]
      (is (= 200 (:status response)))))

  (testing "POST to echo"
    (let [response (app (-> (mock/request :post "/api/echo")
                            (mock/json-body {:foo "bar"})))]
      (is (= 200 (:status response))))))
