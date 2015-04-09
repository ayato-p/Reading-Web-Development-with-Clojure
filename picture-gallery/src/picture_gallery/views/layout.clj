(ns picture-gallery.views.layout
  (:require [compojure.response :refer [Renderable]]
            [hiccup.element :refer [link-to]]
            [hiccup.form :refer :all]
            [hiccup.page :refer [html5 include-css include-js]]
            ;; [noir.response :refer [content-type]]
            [noir.session :as session]
            [noir.util.anti-forgery :refer [anti-forgery-field]]
            [ring.util.response :refer [response content-type]]))

(defn utf-8-response [html]
  (content-type (response html) "text/html; charset=utf-8"))

(deftype RenderablePage [content]
  Renderable
  (render [this request]
    (utf-8-response
     (html5
      [:head
       [:title "Welcome to picture-gallery"]
       (include-css "/css/screen.css")
       [:script {:type "text/javascript"}
        (str "var context=\"" (:context request) "\";")]
       (include-js "//code.jquery.com/jquery-2.1.3.min.js")]
      [:body content]))))

(defn base [& content]
  (RenderablePage. content))

(defn make-menu [& items]
  [:div (for [item items] [:div.menuitem item])])

(defn guest-menu []
  (make-menu
   (link-to "/" "home")
   (link-to "/register" "register")
   (form-to [:post "/login"]
            (anti-forgery-field)
            (text-field {:placeholder "screen name"} "id")
            (password-field {:placeholder "password"} "pass")
            (submit-button "login"))))

(defn user-menu [user]
  (make-menu
   (link-to "/" "home")
   (link-to "/upload" "upload images")
   (link-to "/logout" (str "logout" user))
   (link-to "/delete-account" "delete account")))

(defn common [& content]
  (base
   (if-let [user (session/get :user)]
     (user-menu user)
     (guest-menu))
   [:div.content content]))
