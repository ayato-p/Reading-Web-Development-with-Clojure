;; (ns picture-gallery.test.handler
;;   (:require [clojure.test :refer :all]
;;             [noir.util.crypt :refer [encrypt]]
;;             [noir.util.middleware :as noir-middleware]
;;             [picture-gallery.handler :refer :all]
;;             [ring.mock.request :refer :all]))

;; (defn mock-get-user [id]
;;   (if (= id "foo")
;;     {:id "foo" :pass (encrypt "12345")}))

;; (with-redefs [picture-gallery.models.db/get-user mock-get-user]
;;   (app (request :post "/login" {:id "foo"})))
