(ns picture-gallery.routes.gallery
  (:require [compojure.core :refer :all]
            [hiccup.element :refer :all]
            [hiccup.form :refer [check-box]]
            [hiccup.page :refer [include-js]]
            [noir.session :as session]
            [noir.util.anti-forgery :refer [anti-forgery-field]]
            [picture-gallery.models.db :as db]
            [picture-gallery.util :refer [image-uri thumb-uri]]
            [picture-gallery.views.layout :as layout]))

(defn thumbnail-link [{:keys [userid name]}]
  [:div.thumbnail
   (link-to (image-uri userid name)
            (image (thumb-uri userid name)))
   (if (= userid (session/get :user)) (check-box name))])

(defn display-gallery [userid]
  (if-let [gallery (not-empty (map thumbnail-link (db/images-by-user userid)))]
    [:div
     [:div#error]
     gallery
     (if (= userid (session/get :user))
       [:input#delete {:type "submit" :value "delete images"}])]
    [:p "The user " userid " does not have any galleries"]))

(defn gallery-link [{:keys [userid name]}]
  [:div.thumbnail
   (link-to (str "/gallery/" userid)
            (image (thumb-uri userid name))
            userid "'s gallery")])

(defn show-galleries []
  (map gallery-link (db/get-gallery-previews)))

(defroutes gallery-routes
  (GET "/gallery/:userid" [userid]
       (layout/common
        (anti-forgery-field)
        (include-js "/js/gallery.js")
        (display-gallery userid))))
