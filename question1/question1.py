

def fib(n, computed):
    if n not in computed:
        computed[n] = fib(n - 2, computed) + fib(n - 1, computed)
    return computed[n]


if __name__ == "__main__":
    computed_values = {0:0, 1:1}
    number = 0
    digits = 0

    while(digits < 1000):
        number = number + 1
        result = fib(number, computed_values)
        digits = len(str(result))

    print(f"fib number with 1000 digit is: {number}")




