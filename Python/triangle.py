def draw_pattern():
    for i in range(1, 7):
        print('x' * i + ' ' * (20 - 2 * i) + 'o' * i)
    for i in range(5, 0, -1):
        print('x' * i + ' ' * (20 - 2 * i) + 'o' * i)

draw_pattern()
