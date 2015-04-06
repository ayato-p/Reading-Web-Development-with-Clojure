(ns guestbook.models.db
  (:require [clojure.java.jdbc :as sql])
  (:import java.sql.DriverManager))


(def db {:classname "org.sqlite.JDBC",
         :subprotocol "sqlite",
         :subname "db.sq3"})

(defn create-guestbook-table []
  (sql/db-do-commands db
                      (sql/create-table-ddl
                       :guestbook
                       [:id "integer primary key autoincrement"]
                       [:timestamp "timestamp default current_timestamp"]
                       [:name "text"]
                       [:message "text"]))
  (sql/db-do-commands db "create index timestamp_index on guestbook (timestamp)"))

(defn read-guests []
  (sql/query db ["select * from guestbook order by timestamp desc"]))

(defn save-message [name message]
  (sql/insert! db :guestbook {:name name :message message :timestamp (java.util.Date.)}))
