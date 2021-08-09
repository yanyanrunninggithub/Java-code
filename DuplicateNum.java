//#645. Set Mismatch
//solution 1: missing num = sum-sum(set), duplicate num = same val with the front
public int[] findErrorNums(int[] nums) {
	Arrays.sort(nums);
	int[] ans = new int[2];
	int sum = 0, cur = 0;
	for(int i=0;i<nums.length;++i){
		sum += i+1;
		cur += nums[i];
		if(i>=1 && nums[i] ==nums[i-1]){
			ans[0] = nums[i];
			cur -= nums[i];
		}
	}
	ans[1] = sum-cur;
	return ans;
}
//solution 2: apply a new cnt array to store the times
//time cost less, but memory usage is bigger
public int[] findErrorNums(int[] nums) {
	int[] cnt = new int[nums.length];
	int[] ans = new int[2];
	for(int i=0;i<nums.length;++i){
		cnt[nums[i]-1]++;
	}
	for(int i=0;i<nums.length;++i){
		if(cnt[i] == 0)
			ans[1] = i+1;
		if(cnt[i] == 2)
			ans[0] = i+1;
	}
	return ans;
}