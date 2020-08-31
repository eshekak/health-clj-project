(ns health-clj-project.server.index
  (:require
    [health-clj-project.libs.nodejs :refer [http]]
    [health-clj-project.server.listener :refer [listener]]))


(defn create-server
  [listener]
  (.createServer http listener))


(.listen (create-server #(listener %1 %2)) 8000)
