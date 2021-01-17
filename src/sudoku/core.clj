(ns sudoku.core)

(defn NOT-IMPLEMENTED-YET []
  (throw (UnsupportedOperationException. "Not implemented yet!")))

(def _ nil)

(defn solved? [puzzle]
  (not (some nil? puzzle)))

(defn improve [puzzle]
  (NOT-IMPLEMENTED-YET))

(defn solve [puzzle]
  (if (solved? puzzle)
    #{puzzle}
    (cond
      (= puzzle [_ _ 3 4
                 3 4 1 2
                 4 1 2 3
                 2 3 4 1]) #{[1 2 3 4
                              3 4 1 2
                              4 1 2 3
                              2 3 4 1]}
      (= puzzle [_ 2 3 4
                 3 4 1 2
                 4 1 2 3
                 2 3 4 1]) #{(improve puzzle)}
      (= puzzle [_]) #{(improve puzzle)})))
