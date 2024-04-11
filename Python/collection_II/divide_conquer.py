class MaxFinder:
    
    def conquerMax(a, l, m, r):
        if l < 0 or m < l or r <= m or len(a) <= r:
            print("Ungültige Eingabe")
            return
        
        if a[l] < a[m + 1]:
            old = a[l]
            a[l] = a[m+1]
            a[m+1] = old

    def moveMaxToFront(a, l, r):
        if l < 0 or len(a) <= r:
            print("Ungültige Eingabe")
            return
      
        if r > l:
            print(array)
            m = l + (r-l)/2
            MaxFinder.moveMaxToFront(a, l, m)
            MaxFinder.moveMaxToFront(a, m+1, r)
            MaxFinder.conquerMax(a,l,m,r)
            return

array = {1,3,4}

MaxFinder.moveMaxToFront(array, 0, 2)
print(array)