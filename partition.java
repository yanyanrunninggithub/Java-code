//#1013. Partition Array Into Three Parts With Equal Sum
public boolean canThreePartsEqualSum(int[] arr) {
	int sum=0,cnt=0,target=0,cur=0;
	for(int i=0;i<arr.length;++i)
		sum += arr[i];
	if(sum%3!=0)
		return false;
	target = sum/3;//each part sum should be target
	for(int i=0;i<arr.length;++i){
		cur += arr[i];
		if(cur == target){
			cnt++;
			cur = 0;
		}
	}
	return (cnt>=3) ? true : false;//cnt>3 happened when the target is 0
}