(ns sudoku.core
  (:require [clojure.set :refer :all]))

(defn NOT-IMPLEMENTED-YET []
  (throw (UnsupportedOperationException. "Not implemented yet!")))

(def _ nil)

(defn solved? [puzzle]
  (not (some nil? puzzle)))

(defn improve [puzzle]
  (NOT-IMPLEMENTED-YET))

(defn solve [puzzle]
  (loop [maybe-solved #{puzzle}
         solved #{}]
    (if (empty? maybe-solved)
      solved
      (let [found-to-be-solved (filter solved? maybe-solved)
            not-solved (filter (comp not solved?) maybe-solved)
            improved (map improve not-solved)]
        (recur (reduce union improved)
               (union solved (into #{} found-to-be-solved)))))))
