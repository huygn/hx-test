(ns app
  (:require [hx.react :as hx :include-macros true]
            ["react-dom" :as react-dom]
            [app.core :as core :refer [App]]))

(defn render []
  (react-dom/render (hx/f [App {:name "World"}]) (js/document.getElementById "app")))

(defn ^:export init []
  (render))

(defn reload! []
  (js/console.log "reload")
  (init))
