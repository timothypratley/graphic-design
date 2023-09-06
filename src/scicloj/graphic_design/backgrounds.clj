(ns scicloj.graphic-design.backgrounds
  (:require [scicloj.graphic-design.view :as view]))

(defn on-white [{:keys [width height palette]}]
  [:circle {:fill   (palette 0)
            :r      (/ (min width height) 2)
            :cx     0
            :cy     0
            :stroke "none"}])
(comment (view/icon [on-white]))

(defn clear [_]
  nil)
(comment (view/icon [clear]))

(def all (view/fns))
