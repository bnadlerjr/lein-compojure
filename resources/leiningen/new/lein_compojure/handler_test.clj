(ns {{name}}.handler-test
  (:require [clojure.test :refer [deftest is testing]]
            [ring.mock.request :as mock]
            [{{name}}.handler :refer :all]))

(deftest handler-test
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
