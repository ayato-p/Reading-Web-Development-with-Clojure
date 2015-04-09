(ns picture-gallery.models.db
  (:require [clojure.java.jdbc :as sql]))

(def db
  {:subprotocol "postgresql"
   :subname "//localhost/gallery"
   :user "admin"
   :password "na.13579"})

(defn create-user [user]
  (sql/insert! db :users user))

(defn get-user [id]
  (first (sql/query db ["select * from users where id = ?" id])))

(defn delete-user [userid]
  (sql/delete! db :users ["id = ?" userid]))

(defn add-image [userid name]
  (sql/with-db-transaction [conn db]
    (if (empty? (sql/query
                 conn
                 ["select userid from images where userid = ? and name = ?" userid name]))
      (sql/insert! conn :images {:userid userid :name name}))))

(defn images-by-user [userid]
  (sql/query db ["select * from images where userid = ?" userid]))

(defn get-gallery-previews []
  (sql/query db ["select * from
                    (select *, row_number() over (partition by userid) as row_number from images)
                    as rows where row_number = 1"]))

(defn delete-image [userid name]
  (sql/delete! db :images ["userid = ? and name = ?" userid name]))
