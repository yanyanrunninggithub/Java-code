//3sum:a, b, c in S such that a + b + c = 0
//scan from start to end, one element is fixed, using 2 index left right to match -num[i]
//not add two same int[] to the result(some element will be duplicate in arr)
class Solution {
public:
    list<list<int>> threeSum(int[] nums) {
		List<int> r = new List<int>();
        list<list<int>> res = new ist<list<int>>();
        Arrays.Sort(nums);
        if (nums.empty() || nums{nums.length-1] < 0 || nums[0] > 0) return {};
        for (int k = 0; k < nums.length-2; ++k) {
            if (nums[k] > 0) break;
			//limited that not add the same element
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            int target = 0 - nums[k];
            int i = k + 1, j = nums.length - 1;
            while (i < j) {
                if (nums[i] + nums[j] == target) {
                    r.add({nums[k], nums[i], nums[j]});
					res.Add(r);
					//limited that not add the same element
                    while (i < j && nums[i] == nums[i + 1]) ++i;
                    while (i < j && nums[j] == nums[j - 1]) --j;
					//for while go continue
                    ++i; --j;
                } 
				else if (nums[i] + nums[j] < target) ++i;
                else --j;
            }
        }
        return res;
    }
};
//3Sum Closest: 最接近目标的三数和
int threeSumClosest(int[] nums, int target) {
	int offset = Math.max();
	int sum = 0;
	if(nums.length<3) return offset;
	Arrays.sort(nums);
	int closest = 0;	
	int dis = 0;
	for (int k = 0; k < nums.length-2; ++k) 
	{
		int left = k + 1, right = nums.length - 1;
		sum = nums[i]+nums[left]+nums[right];
		if(sum==target)
			return target;
		while(left<right)
		{
			dis = Math.abs(sum-target);
			if(dis<offset)
			{
				offset = dis;
				closest = sum;
			}
			if(left<right && sum<target)
			{
				left++;
			}
			if(left<right && sum>target)
			{
				right--;
			}
		}
	}
	return closest;
}