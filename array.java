//#1588. Sum of All Odd Length Subarrays
/*Input: arr = [1,4,2,5,3]
Output: 58
Explanation: The odd-length subarrays of arr and their sums are:
[1] = 1
[4] = 4
[2] = 2
[5] = 5
[3] = 3
[1,4,2] = 7
[4,2,5] = 11
[2,5,3] = 10
[1,4,2,5,3] = 15
If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58*/
public int sumOddLengthSubarrays(int[] arr) {
	int sum = 0;
	//count all of subarray that start from i, length is j
	for(int i=0; i<arr.length;++i){
		for(int j=i;j<arr.length;j=j+2){
			for(int k=i;k<=j;++k){
				sum+=arr[k];
			}
		}
	}
	return sum;
	
}
//#1748. Sum of Unique Elements
//Input: nums = [1,2,3,2] Output: 4 Explanation: The unique elements are [1,3], and the sum is 4.
//solution1: map 
public int sumOfUnique(int[] nums) {
	//List<Integer> l = new ArrayList<Integer>();
	if(nums.length==0)
		return 0;
	int sum = 0;
	if(nums.length==1)
		return nums[0];
	Map<Integer,Integer> m = new HashMap<Integer,Integer>();
	for(int i=0; i<nums.length; ++i){
		if(!m.containsKey(nums[i]))
			m.put(nums[i],1);
		else
			m.put(nums[i],m.get(nums[i])+1);
	}
	for(var kvp : m.entrySet()){
		if(kvp.getValue() == 1)
			sum+=kvp.getKey();
	}
	return sum;
}
//solution2: order the array
public int sumOfUnique(int[] nums) {
	if(nums.length==0)
		return 0;
	int sum = 0;
	if(nums.length==1)
		return nums[0];
	Arrays.sort(nums);        
	int i = 0;
	while(i<nums.length){
		if(i+1<nums.length && nums[i+1]!=nums[i]){
			sum += nums[i];
			i++;
		}
		else if(i+1<nums.length && nums[i+1]==nums[i]){
			i++;
			while(i<nums.length && nums[i]==nums[i-1])
				i++;
		}
		else if(i==nums.length-1 && nums[i]!=nums[i-1]){
			sum += nums[i];
			i++;
		}
	}			
	return sum;
}
//905. Sort Array By Parity
//Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.
//Input: [3,1,2,4] Output: [2,4,3,1] The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
//quick sort solution:
public int[] sortArrayByParity(int[] A) {
	int p = 0, q = A.length-1;
	while(p<q){
		while(p<A.length && A[p]%2==0)
			p++;
		while(q>=0 && A[q]%2==1)
			q--;
		if(p<q){
			int t = A[p];
			A[p] = A[q];
			A[q] = t;
			p++;
			q--;
		}
	}
	return A;
}
//#1299. Replace Elements with Greatest Element on Right Side
//Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.
//Input: arr = [17,18,5,4,6,1] Output: [18,6,6,6,1,-1]
//solution1: 2 loops
 public int[] replaceElements(int[] arr) {
	int[] res = new int[arr.length];
	for(int i=0; i<arr.length-1; ++i){
		int max = Integer.MIN_VALUE;
		for(int j=i+1; j<arr.length; ++j){
			max = Math.max(max, arr[j]);
		}
		res[i] = max;
	}
	res[arr.length-1] = -1;
	return res;
}
//solution 2: 1 loops: from right to left, update each subarray max value
public int[] replaceElements(int[] arr) {
	int[] res = new int[arr.length];
	res[res.length-1] = -1;
	int max = arr[arr.length-1];
	for(int i=arr.length-2; i>=0; --i){
		res[i] = max;
		max = Math.max(arr[i],max);
	}
	return res;
}
//#349. Intersection of Two Arrays
//Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4] Output: [9,4]
//solution: sort then 2 pointer for 2 arrays
public List<Integer> intersection(int[] nums1, int[] nums2) {
	Arrays.sort(nums1);
	Arrays.sort(nums2);
	List<Integer> ans = new ArrayList<>();
	int p=0,q=0;
	while(p<nums1.length && q<nums2.length){
		if(nums1[p] == nums2[q]){
			if(ans.size()==0 || nums1[p]!=ans.get(ans.size()-1))
				ans.add(nums1[p]);
			p++;
			q++;
		}
		else if(nums1[p]<nums2[q]){
			p++;
		}
		else{
			q++;
		}
	}
	return ans;
}
//1103. Distribute Candies to People
//1st give round:(1,2,...n); 2nd give round(n+1,...2n)...until all of the candies gone
public int[] distributeCandies(int candies, int num_people) {
   int[] ans = new int[num_people];
	int num = 1;
   while(candies>0){
	  if(candies>=num){              
		  ans[(num-1)%num_people]+= num;
	  }
	   else
	   {
		   ans[(num-1)%num_people]+=candies;
	   }
	   candies -= num;
	   num++;
   } 
	return ans;
}
//#985. Sum of Even Numbers After Queries
//queries[i][0] means add value, queries[i][1] means add position
//return after each queries, the even numbers sum of the A
public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
	int[] even = new int[A.length];
	int sum = 0;
	for(int i=0; i<A.length; ++i){
	   if(A[i]%2==0){
		   sum += A[i];
		   even[i] = 1;//this pos is even
	   } 
	}
	int[] ans = new int[queries.length];
	for(int i=0; i<queries.length; ++i){
		int newNum = A[queries[i][1]] + queries[i][0];
		if(newNum%2 == 0){
			if(even[queries[i][1]] == 1){
				//sum contains its original value
				sum += queries[i][0];
			}
			else{//sum doesn't contains its original value
				sum += newNum;
				even[queries[i][1]] = 1;
			}
		}
		else{
			if(even[queries[i][1]] == 1){
				//from even change to odd
				sum -= A[queries[i][1]];
				even[queries[i][1]] = 0;
			}
			//from odd to odd, do nothing
		}
		ans[i] =sum;
		A[queries[i][1]] = newNum;//update the original A
	}
	return ans;
}
//#1331. Rank Transform of an Array
//Input: arr = [40,10,30,20,30] Output: [4,1,3,2,3]
 public int[] arrayRankTransform(int[] arr) {
	int[] sort = Arrays.copyOf(arr,arr.length);
	Arrays.sort(sort);
	//map of (num,rank)
	Map<Integer,Integer> map = new HashMap<>();
	int rank = 1;
	for(int i=0; i<sort.length;++i){
		if(!map.containsKey(sort[i])){
			map.put(sort[i],rank);
			rank++;
		}                
	}
	int[] ans = new int[arr.length];
	for(int i=0; i<sort.length;++i){
		ans[i] = map.get(arr[i]);
	}
	return ans;
 }
 //#448. Find All Numbers Disappeared in an Array
 //solution 1: sort+find next and first diff, don't forget the first position is not 1 situation
 public List<Integer> findDisappearedNumbers(int[] nums) {
	Arrays.sort(nums);
	List<Integer> ans = new ArrayList<>();
	if(nums.length==0)
		return ans;
	if(nums[0]!=1){
		for(int j=1;j<nums[0];++j){
			ans.add(j);
		}
	}
	for(int i=1;i<=nums.length;++i){
		int dif = (i==nums.length) ? (nums.length-nums[nums.length-1]+1) : (nums[i]-nums[i-1]);
		if(dif>1){
			for(int j=1;j<dif;++j){
				ans.add(nums[i-1]+j);
			}
		}
	}
	return ans;	
}
//solution 2:if ont number show, change its position number to be negative, after that, if positive number, mean the array miss its index val 
 public List<Integer> findDisappearedNumbers(int[] nums) {
	List<Integer> ret = new ArrayList<Integer>();        
	for(int i = 0; i < nums.length; i++) {
		int val = Math.abs(nums[i]) - 1;
		if(nums[val] > 0) {
			nums[val] = -nums[val];//change to its negative after do abs no impacton the after numbers
		}
	}        
	for(int i = 0; i < nums.length; i++) {
		if(nums[i] > 0) {
			ret.add(i+1);
		}
	}
	return ret;
}
//#594. Longest Harmonious Subsequence
//solution 1: using map
 public int findLHS(int[] nums) {
	Map<Integer,Integer> map = new TreeMap<>();
	for(int i=0;i<nums.length;++i){
		if(map.containsKey(nums[i]))
			map.put(nums[i],map.get(nums[i])+1);
		else
			map.put(nums[i],1);
	}
	int ans = Integer.MIN_VALUE;
	for(var kvp : map.entrySet()){
		int num = kvp.getKey();
		if(map.containsKey(num+1))
			ans = Math.max(ans,kvp.getValue()+map.get(num+1));
	}
	return (ans==Integer.MIN_VALUE) ? 0 : ans;
 }
 //solution 2: sort+idx distance get the len, time+space cost much better
 public int findLHS(int[] nums) {
	Arrays.sort(nums);
	int  ans=0;
	int start = 0,nextStart = 0;
	for(int i=1;i<nums.length;++i){
		if(nums[i]-nums[start] > 1)
			start = nextStart;
		if(nums[i]!=nums[i-1])
			nextStart = i;
		if(nums[i]-nums[start] == 1){
			ans = Math.max(ans,i-start+1);    
		}      
	}
	return ans;
}
//#1037. Valid Boomerang: point should be distinct but not in the same line
public boolean isBoomerang(int[][] points) {
	int dx1, dx2, dy1, dy2;
	dx1 = points[0][0] - points[1][0];
	dy1 = points[0][1] - points[1][1];
	dx2 = points[1][0] - points[2][0];
	dy2 = points[1][1] - points[2][1];
	//check it's Slope dy2 / dx2 == dy1 / dx1, to avoid divide 0, we check:
	return dx2 * dy1 != dx1 * dy2;
}
//#1566. Detect Pattern of Length M Repeated K or More Times
 public boolean containsPattern(int[] arr, int m, int k) {
	if(m*k>arr.length) return false;
	int i=0;
	int[] sub = new int[m*k];
	while(i+m*k<=arr.length){
		sub = Arrays.copyOfRange(arr,i,i+m*k);
		int[] single = new int[m];
		System.arraycopy(arr,i,single,0,m);
		int[] pattern = new int[m*k];
		for(int j=0;j<k;++j){
			System.arraycopy(single,0,pattern,j*m,m);
		}
		if(Arrays.equals(sub,pattern))
			return true;
		i++;
	}
	return false;
 }
//#1752. Check if Array Is Sorted and Rotated
 public boolean check(int[] nums) {
	if(nums.length==1)
		return true;
	int cnt = 0;
	for(int i=0; i<nums.length-1; ++i){
		if(nums[i]>nums[i+1])
			cnt++;
	}//for len>2 descending array, cnt will be len-1
	if(nums[nums.length-1] > nums[0])
		cnt++;	
	return (cnt>1) ? false : true;
}
//#26. Remove Duplicates from Sorted Array without extra space
 public int removeDuplicates(int[] nums) {
	if(nums.length==0 || nums.length==1)
		return nums.length;
	int len = 0;
	for(int i=0;i<nums.length-1;++i){
		if(nums[i]!=nums[i+1]){//record when the number got changed, so that means record the last one same number
			nums[len] = nums[i];//update the nums at the same time when traverse
			len++;
		}
	}
	nums[len] = nums[nums.length-1];
	len++;
	return len;
}