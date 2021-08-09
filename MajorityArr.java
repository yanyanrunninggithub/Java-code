//#169. Majority Element
//The majority element is the element that appears more than ⌊n / 2⌋ times. 
//solution1: sort return the mean number
public int majorityElement(int[] nums) {
	Arrays.sort(nums);
	return nums[nums.length/2];
}
//solution 2: same cnt++, diff cnt--; majority must be cnt>0
ublic int majorityElement(int[] nums) {
	int cnt =0;
	int ans =-1;
	for(int i=0; i<nums.length;++i){
		if(cnt==0){
			ans = nums[i];
			cnt++;
		}
		else{
			if(ans==nums[i])
				cnt++;
			else
				cnt--;
		}
	}
	return ans;
}
//#1287. Element Appearing More Than 25% In Sorted Array: array is sorted
public int findSpecialInteger(int[] arr) {
	int len = arr.length;        
	for(int i=0; i<len; ++i){
		if(arr[i]==arr[i+len/4])//only check i and i+len/4, because it's a sorted array
			return arr[i];
	}
	return -1;
}
//#1539. Kth Missing Positive Number
//Input: arr = [2,3,4,7,11], k = 5 Output: 9
//Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]
public int findKthPositive(int[] arr, int k) {
	int cnt = 0;
	int i=0,idx=1;
	int miss = 0;
	while(cnt<k){            
		if(i<arr.length && arr[i]==idx){
			i++;
		}
		else{//combine (i<arr.length && arr[i]!=idx) and (i>=arr.length)
			miss = idx;
			cnt++;
		}
		idx++;
	}
	return miss;
}