(def ^:private branch-regex #"^(master|feat\/[a-z0-9._-]+)$")


(defn- get-current-branch-name
  []
  (-> (shell/sh "git" "rev-parse" "--abbrev-ref" "HEAD")
      :out
      (str/trim)))


(defn- valid-branch-name?
  []
  (re-matches branch-regex (get-current-branch-name)))


(defn- print-error-msg
  []
  (println (format "Branch names in the project must follow to this contract: %s." branch-regex)))


(when-not (valid-branch-name?)
  (print-error-msg)
  (System/exit 1))
