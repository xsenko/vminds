
def zeroes(i):
    # return -1 if i = 0 or i have only one "1" bit.
    if(i == 0 or (i & (i-1)) == 0):
        return -1

    # find bit length of number
    bit_length = i.bit_length() + 1

    # python allocates 32bit for a int type
    # with padding 0's
    bit_to_find = 1
    previous = 0
    n = 1
    while(n < bit_length):
        previous = previous + 1

        # find rightmost "1"
        if((i & bit_to_find) == bit_to_find):
            bit_to_find = bit_to_find << 1
            break

        # left shift bit_to_find to look for next bit
        bit_to_find = bit_to_find << 1

    max = -10**9
    current = previous
    for j in range(n+1, bit_length):
        current = current + 1

        if((i & bit_to_find) == bit_to_find):
            if(max < (current - previous -1)):
                max = current - previous -1

            previous = current

        bit_to_find = bit_to_find << 1

    return  max

# test with some values
assert 6 == zeroes(517) # 1000000101
assert -1 == zeroes(8) # 1000
assert 4 == zeroes(2577) # 101000010001
assert 3 == zeroes(369) # 101110001
assert 3 == zeroes(273) # 100010001
