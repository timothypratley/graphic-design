(ns scicloj.graphic-design.view
  "Helpers for making scicloj styled project icons"
  (:require [scicloj.graphic-design.config :as config]
            [scicloj.kindly.v4.kind :as kind]))

(defn grid [n rows]
  (kind/hiccup
    (into [:div {:style {:display               "grid"
                         :grid-template-columns (str "repeat(" n ", 1fr)")
                         :gap                   10
                         :align-items     "center"
                         :justify-content "center"
                         :text-align "center"}}]
          (for [column rows]
            [:div column]))))

(defn color-card [idx [color description text-color]]
  [:div {:style {:padding         10
                 :backgroundColor color
                 :color           text-color}}
   [:div idx]
   [:div color]
   [:div description]])

(defn fns []
  (-> (ns-interns *ns*)
      (dissoc 'all)
      (update-vals deref)
      (update-keys name)))

(defn deref-var [x]
  (if (var? x)
    @x
    x))

(defn apply-fn [x config]
  (if (fn? x)
    (x config)
    x))

(defn icon
  "Icons consist of components such as background, border, center.
  Components are either SVG hiccup fragments,
  or functions that produce SVG hiccup fragments from configuration."
  ([components]
   (icon components config/*config*))
  ([components config]
   (let [{:keys [width height attrs]} config
         attrs (into {:viewBox [(- (/ width 2)) (- (/ width 2)) width height]
                      :xmlns   "http://www.w3.org/2000/svg"}
                     attrs)]
     (->> (map #(-> % (deref-var) (apply-fn config)) components)
          (remove nil?)
          (into [:svg attrs])
          (kind/hiccup)))))

(def checkerboard-style
  {:background-image    "linear-gradient(45deg, #DDDDDD 25%, transparent 25%),
                         linear-gradient(-45deg, #DDDDDD 25%, transparent 25%),
                         linear-gradient(45deg, transparent 75%, #DDDDDD 75%),
                         linear-gradient(-45deg, transparent 75%, #DDDDDD 75%)"
   :background-size     "20px 20px"
   :background-position " 0 0, 0 10px, 10px -10px, -10px 0px"})

(defn icon-card [[style-name components]]
  (kind/hiccup
    [:div
     [:h4 (name style-name)]
     [:div {:style checkerboard-style}
      (icon components)]]))

(defn component-card [[style-name component]]
  (icon-card [style-name [component]]))
