S = [1,3,6,8,3,2]
x = 14

def findpair(S, x):
    n = len(S) - 1
    i = 0
    for i in range(n):
        j = i+1
        for j in range(n):
            result = S[i] + S[j]
            if result == x:
                return(S[i], S[j])
    print("Es gibt kein Paar")

print(findpair(S, x))