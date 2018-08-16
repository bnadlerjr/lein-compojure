{:profiles-dev
 {:env
  {:http-basic-auth-username "{{name}}"
   :http-basic-auth-password "secret"
   :ring-env "development"}}
 :profiles-test
 {:env
  {:http-basic-auth-username "{{name}}"
   :http-basic-auth-password "secret"
   :ring-env "test"}}}
