class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        for a in range(0, len(nums)):
            for b in range(1, len(nums)):
                number = nums[a] + nums[b]
                if target == number and a != b:
                    return a,b

        