(ns picture-gallery.routes.home
  (:require [compojure.core :refer :all]
            [noir.session :as session]
            [picture-gallery.routes.gallery :refer [show-galleries]]
            [picture-gallery.views.layout :as layout]))

(defn home []
  (layout/common (show-galleries)))

(defroutes home-routes
  (GET "/" [] (home)))
