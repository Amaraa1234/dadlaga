fib2 :: Integer -> Integer
fib2 n = helper n 0 1
  where
    helper 0 a b = a
    helper n a b = helper (n-1) b (a + b)