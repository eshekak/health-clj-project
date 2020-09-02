(ns tests.e2e.index
  (:require
    [cljs.core.async :refer [go]]
    [cljs.core.async.interop :refer-macros [<p!]]))

(def puppeteer (js/require "puppeteer"))

(defn get-fe-url []
  (if-let [FE_URL (.-FE_URL (.-env js/process))]
    FE_URL
    "http://localhost:8000/"))

(get-fe-url)

(go
  (let [browser (<p! (.launch puppeteer))
        page (<p! (.newPage browser))]
    (try
      (println (get-fe-url))
      (<p! (.goto page (get-fe-url)))
      (let [title (<p! (.title page))]
        (assert (= title "Fullstack pet project in ClojureScript!!!")))
      (catch js/Error err (js/console.log err)))
    (.close browser)))
