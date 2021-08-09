//#1 given unordered array, return the 2 number's indexs that add to the target, one number cannot be used twice
public int[] twoSum(int[] nums, int target) {
	int ans[] = {-1,-1};
	if(nums.length<2)
		return  ans;
	//value,index
	Map<Integer,Integer> map = new HashMap<>();
	for(int i=0; i< nums.length; ++i){
		//first step to check map contain k-nums[i] can avoid the situation that using one number twice
		if(map.containsKey(target-nums[i])){
			ans[0] = map.get(target-nums[i]);
			ans[1] = i;
			return ans;
		}
		else
			map.put(nums[i],i);
	}
	ans[0] =-1;
	ans[1] =-1;
	return ans;
}
//#167. Two Sum II - Input array is sorted
//two pointer solution: see IV function
//#653. Two Sum IV - Input is a BST
//solution: get BST's inorder list(sorted), then get the 2 nums
public boolean findTarget(TreeNode root, int k) {
	List<Integer> ans = inorder(root);
	int p =0, q= ans.size()-1;
	while(p<q){
		int sum = ans.get(p) + ans.get(q);
		if(sum == k)
			return true;
		else if(sum<k)
			p++;
		else
			q--;
	}
	return false;
}
public List<Integer> inorder(TreeNode root){
	List<Integer> list = new ArrayList<>();//initial first
	if(root==null)
		return list;
	 if(root.left!=null)
		list.addAll(inorder(root.left));
	list.add(root.val);
	if(root.right !=null)
		list.addAll(inorder(root.right));
	return list;
}
//#15. 3Sum (three num in array sum is zero)
//solution: fix one number and two pointer
public List<List<Integer>> threeSum(int[] nums) {
   List<List<Integer>> res = new ArrayList<List<Integer>>();            
   if(nums.length<3)
		return res;
	Arrays.sort(nums);	
	for(int i=0; i<=nums.length-3;++i){
		if(nums[i]>0)//save time
			break;
		//avoid duplicate start number
		if(i>0 && nums[i-1] == nums[i])
			continue;
		int p=i+1, q=nums.length-1;
		while(p<q){
			int sum = nums[i] + nums[p] + nums[q];
			if(sum<0)
				p++;
			else if(sum>0)
				q--;
			else{
				List<Integer> ans = new ArrayList<>() ;
				ans.add(nums[i]);
				ans.add(nums[p]);
				ans.add(nums[q]);
				res.add(ans);
				//avoid the situation like (-2,0,0,2,2)
				while(p<q && nums[p] == nums[p+1])
					p++;
				while(p<q && nums[q] == nums[q-1])
					q--;
				p++;
				q--;
			}
		}
	}
	return res;
}
//#16. 3Sum Closest (return the 3sum that value is closest to the target)
public int threeSumClosest(int[] nums, int target) {
	Arrays.sort(nums);
	int dif = Integer.MAX_VALUE;
	int ans = 0;
	for(int i=0; i<=nums.length-3;++i){
		int p=i+1, q=nums.length-1;
		while(p<q){
			int sum = nums[i] + nums[p] + nums[q];
			if(sum<target){
				p++;
				if(dif>Math.abs(target-sum)){
					dif = Math.abs(target-sum);
					ans = sum;
				}
			}                    
			else if(sum>target){
				q--;
				if(dif>Math.abs(target-sum)){
					dif = Math.abs(target-sum);
					ans = sum;
				}
			}               
			else{
				return target;
			}
		}
	}
	return ans;
}
//#923. 3Sum With Multiplicity
// arr = [1,1,2,2,3,3,4,4,5,5], target = 8  Output: 20
//(1, 2, 5) occurs 8 times;(1, 3, 4) occurs 8 times;(2, 2, 4) occurs 2 times;(2, 3, 3) occurs 2 times.
 public int threeSumMulti(int[] arr, int target) {
	int ans = 0;
	Arrays.sort(arr);        
	for(int i=0; i<=arr.length-3;++i){
		int p=i+1, q=arr.length-1;
		while(p<q){
			int sum = arr[i] + arr[p] + arr[q];
			if(sum<target)
				p++;
			else if(sum>target)
				q--;
			else{
				int left=1,right=1;
				while (p + left < q && arr[p + left] == arr[p]) ++left;
				while (p + left <= q - right && arr[q - right] == arr[q]) ++right;
				ans += arr[p] == arr[q] ? (q - p + 1) * (q - p) / 2 : left * right;
				p += left;
				q -= right;
			}
		}
	}
	return ans;
}