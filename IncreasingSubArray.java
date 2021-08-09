//#674. Longest Continuous Increasing Subsequence
public int findLengthOfLCIS(int[] nums) {
	int len = 1, max = 1;
	if(nums.length==1) return 1;
	for(int i=1;i<nums.length;++i){
		if(nums[i]>nums[i-1]){
			len++;
			max = Math.max(len,max);
		}
		else{//if meet descending or equal, start over count the subsequence, because it must be continuous
			len = 1;
		}
	}
	return max;
}