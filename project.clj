(defproject gateway "0.1.0-SNAPSHOT"
  :description "test of long running requests"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-jetty-adapter "1.5.1"]
                 [ring/ring-defaults "0.2.3"]
                 [ring/ring-json "0.4.0"]
                 [http-kit "2.2.0"]
                 ; [org.clojure/data.json "0.2.6"]
                 [org.clojure/core.async "0.3.442"]
                 [org.clojure/tools.logging "0.3.1"]]
  :plugins [[lein-ring "0.11.0"]]
  :main gateway.core
  :profiles
  {:dev {:dependencies [[ring/ring-mock "0.3.0"]]
         :plugin [[venantius/ultra "0.5.1"]]
         :test-refresh {:watch-dirs ["src" "test"]}
         :source-paths   ["dev/src"]
         :resource-paths ["dev/resources"]}
   :uberjar {:aot :all}})
