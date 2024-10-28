def stairs(n):
    out_str = ""
    for i in range(n):
        out_str += '*' * (i + 1) + '\n'

    return out_str


if __name__ == '__main__':
    in_int = int(input("How many stairs? "))
    print(stairs(in_int))
