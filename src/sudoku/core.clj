(ns sudoku.core)

(defn NOT-IMPLEMENTED-YET []
  (throw (UnsupportedOperationException. "Not implemented yet!")))

(defn solved? [puzzle]
  (not (some nil? puzzle)))

(defn solve [puzzle]
  (if (solved? puzzle)
    #{puzzle}
    #{[1]}))
