from divide_conquer import MaxFinder

mm = MaxFinder()

a = [1,2,7,4,9,12,5,4]
mm.moveMaxToFront(a,0,len(a)-1)

def calculate_S_recursive(n, j=0, s=1):
    if j == n:
        return 0
    else:
        return s + calculate_S_recursive(n, j+1, s*2)

print(calculate_S_recursive(3))
