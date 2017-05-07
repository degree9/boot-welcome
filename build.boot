(set-env!
 :dependencies  '[[org.clojure/clojure     "1.8.0"]
                  [boot/core               "2.7.1"]
                  [degree9/boot-semver     "1.5.0"]
                  [clj-figlet              "0.1.1"]]
 :resource-paths   #{"src" "resources"})

(require
 '[degree9.boot-semver :refer :all]
 '[degree9.boot-welcome :refer :all])

(task-options!
 pom {:project 'degree9/boot-welcome
      :description "Display a welcome message in boot-clj."
      :url         "https://github.com/degree9/boot-welcome"
      :scm {:url "https://github.com/degree9/boot-welcome"}}
 target {:dir #{"target"}})

(deftask develop
  "Build boot-welcome for development."
  []
  (comp
   (version :develop true
            :minor 'inc
            :patch 'zero
            :pre-release 'snapshot)
   (watch)
   (target)
   (build-jar)))

(deftask deploy
  "Build boot-welcome and deploy to clojars."
  []
  (comp
   (version)
   (target)
   (build-jar)
   (push-release)))
