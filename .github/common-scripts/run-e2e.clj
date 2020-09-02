(shell/sh "clj" "-m" "cljs.main" "-t" "node" "-d" "./tests" "-o" "./tests/e2e/index.js" "-c" "tests.e2e.index")

(let [{:keys [:exit :err :out]} (shell/sh "node" "./tests/e2e/index.js")]
  (if (zero? exit) out
    (do (println err)
        (System/exit 1))))
