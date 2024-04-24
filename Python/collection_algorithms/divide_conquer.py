import numpy as np
import math

class MaxFinder:
    def conquerMax(self, a, l, m, r):
        if not (0 <= l <= m < r < len(a)):
            raise ValueError("Ungültige Eingabe")
        if(a[m+1] > a[l]):
            v = a[l]
            a[l] = a[m+1]
            a[m+1] = v

    def moveMaxToFront(self, a, l, r):
        if r > l:
            m = (l + r) // 2
            self.moveMaxToFront(a, l, m)
            self.moveMaxToFront(a, m+1, r)
            self.conquerMax(a, l, m, r)
        print(a)
