(ns scicloj.project-icons
  "SVG helpers for making project icons"
  (:require [clojure.java.io :as io]
            [dali.io :as dio]
            [scicloj.clay.v2.api :as clay]
            [scicloj.kindly.v3.api :as kindly]
            [scicloj.kindly.v3.kind :as kind]
            [scicloj.kindly-default.v1.api :as kindly-default])
  (:import (javax.imageio ImageIO)))

;; # Scicloj style recommendations
;;
;; Scicloj libraries benefit from a common style for their icons and logos.
;; A common style gives a sense of community and composition.
;; This code provides recommendations for creating a common style.
;;
;; ## Icons
;;
;; An Icon or favicon should be square.
;;
;; Consider using:
;;
;; 1. A common border around the edge of icons
;; 2. A unique shape in the center of the icon relevant to the project
;; 3. A common palette of colors based on the Clojure logo
;; 4. A common background strategy
;;
;; ## Logos
;;
;; A logo may be larger than an icon, but should share a similar theme.
;; Prefer the same font as the Clojure website for any text.
;;
;; ## Harmony
;;
;; These recommendations are intended as a starting point to help you be creative.
;; Variations on these themes are encouraged.
;; Our goal is harmony rather than standardization.
;;
;; ## Palette

(def palette-default
  ["#FFFFFF"                                                ; White background
   "#62B132"                                                ; Clojure green dark
   "#5881D8"                                                ; Clojure blue dark
   "#91DC47"                                                ; Clojure green light
   "#8FB5FE"                                                ; Clojure blue light
   ;;"#96CA4B"                                     ; ClojureScript green
   ;;"#5F7FBF"                                     ; ClojureScript blue
   "#F26767"                                                ; coral red
   "#FFCD52"                                                ; golden yellow
   "#A86F40"                                                ; brown
   "#E0E0E0"                                                ; light grey
   "#808080"                                                ; grey
   "#2F4179"                                                ; midnight blue
   ])

(into ^ {:kindly/kind :kind/hiccup}
      [:table]
      (for [row (partition-all 8 palette-default)]
        (into [:tr]
              (for [color row]
                [:td {:style {:backgroundColor color}}
                 color]))))

;; ## Font

(def clojure-font "'Open Sans',sans-serif;")

^{:kindly/kind :kind/hiccup}
[:div {:style {:font-size "3em"
               :font-family (str "font-family: " clojure-font)}}
 clojure-font]

;; ## Size

(def config-default
  {:width       256
   :height      256
   :palette     palette-default
   :font-family clojure-font
   :attrs       {:fill            "none"
                 :stroke-linecap  "round"
                 :stroke-linejoin "round"
                 :stroke-width    32}})

(defn icon [{:keys [width height attrs]} components]
  (into [:dali/page (into {:width   width
                           :height  height
                           :viewBox [(- (/ width 2)) (- (/ width 2)) width height]}
                          attrs)]
        components))

;; ## Backgrounds

(defn backgrounds [{:keys [width height palette]}]
  [["circle"
    [:circle {:fill   (palette 0)
              :r      (/ (min width height) 2)
              :cx     0
              :cy     0
              :stroke "none"}]]
   ["clear"
    [:g]]])

;; ## Borders

(defn borders [{:keys [palette]}]
  [["circular"
    [:g
     [:path {:stroke (palette 1)
             :d      "M 0,-112 A 112 112 0 0 0 0,112"}]
     [:path {:stroke (palette 2)
             :d      "M 0,112 A 112 112 0 0 0 0,-112"}]]]
   ["squashed"
    [:g
     [:path {:stroke (palette 1)
             :d      "M -32,-112 Q -112,-112 -112,0 -112,112 -32,112"}]
     [:path {:stroke (palette 2)
             :d      "M 32,112 Q 112,112 112,0 112,-112 32,-112"}]]]
   ["tilted"
    [:g
     [:path {:stroke (palette 1)
             :d      "M -112,0 Q -112,112 0,112"}]
     [:path {:stroke (palette 2)
             :d      "M 112,0 Q 112,-112 0,-112"}]]]])

;; ## Centers

(defn centers [{:keys [palette width font-family]}]
  [["Clay"
    [:circle {:id   "grey ball of clay"
              :fill (palette 9)
              :r    (/ width 4)
              :cx   0
              :cy   0}]]
   ["Kindly"
    [:g {:id        "heart"
         :transform "translate(-120,-120) scale(10)"}
     [:path {:fill (palette 5)
             :d    "M 12.0 7.2 C 10.5 5.6 8.1 5.2 6.3 6.7 C 4.5 8.1 4.2 10.6 5.7 12.4 L 12.0 18.3 L 18.3 12.4 C 19.7 10.6 19.5 8.1 17.7 6.7 C 15.8 5.2 13.4 5.6 12.0 7.2 Z"}]]]
   ["TMD1"
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
             :height       100}]]]

   ["TMD2"
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
             :height       100}]]]
   ["clj-dlj"
    [:g {:id "DL text"}
     [:text
      {:fill  (palette 10)
       :x     -85
       :y     40
       :style (str "font: 100px sans-serif; font-family: " font-family)}
      "D L"]]]])

;; ## Rendering

(defn render-icon [config dir filename components]
  (doto (icon config components)
    (dio/render-svg (doto (io/file dir (str filename ".svg"))
                      (io/make-parents)))
    (dio/render-png (io/file dir (str filename ".png"))))
  ;; show it in Clay
  (ImageIO/read (io/file dir (str filename ".png"))))

(defn render-components [config dir]
  (doall
    (for [[style-name component] (concat (borders config)
                                         (centers config)
                                         (backgrounds config))]
      (render-icon config dir style-name [component]))))

(defn render-icons [config dir]
  (doall
    (for [[ce-name center] (centers config)
          [bo-name border] (borders config)
          [ba-name background] (backgrounds config)]
      (render-icon config
                   dir
                   (str ce-name "_" bo-name "_" ba-name)
                   [background border center]))))

(render-components config-default "components")
(render-icons config-default "icons")

(defn -main [& args]
  (kindly-default/setup!)
  (clay/start!)
  (scicloj.clay.v2.api/generate-and-show-namespace-quarto!
    "src/scicloj/project_icons.clj"
    {}))

(comment
  (-main))
