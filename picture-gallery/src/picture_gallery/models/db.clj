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

(defn add-image [userid name]
  (sql/with-db-transaction [conn db]
    (if (empty? (sql/query
                 conn
                 ["select userid from images where userid = ? and name = ?" userid name]))
      (sql/insert! conn :images {:userid userid :name name}))))
