fibs = 0 : 1 : zipWith (+) fibs (tail fibs)

getFib n = fibs !! n