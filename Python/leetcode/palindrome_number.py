class Solution(object):
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """
        x_str = str(x)
        length = len(x_str)

        for i in range(0, length // 2): 
            if x_str[i] != x_str[length - i - 1]:
                return False

        return True