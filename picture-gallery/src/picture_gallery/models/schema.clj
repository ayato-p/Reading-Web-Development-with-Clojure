(ns picture-gallery.models.schema
  (:require [picture-gallery.models.db :refer :all]
            [clojure.java.jdbc :as sql]))

(defn create-users-table []
  (sql/db-do-commands db
                      (sql/create-table-ddl
                       :users
                       [:id "varchar(32) primary key"]
                       [:pass "varchar(100)"])))

(defn create-images-table []
  (sql/db-do-commands db
                      (sql/create-table-ddl
                       :images
                       [:userid "varchar(32)"]
                       [:name "varchar(100)"])))
