strs = ["flower", "flow", "flight"]



class Solution(object):
    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        output = strs[0]
        real_output = ""

        for item in strs:
            if item != output:
                length = get_shorter(len(output), len(item))
                for i in range(length):
                    if item[i] == output[i]: 
                        real_output += item[i]
                    elif item[i] != output[i]:                 
                        break
                output = real_output
                real_output =""
                
        return output
                    


def get_shorter(a: int, b: int) -> int: 
    if a < b: 
        return a
    else: 
        return b
    
    

s = Solution()

print("Ausgabe", s.longestCommonPrefix(strs))