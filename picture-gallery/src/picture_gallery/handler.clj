(ns picture-gallery.handler
  (:require [compojure.core :refer [defroutes]]
            [compojure.route :as route]
            [noir.session :as session]
            [noir.util.middleware :as noir-middleware]
            [picture-gallery.routes.auth :refer [auth-routes]]
            [picture-gallery.routes.gallery :refer [gallery-routes]]
            [picture-gallery.routes.home :refer [home-routes]]
            [picture-gallery.routes.upload :refer [upload-routes]]))

(defn user-page [_]
  (session/get :user))

(defn init []
  (println "picture-gallery is starting"))

(defn destroy []
  (println "picture-gallery is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (noir-middleware/app-handler
   [home-routes
    gallery-routes
    auth-routes
    upload-routes
    app-routes]
   :access-rules [user-page]))
