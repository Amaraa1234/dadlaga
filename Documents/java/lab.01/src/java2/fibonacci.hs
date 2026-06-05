-- The logic
fib :: Integer -> Integer
fib n = helper 0 1 n
  where
    helper a b 0 = a
    helper a b n = helper b (a + b) (n - 1)

-- The entry point
main :: IO ()
main = do
    putStrLn "Enter a number to calculate its Fibonacci:"
    input <- getLine
    let n = read input :: Integer
    print (fib n)