(ns sudoku.core-test
  (:require [clojure.test :refer :all]
            [sudoku.core :refer :all]))

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
      (is (contains? solutions a-possible-solution))))
  (testing "the 9x9 case"
    (let [unsolved-puzzle [_ _ _ 2 6 _ 7 _ 1
                           6 8 _ _ 7 _ _ 9 _
                           1 9 _ _ _ 4 5 _ _
                           8 2 _ 1 _ _ _ 4 _
                           _ _ 4 6 _ 2 9 _ _
                           _ 5 _ _ _ 3 _ 2 8
                           _ _ 9 3 _ _ _ 7 4
                           _ 4 _ _ 5 _ _ 3 6
                           7 _ 3 _ 1 8 _ _ _]
          solution [4 3 5 2 6 9 7 8 1
                    6 8 2 5 7 1 4 9 3
                    1 9 7 8 3 4 5 6 2
                    8 2 6 1 9 5 3 4 7
                    3 7 4 6 8 2 9 1 5
                    9 5 1 7 4 3 6 2 8
                    5 1 9 3 2 6 8 7 4
                    2 4 8 9 5 7 1 3 6
                    7 6 3 4 1 8 2 5 9]]
      (is (contains? (solve unsolved-puzzle) solution)))))

(deftest solve-test
  (testing "rank 0, 0x0 puzzle"
    (is (= #{[]} (solve []))))
  (testing "rank 1, 1x1 puzzle"
    (is (= #{[1]} (solve [1])))
    (with-redefs [improve (fn [puzzle]
                            (if (= puzzle [_]) #{[1]}))]
      (is (= #{[1]} (solve [_])))))
  (testing "rank 2, 4x4 puzzle"
    (testing "missing one value"
      (with-redefs [improve (fn [puzzle]
                              (if (= puzzle [_ 2 3 4
                                             3 4 1 2
                                             4 1 2 3
                                             2 3 4 1])
                                #{[1 2 3 4
                                   3 4 1 2
                                   4 1 2 3
                                   2 3 4 1]}))]
        (is (= #{[1 2 3 4
                  3 4 1 2
                  4 1 2 3
                  2 3 4 1]} (solve [_ 2 3 4
                                    3 4 1 2
                                    4 1 2 3
                                    2 3 4 1])))))
    (testing "missing two values"
      (with-redefs [improve (fn [puzzle]
                              (cond
                                (= puzzle [_ _ 3 4
                                           3 4 1 2
                                           4 1 2 3
                                           2 3 4 1])
                                #{[1 _ 3 4
                                   3 4 1 2
                                   4 1 2 3
                                   2 3 4 1]}

                                (= puzzle [1 _ 3 4
                                           3 4 1 2
                                           4 1 2 3
                                           2 3 4 1])
                                #{[1 2 3 4
                                   3 4 1 2
                                   4 1 2 3
                                   2 3 4 1]}))]
        (is (= #{[1 2 3 4
                  3 4 1 2
                  4 1 2 3
                  2 3 4 1]} (solve [_ _ 3 4
                                    3 4 1 2
                                    4 1 2 3
                                    2 3 4 1])))))
    (testing "missing three values"
      (with-redefs [improve (fn [puzzle]
                              (cond
                                (= puzzle [_ _ 3 4
                                           3 4 1 2
                                           4 1 2 3
                                           _ 3 4 1])
                                #{[1 _ 3 4
                                   3 4 1 2
                                   4 1 2 3
                                   _ 3 4 1]
                                  [2 _ 3 4
                                   3 4 1 2
                                   4 1 2 3
                                   _ 3 4 1]}

                                (= puzzle [1 _ 3 4
                                           3 4 1 2
                                           4 1 2 3
                                           _ 3 4 1])
                                #{[1 2 3 4
                                   3 4 1 2
                                   4 1 2 3
                                   _ 3 4 1]}

                                (= puzzle [2 _ 3 4
                                           3 4 1 2
                                           4 1 2 3
                                           _ 3 4 1])
                                nil

                                (= puzzle [1 2 3 4
                                           3 4 1 2
                                           4 1 2 3
                                           _ 3 4 1])
                                #{[1 2 3 4
                                   3 4 1 2
                                   4 1 2 3
                                   2 3 4 1]}))]
        (is (= #{[1 2 3 4
                  3 4 1 2
                  4 1 2 3
                  2 3 4 1]} (solve [_ _ 3 4
                                    3 4 1 2
                                    4 1 2 3
                                    _ 3 4 1])))))))
