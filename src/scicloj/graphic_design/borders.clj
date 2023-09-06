(ns scicloj.graphic-design.borders
  (:require [scicloj.graphic-design.view :as view]))

(defn circular [{:keys [palette]}]
  [:g
   [:path {:stroke (palette 1)
           :d      "M 0,-112 A 112 112 0 0 0 0,112"}]
   [:path {:stroke (palette 2)
           :d      "M 0,112 A 112 112 0 0 0 0,-112"}]])
(comment (view/icon [circular]))

(defn squashed [{:keys [palette]}]
  [:g
   [:path {:stroke (palette 1)
           :d      "M -32,-112 Q -112,-112 -112,0 -112,112 -32,112"}]
   [:path {:stroke (palette 2)
           :d      "M 32,112 Q 112,112 112,0 112,-112 32,-112"}]])
(comment (view/icon [squashed]))

(defn tilted [{:keys [palette]}]
  [:g
   [:path {:stroke (palette 1)
           :d      "M -112,0 Q -112,112 0,112"}]
   [:path {:stroke (palette 2)
           :d      "M 112,0 Q 112,-112 0,-112"}]])
(comment (view/icon [tilted]))

(def all (view/fns))
