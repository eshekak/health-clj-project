(ns tests.e2e.index
  (:require
    [cljs.core.async :refer [go]]
    [cljs.core.async.interop :refer-macros [<p!]]))

(def puppeteer (js/require "puppeteer"))

(go
  (let [browser (<p! (.launch puppeteer))
        page (<p! (.newPage browser))]
    (try
      (<p! (.goto page "http://35.185.143.216/"))
      (let [title (<p! (.title page))]
        (assert (= title "Fullstack pet project in ClojureScript!!!")))
      (catch js/Error err (js/console.log err)))
    (.close browser)))
