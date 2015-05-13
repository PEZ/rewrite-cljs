(ns rewrite-clj.findz-test
  (:require-macros [cemerick.cljs.test
                    :refer (is deftest with-test run-tests testing test-var)])
  (:require [cemerick.cljs.test :as t]
            [rewrite-clj.zip :as z]
            [rewrite-clj.node :as n]
            [rewrite-clj.zip.findz :as f]))



(deftest find-last-by-pos
  (is (= "2" (-> "[1 2 3]"
                 z/of-string
                 (f/find-last-by-pos {:row 1 :col 4} (constantly true))
                 z/string))))

(deftest find-last-by-pos-when-whitespace
  (is (= " " (-> "[1 2 3]"
                 z/of-string
                 (f/find-last-by-pos {:row 1 :col 3} (constantly true))
                 z/string))))


(deftest find-tag-by-pos
  (is (= "[4 5 6]" (-> "[1 2 3 [4 5 6]]"
                       z/of-string
                       (f/find-tag-by-pos {:row 1 :col 8} :vector)
                       z/string))))


(deftest find-tag-by-pos-set
  (is (= "#{4 5 6}" (-> "[1 2 3 #{4 5 6}]"
                       z/of-string
                       (f/find-tag-by-pos {:row 1 :col 10} :set)
                       z/string))))

