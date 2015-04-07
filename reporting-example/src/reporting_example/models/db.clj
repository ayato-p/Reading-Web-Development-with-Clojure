(ns reporting-example.models.db
  (:require [clojure.java.jdbc :as sql]))

(def db {:subprotocol "postgresql"
         :subname "//localhost/reporting"
         :user "admin"
         :password "na.13579"})

(defn create-employee-table []
  (sql/db-do-commands db
                      (sql/create-table-ddl
                       :employee
                       [:name "varchar(50)"]
                       [:occupation "varchar(50)"]
                       [:place "varchar(50)"]
                       [:country "varchar(50)"])))

(defn read-employees []
  (sql/query db ["select * from employee"]))

;; (defn add-employees []
;;   (create-employee-table)
;;   (sql/insert! db :employee
;;                [:name, :occupation, :place, :country]
;;                ["Albert Einstein", "Engineer", "Ulm", "Germany"]
;;                ["Alfred Hitchcock", "Movie Director", "London", "UK"]
;;                ["Wernher Von Braun", "Rocket Scientist", "Wyrzysk", "Poland"]
;;                ["Sigmund Freun", "Neurologist", "Pribor", "Czech Republick"]
;;                ["Mahatma Gandhi", "Lawyer", "Gujarat", "India"]
;;                ["Sachin Tendulkar", "Cricket Player", "Mumbai", "India"]
;;                ["Michael Schumacher", "F1 Racer", "Cologne", "Germany"]))
