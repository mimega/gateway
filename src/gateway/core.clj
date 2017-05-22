(ns gateway.core
  (:gen-class)
  (:use org.httpkit.server
        [clojure.tools.logging :as log]
        [org.httpkit.client :as http]
        [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! timeout]]
        (compojure [core :only [defroutes GET POST]]
                   [handler :only [site]]
                   [route :only [files not-found]])))

(defn- log-text [x]
  ; (log/info x) ; silent mode
  )

(defn- respond-to [channel body]
   (log-text (str "Responding to channel: " channel)  )
   (log-text (str "Body: " body))
   (send! channel {:status 200 :body body}))

; http://localhost:9898/atest?time=1000&response=abc
(defn async-test-fn-good [request]
  (let [params (:params request)
        wait-time (Integer/parseInt (:time params))
        resp (:response params)]
    (with-channel request channel
      (go (<! (timeout wait-time))
          (respond-to channel resp)))))

(defroutes routes
  (GET "/atest" [] async-test-fn-good)
  (not-found "<p>Page not found.</p>" ))

(defn -main [& args]
  (run-server (-> #'routes site)
              {:port 9898})
  (log/info (str "clojure.core.async.pool-size=" (System/getProperty "clojure.core.async.pool-size")))
  (log/info "server started. http://127.0.0.1:9898")
  )
