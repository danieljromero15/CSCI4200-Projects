if __name__ == '__main__':
    j = -3
    key = j + 2

    for i in range(10):
        print(f"i: {i}; j: {j}")
        if (key == 3) or (key == 2):
            j -= 1
        elif key == 0:
            j += 2
        else:
            j = 0
        if j > 0:
            break
        else:
            j = 3 - i

    print(f"j: {j}; key: {key}")