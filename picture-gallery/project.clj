(defproject picture-gallery "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [lib-noir "0.9.9"]
                 [org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [postgresql/postgresql "9.1-901.jdbc4"]
                 [ring-server "0.3.1"]]
  :plugins [[lein-ring "0.8.12"]]
  :ring {:handler picture-gallery.handler/app
         :init picture-gallery.handler/init
         :destroy picture-gallery.handler/destroy}
  :profiles
  {:uberjar {:aot :all}
   :production
   {:ring
    {:open-browser? true, :stacktraces? true, :auto-reload? true}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.3.1"]]}})
