(ns degree9.boot-welcome
  (:require [boot.core :as boot]
            [boot.util :as util]
            [clojure.java.io :as io]
            [clj-figlet.core :as fig]))

(defn resource-font [font]
  (io/resource font))

(defn default-font [font]
  (resource-font (str "degree9/boot_welcome/fonts/" font)))

(defn file-font [font]
  (let [f (io/file font)]
    (when (.exists f)
      f)))

(boot/deftask welcome
  "Display a welcome message with  ascii art."
  [m message str "Message to be displayed."
   f font    str "Font to be used for ASCII art."]
  (let [msg  (:message *opts* "Welcome!")
        font (:font *opts* "banner.flf")
        file (or (file-font font) (resource-font font) (default-font font))]
    (when-not file
      (util/warn "Missing Font...: %s \n" font)
      (util/info msg))
    (when-let [render (fig/load-flf file)]
      (util/info (fig/render-to-string render msg))
      (util/info "\n")))
  identity)
