(ns sudoku.core-test
  (:require [clojure.test :refer :all]
            [sudoku.core :refer :all]))

(def _ nil)

(deftest solver-feature-test
  (testing "the 4x4 case"
    (let [empty-puzzle [_ _ _ _
                        _ _ _ _
                        _ _ _ _
                        _ _ _ _]
          a-possible-solution [1 2 3 4
                               3 4 1 2
                               4 1 2 3
                               2 3 4 1]
          solutions (solve empty-puzzle)]
      (is (= 288 (count solutions)))
      (is (contains? solutions a-possible-solution)))))
