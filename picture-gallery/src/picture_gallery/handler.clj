(ns picture-gallery.handler
  (:require [compojure.core :refer [defroutes routes]]
            [compojure.route :as route]
            [picture-gallery.routes.home :refer [home-routes]]
            [picture-gallery.routes.auth :refer [auth-routes]]
            [picture-gallery.routes.upload :refer [upload-routes]]
            [noir.util.middleware :as noir-middleware]
            [noir.session :as session]))

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
    auth-routes
    upload-routes
    app-routes]
   :access-rules [user-page]))
