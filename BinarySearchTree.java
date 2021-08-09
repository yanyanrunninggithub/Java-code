//#938 Range Sum of BST(left<=root<=right)
//binary search tree, return the sum of values of all nodes with a value in the range [low, high].
public int rangeSumBST(TreeNode root, int low, int high) {
	if(root==null)
		return 0;
	int sum = 0;//result is int, initial after the recursion end condition
	if(root.val>=low && root.val<=high)
		sum += root.val;
			
	if(root.val>=low)
		sum += rangeSumBST(root.left,low,high);
	if(root.val<=high)
		sum += rangeSumBST(root.right,low,high);
	return sum;
	
}
//#700. Search in a Binary Search Tree
//Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null
public TreeNode searchBST(TreeNode root, int val) {
	if(root == null)
		return null;
	if(root.val==val)
		return root;
	if(root.val>val)
		return searchBST(root.left,val);
	else
		return searchBST(root.right,val);
	
}
//#108. Convert Sorted Array to Binary Search Tree
//given a sorted array, creat a BST
//binary search solution
public TreeNode sortedArrayToBST(int[] nums) {       
	return buildBST(nums,0,nums.length-1);
}
private TreeNode buildBST(int[] nums, int start, int end){
	if(start>end)
		return null;
	int mid = start + (end-start)/2;
	TreeNode root = new TreeNode(nums[mid]);
	root.left = buildBST(nums,start,mid-1);
	root.right = buildBST(nums,mid+1,end);
	return root;
}
//#235. Lowest Common Ancestor of a Binary Search Tree
 public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	if(p.val<root.val && q.val<root.val)
		return lowestCommonAncestor(root.left, p,q);//if withour return, wrong answer, we should directly return
	if(p.val>root.val && q.val>root.val)
		return lowestCommonAncestor(root.right, p,q);
	return root;//one smaller and one bigger or one is equal to root.val, return root
}
//#501. Find Mode in Binary Search Tree
class Solution {
    int cur=1,max=0;
    public int[] findMode(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        //Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        TreeNode pre = null;
        //int curCnt=0,maxCnt=0;
        inOrder(root,pre,ans);
        int[] modes = new int[ans.size()];
        for(int i=0;i<ans.size();++i){
            modes[i] = ans.get(i);
        }
        return modes;
    }
    public void inOrder(TreeNode root, TreeNode pre,List<Integer> list){
        if(root==null)
            return;
        inOrder(root.left,pre,list);
        if(pre!=null)
            cur = (root.val == pre.val) ? cur+1 : 1;
        if(max<=cur){
            if(max<cur)
                list.clear();
            list.add(root.val);
            max = cur;
        }
        
        pre = root;
        inOrder(root.right,pre,list);
    }
}