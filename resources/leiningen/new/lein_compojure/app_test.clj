(ns {{name}}.app-test
  (:require [clojure.test :refer [deftest is testing]]
            [environ.core :refer [env]]
            [ring-basic-auth-test.authorize :as basic-auth]
            [ring.mock.request :as mock]
            [{{name}}.app :refer [handler]]))

(def with-auth
  (basic-auth/authorize (:http-basic-auth-username env)
                        (:http-basic-auth-password env)))

(deftest site-routes-test
  (testing "main route"
    (let [response (handler (with-auth (mock/request :get "/")))]
      (is (= 200 (:status response)))))

  (testing "not-found route"
    (let [response (handler (with-auth (mock/request :get "/invalid")))]
      (is (= 404 (:status response)))))

  (testing "main route when not authenticated"
    (let [response (handler (mock/request :get "/"))]
      (is (= 401 (:status response))))))
