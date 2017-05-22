(ns gateway.services
  (:require [org.httpkit.client :as http]))

; this is fake ... and yes, it can be generated
(def operation-table
  {:service1 {:url-fn (fn [{:keys [id]}] (str "http://localhost:9901/dostuff?id=" id))
             :timeout 2000}
   :service2 {:url-fn (fn [{:keys [id]}] (str "http://localhost:9902/dostuff?id=" id))
             :timeout 2000}
   :service3 {:url-fn (fn [{:keys [id]}] (str "http://localhost:9903/dostuff?id=" id))
             :timeout 2000}
   :service4 {:url-fn (fn [{:keys [id]}] (str "http://localhost:9904/dostuff?id=" id))
             :timeout 2000}
   :service5 {:url-fn (fn [{:keys [id]}] (str "http://localhost:9905/dostuff?id=" id))
             :timeout 2000}
   :service6 {:url-fn (fn [{:keys [id]}] (str "http://localhost:9906/dostuff?id=" id))
             :timeout 2000}
   :service7 {:url-fn (fn [{:keys [id]}] (str "http://localhost:9907/dostuff?id=" id))
             :timeout 2000}
   :service8 {:url-fn (fn [{:keys [id]}] (str "http://localhost:9908/dostuff?id=" id))
             :timeout 2000}
   :service9 {:url-fn (fn [{:keys [id]}] (str "http://localhost:9909/dostuff?id=" id))
             :timeout 2000}
   :service10 {:url-fn (fn [{:keys [id]}] (str "http://localhost:9910/dostuff?id=" id))
             :timeout 2000}
})

(def operations (into [] (keys operation-table)))

(defn- perform-call [config inputs]
  (http/get ((:url-fn config) inputs) {:timeout (:timeout config)}
          (fn [{:keys [status headers body error]}] ;; asynchronous response handling
            (if error ; like rspec - dot vs F
              (do (print "F") (flush))
              (do (print ".") (flush))))))

(defn call-number [nr]
  (perform-call (:check-number operation-table) {:number nr}))

(defn make-random-call []
  (-> operations
      (get (rand-int 4))
      (operation-table)
      (perform-call 123)))

