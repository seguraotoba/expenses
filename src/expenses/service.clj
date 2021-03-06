(ns expenses.service
  (:require [expenses.http.http-in :as http-in]
            [expenses.interceptors :as interceptors]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.cors :as cors]))

(defn home-page
  [_]
  {:status 200
   :body   {:name "Expenses Service"}})

(def routes
  `[[["/" {:get home-page}
      ^:interceptors [(body-params/body-params)
                      (cors/allow-origin ["http://localhost:3000"])
                      interceptors/db-interceptor
                      interceptors/to-json-response-interceptor
                      interceptors/service-error-handler]
      ["/expenses"

       ["/purchase"
        {:post http-in/create-purchase}
        ["/:id" {:delete http-in/refund-purchase}]]

       ["/fixed" {:post http-in/create-fixed-expense}
        ["/:id" {:delete http-in/delete-fixed-expense}]]]

      ["/revenue" {:post http-in/create-revenue}
       ["/:id" {:delete http-in/delete-revenue}]]

      ["/monthly" {:get http-in/monthly-data-for-period}
       ["/periods" {:get http-in/monthly-periods}]]
      ["/general" {:get http-in/general-data-for-period}
       ["/periods" {:get http-in/general-periods}]]
      ["/purchases-from-csv" {:post http-in/purchases-from-csv}]]]])
