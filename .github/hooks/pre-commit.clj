(try (load-file ".github/common-scripts/validate-branch-name.clj")
     (load-file ".github/common-scripts/format-code.clj")
     (load-file ".github/common-scripts/lint-code.clj")
     (load-file ".github/common-scripts/run-units.clj")
     (catch Exception _
       (do
         (println "Error in pre-commit hook!")
         (System/exit 1))))
