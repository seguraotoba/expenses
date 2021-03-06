(ns expenses.logic.db-helper-test
  (:require [midje.sweet :refer :all]
            [expenses.logic.date-helper :as date-helper]
            [expenses.logic.db-helper :as db-helper]))

(fact "Should add id and created at values to map"
  (db-helper/add-id-and-created-at {:title "Target"
                                    :amount 30}) => {:_id        ..uuid..
                                                     :created-at ..current-date..
                                                     :title      "Target"
                                                     :amount     30}
  (provided
    (db-helper/generate-uuid) => ..uuid..
    (date-helper/current-date) => ..current-date..))

(fact "Should add updated at value to map"
      (db-helper/add-updated-at {:_id        ..uuid..
                                 :created-at ..created-at..
                                 :title      "Target"
                                 :amount     30}) => {:_id        ..uuid..
                                                      :created-at ..created-at..
                                                      :updated-at ..current-date..
                                                      :title      "Target"
                                                      :amount     30}
      (provided
        (date-helper/current-date) => ..current-date..))

(facts "Format search parameters"
  (fact "Should return the same map when does not have any special parameter"
    (db-helper/format-search-parameters {:_id ..uuid..}) => {:_id ..uuid..})

  (fact "Should return the map with active true when active is true"
    (db-helper/format-search-parameters {:_id    ..uuid..
                                         :active true}) => {:_id    ..uuid..
                                                            :active true})

  (fact "Should return the map with active not-equal true when active is false"
    (db-helper/format-search-parameters {:_id    ..uuid..
                                         :active false}) => {:_id    ..uuid..
                                                             :active {"$ne" true}})

  (fact "Should return the map with recurrent true when recurrent is true"
    (db-helper/format-search-parameters {:_id       ..uuid..
                                         :recurrent true}) => {:_id       ..uuid..
                                                               :recurrent true})

  (fact "Should return the map with recurrent not-equal true when recurrent is false"
    (db-helper/format-search-parameters {:_id       ..uuid..
                                         :recurrent false}) => {:_id       ..uuid..
                                                                :recurrent {"$ne" true}})

  (fact "Should return the map with updated-at regex and without period when period has year"
    (db-helper/format-search-parameters {:_id    ..uuid..
                                         :period 2020}) => {:_id        ..uuid..
                                                            :updated-at {"$regex" "2020.*"}})

  (fact "Should return the map with updated-at regex and without period when period has year and month"
    (db-helper/format-search-parameters {:_id    ..uuid..
                                         :period "2020-10"}) => {:_id        ..uuid..
                                                                 :updated-at {"$regex" "2020-10.*"}}))