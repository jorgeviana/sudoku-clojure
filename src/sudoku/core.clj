(ns sudoku.core)

(defn NOT-IMPLEMENTED-YET []
  (throw (UnsupportedOperationException. "Not implemented yet!")))

(def _ nil)

(defn solved? [puzzle]
  (not (some nil? puzzle)))

(defn improve [puzzle]
  (NOT-IMPLEMENTED-YET))

(defn solve [puzzle]
  (loop [maybe-solved puzzle]
    (if (solved? maybe-solved)
      #{maybe-solved}
      (recur (improve maybe-solved)))))
