(ns health-clj-project.libs.nodejs
  (:require [cljs.nodejs :as nodejs]))

(def fs (nodejs/require "fs"))
(def http (nodejs/require "http"))
(def path (nodejs/require "path"))
