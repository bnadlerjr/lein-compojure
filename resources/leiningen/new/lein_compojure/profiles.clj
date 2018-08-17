{:profiles-dev
 {:env
  {:http-basic-auth-username "{{name}}"
   :http-basic-auth-password "secret"
   {{#postgresql?}}
   :database-url "postgresql://localhost:5432/{{name}}-dev"
   {{/postgresql?}}
   :ring-env "development"}}
 :profiles-test
 {:env
  {:http-basic-auth-username "{{name}}"
   :http-basic-auth-password "secret"
   {{#postgresql?}}
   :database-url "postgresql://localhost:5432/{{name}}-test"
   {{/postgresql?}}
   :ring-env "test"}}}
