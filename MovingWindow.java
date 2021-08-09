//#1652. Defuse the Bomb
//If k > 0, replace the ith number with the sum of the next k numbers.
//If k < 0, replace the ith number with the sum of the previous k numbers.
//If k == 0, replace the ith number with 0.
public int[] decrypt(int[] code, int k) {
	int[] ans = new int[code.length];
	if(k==0)
		return ans;
	int[] num = new int[code.length*2];
	System.arraycopy(code,0,num,0,code.length);
	System.arraycopy(code,0,num,code.length,code.length);
	int sum = 0;
	if(k>0){
	   //start i+1 every time k elements
		//get the 1st element
		for(int i=1; i<k+1; ++i){
			sum += code[i];
		}
		ans[0] = sum;
		for(int i=1; i<code.length; ++i){
			sum = sum-code[i]+num[k+i];
			ans[i] = sum;
		}
	}
	if(k<0){
	   //start i+n-abs(k) = i+n+k
		for(int i=code.length+k; i<code.length; ++i){
			sum += code[i];
		}
		ans[0] = sum;
		for(int i=1; i<code.length; ++i){
			sum = sum+code[i-1]-num[code.length+k+i-1];
			ans[i] = sum;
		}
	}
	return ans;
}
//#1422. Maximum Score After Splitting a String
//Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty substrings,The score after splitting a string is the number of //zeros in the left substring plus the number of ones in the right substring
//Input: s = "011101" Output: 5  "0" and "11101" 
public int maxScore(String s) {
	if(s.length()<2)
		return 0;
	int left0 = (s.charAt(0)=='0') ? 1 : 0;
	int right1 = 0;
	for(int i=1;i<s.length();++i){
		if(s.charAt(i)=='1')
			right1++;
	}
	int max = left0+right1;
	for(int i=1; i<s.length()-1;++i){
		if(s.charAt(i)=='1')
			right1--;
		else
			left0++;
		max = Math.max(max,right1+left0);
	}
	return max;
}
//#228. Summary Ranges
public List<String> summaryRanges(int[] nums) {
	List<String> ans = new ArrayList<String>();
	/*//using start end to update, this solution is faster but code complicated
	int start=0，end=0;
	for(int i=1;i<nums.length;++i){
	   if(nums[i]!=nums[i-1]+1){
			   if(start==end){
				   ans.add(String.valueOf(nums[start]));
			   }
			   else{
				   String s = String.valueOf(nums[start]) + "->" +String.valueOf(nums[end]);
				   ans.add(s);
			   }
			   start = i;
			   end = i;
	   }
		else{
		  end++;  
		}
	}
	if(start==end && end==nums.length-1)
	   ans.add(String.valueOf(nums[start])); 
	else if(start!=end && end==nums.length-1)
		ans.add(String.valueOf(nums[start]) + "->" +String.valueOf(nums[end]));*/
	//solution2: using len to update, this solution is slower but code clearer
	while(start<nums.length){
		int len = 1;
		while(start+len<nums.length && nums[start+len]-nums[start] == len)
			len++;
		if(len==1)
			ans.add(String.valueOf(nums[start]));
		else
			ans.add(String.valueOf(nums[start]) + "->" +String.valueOf(nums[start+len-1]));
		start = start+len;
	}
	return ans;
}
