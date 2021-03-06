(ns expenses.db.revenue
  (:require [monger.collection :as mc]
            [monger.operators :refer :all]
            [io.pedestal.log :as log]
            [expenses.logic.db-helper :as db-helper]))

(def revenue-collection "revenue")

(defn create-revenue [revenue db]
  (log/info :msg (str "Creating revenue: " revenue))
  (->> revenue
       db-helper/add-id-and-created-at
       db-helper/add-updated-at
       (mc/insert-and-return db revenue-collection)))

(defn update-revenue [id revenue db]
  (log/info :msg (str "Updating revenue with id " id " with: " revenue))
  (->> revenue
       db-helper/add-updated-at
       (mc/update-by-id db revenue-collection id)))

(defn search-revenue-with [search-parameters db]
  (log/info :msg (str "Searching revenue with parameters: " (db-helper/format-search-parameters search-parameters)))
  (mc/find-maps db revenue-collection (db-helper/format-search-parameters search-parameters)))
