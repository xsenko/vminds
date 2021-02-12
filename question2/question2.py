import math

def jumps(a, b, d):
    if a == b:
        return 0
    diff = b - a
    steps = math.ceil(diff / d)
    return steps


assert 3 == jumps(10, 85, 30)
assert 0 == jumps(20, 20, 10)
assert 5 == jumps(0, 100, 21)
assert 3 == jumps(45, 135, 30)