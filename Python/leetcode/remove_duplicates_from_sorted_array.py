class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """

        errors = []

        for i in range(len(nums)-1): 
            if nums[i] == nums[i+1]:
                errors.append(nums[i])

        for err in errors: 
            nums.append(err)
            nums.remove(err)

        k = 1
        y = 0
        
        while len(nums) > y+1 and nums[y] < nums[y+1]:
            k += 1
            y += 1

        return k



nums = [0,0,1,1,1,2,2,3,3,4]
ob = Solution()
print(ob.removeDuplicates(nums))
