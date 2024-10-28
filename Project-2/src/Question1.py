def q1(k):
    j = 0

    if (k == 1) or (k == 2):
        j = 2 * k - 1
    elif (k == 3) or (k == 5):
        j = 3 * k + 1
    elif k == 4:
        j = 4 * k - 1
    elif (k == 6) or (k == 7) or (k == 8):
        j = k - 2

    return f"In: {k}; Out: {j}"

if __name__ == '__main__':
    for i in range(10):
        print(q1(i))