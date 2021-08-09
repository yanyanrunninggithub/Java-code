//#747. Largest Number At Least Twice of Others
public int dominantIndex(int[] nums) {
	if(nums.length==1)
		return 0;
	int m1=0, m2=0;
	if(nums[1]>nums[0])
	   m1 = 1;
	else
		m2 = 1;
	
	for(int i=2;i<nums.length;++i){
		if(nums[i]>nums[m1]){
			m2 = m1;
			m1 = i;
		}
		else if(nums[i]>nums[m2])
			m2 = i;
	}
	return (nums[m2]*2<=nums[m1]) ? m1 : -1;
}