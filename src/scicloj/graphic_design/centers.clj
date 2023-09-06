(ns scicloj.graphic-design.centers
  (:require [scicloj.graphic-design.view :as view]))

(defn clay [{:keys [palette width]}]
  [:circle {:id   "gray ball of clay"
            :fill (palette 9)
            :r    (/ width 4.0)
            :cx   0
            :cy   0}])
(comment (view/icon [clay]))

(defn kindly [{:keys [palette]}]
  [:g {:id        "heart"
       :transform "translate(-120,-120) scale(10)"}
   [:path {:fill (palette 5)
           :d    "M 12.0 7.2 C 10.5 5.6 8.1 5.2 6.3 6.7 C 4.5 8.1 4.2 10.6 5.7 12.4 L 12.0 18.3 L 18.3 12.4 C 19.7 10.6 19.5 8.1 17.7 6.7 C 15.8 5.2 13.4 5.6 12.0 7.2 Z"}]])
(comment (view/icon [kindly]))

(defn tmd1 [{:keys [palette]}]
  [:g {:id "grid of data with header"}
   [:rect {:fill         (palette 8)
           :stroke       (palette 10)
           :stroke-width 8
           :x            -75
           :y            -50
           :width        150
           :height       100}]
   [:rect {:stroke       (palette 10)
           :fill         (palette 10)
           :stroke-width 8
           :x            -75
           :y            -50
           :width        150
           :height       25}]
   [:rect {:stroke       (palette 10)
           :stroke-width 8
           :x            -75
           :y            0
           :width        150
           :height       25}]
   [:rect {:stroke       (palette 10)
           :stroke-width 8
           :x            -25
           :y            -50
           :width        50
           :height       100}]])
(comment (view/icon [tmd1]))

(defn tmd2 [{:keys [palette]}]
  [:g {:id "grid of data"}
   [:rect {:fill         (palette 8)
           :stroke       (palette 10)
           :stroke-width 8
           :x            -75
           :y            -50
           :width        150
           :height       100}]
   [:rect {:stroke       (palette 10)
           :stroke-width 8
           :x            -75
           :y            -50
           :width        150
           :height       25}]
   [:rect {:stroke       (palette 10)
           :stroke-width 8
           :x            -75
           :y            0
           :width        150
           :height       25}]
   [:rect {:stroke       (palette 10)
           :stroke-width 8
           :x            -25
           :y            -50
           :width        50
           :height       100}]])
(comment (view/icon [tmd2]))

(defn clj-dlj [{:keys [palette font-family]}]
  [:g {:id "DL text"}
   [:text {:fill  (palette 10)
           :x     -85
           :y     40
           :style {:font        "100px sans-serif"
                   :font-family font-family}}
    "D L"]])
(comment (view/icon [clj-dlj]))

(def all (view/fns))
