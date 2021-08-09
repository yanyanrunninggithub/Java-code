//find array peak elements index: Input: nums = [1,2,1,3,5,6,4]
//Output: [1,5]
public static List<Integer> findPeakElement(int[] arr){
	if(arr.length==0) return null;
	
	List<Integer> index = new ArrayList<Integer>();
	if(arr.length==1) {
		index.add(0);
		return index;
	}
	for(int i = 1; i<arr.length-1;i++){
		if(arr[i]>arr[i-1] && arr[i]>arr[i+1])
			index.add(i);//if arr[1]<arr[0], so arr[0] is a peak, otherwise arr[0] not peak
	}
	//check arr[arr.length-1]
	if(arr[arr.length-1]>arr[arr.length-2])
		index.add(arr.length-1);
	return index;
}
//binary search solution
public int findPeakElement(int[] nums) {
	int left = 0, right = nums.length - 1;
	while (left < right) {
		int mid = left + (right - left) / 2;
		if (nums[mid] < nums[mid + 1]) left = mid + 1;
		else right = mid;
	}
	return right;
}