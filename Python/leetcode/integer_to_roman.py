class Solution(object):
    def romanToInt(self, s):
        """
        :type s: str
        :rtype: int
        """
        num = 0
        i = 0

        roman_map = {
            "I": 1, 
            "V": 5,
            "X": 10, 
            "L": 50, 
            "C": 100, 
            "D": 500,
            "M": 1000
        }

        while i < len(s): 
            if i + 1 < len(s) and roman_map[s[i]] < roman_map[s[i+1]]:
                num += roman_map[s[i+1]] - roman_map[s[i]]
                i += 2
            else: 
                num += roman_map[s[i]]
                i += 1

        return num
