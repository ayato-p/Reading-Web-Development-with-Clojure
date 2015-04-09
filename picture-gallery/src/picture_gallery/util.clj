(ns picture-gallery.util
  (:import java.io.File)
  (:require [hiccup.util :refer [url-encode]]
            [noir.session :as session]))

(def thumb-size 150)
(def thumb-prefix "thumb_")

(def galleries "galleries")

(defn gallery-path []
  (str galleries File/separator (session/get :user)))
(defn image-uri [userid file-name]
  (str "/img/" userid "/" (url-encode file-name)))

(defn thumb-uri [userid file-name]
  (image-uri userid (str thumb-prefix file-name)))
