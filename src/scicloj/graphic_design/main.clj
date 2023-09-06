(ns scicloj.graphic-design.main
  (:require [clojure.java.io :as io]
            [dali.io :as dio]
            [scicloj.clay.v2.api :as clay]
            [scicloj.graphic-design.backgrounds :as bac]
            [scicloj.graphic-design.borders :as bor]
            [scicloj.graphic-design.centers :as cen]))

;; TODO: dali.io requires a different top level flag

(defn render-icon [icon dir filename]
  (doto icon
    (dio/render-svg (doto (io/file dir (str filename ".svg"))
                      (io/make-parents)))
    (dio/render-png (io/file dir (str filename ".png")))))

#_(render-icon (view/icon [bac/on-white bor/tilted cen/kindly])
             "icons" "test")

;;(render-icons config-default "icons")

(defn -main [& args]
  ;;(kindly-default/setup!)
  (clay/start!)
  (doseq [notebook (file-seq (io/file "notebooks"))
          :when (not (.isDirectory notebook))]
    (scicloj.clay.v2.api/generate-and-show-namespace-quarto! notebook {})))

(comment
  (-main))
