(ns health-clj-project.server.listener
  (:require [health-clj-project.server.main-page :refer [main-page]]
            [health-clj-project.libs.nodejs :refer [fs path]]
            [goog.object :as go]))

(def __dirname (js* "__dirname"))
(def stringify-err #(.stringify js/JSON %1))

(defn send-error [err res]
  (do
    (.writeHead res 404)
    (.end res (stringify-err err))))

(defn send-content [content res]
  (do
    (.writeHead res 200)
    (.end res content)))

(defn get-url [req] (go/get req "url"))
(defn calc-asset-path [req-url] (.resolve path __dirname "./../../" (str "." req-url)))
(defn serve-assets [req res]
  (.readFile fs (calc-asset-path (get-url req))
             #(if %1
                (send-error %1 res)
                (send-content %2 res))))


(defmulti listener (fn [req] (get-url req)))
(defmethod listener "/" [_ res] (do
                                    (.setHeader res "Content-Type" "text/html")
                                    (send-content main-page res)))
(defmethod listener :default [req res] (serve-assets req res))
