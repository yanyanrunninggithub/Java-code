/*public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }*/

//#617. Merge Two Binary Trees
//he merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of the newtree.
public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {       
	if(root1 == null){
		return root2;
	}
	if(root2 == null){
		return root1;
	}
	//initial new node after recursion end condition
	TreeNode newTree = new TreeNode();
	newTree.val = root1.val+root2.val;
	newTree.left = mergeTrees(root1.left, root2.left);
	newTree.right = mergeTrees(root1.right, root2.right);
	return newTree;
}
//inOrder scan BT
private static List<Integer> inOrder(TreeNode root) {
	List<Integer> list= new ArrayList<>();
	if(root == null)
		return  list;	
	if(root.left != null){
		list.addAll(inOrder(root.left));
	}	
	list.add(root.val);	
	if(root.right != null) {
	   list.addAll(inOrder(root.right)); 
	}	
	return list;        
}

//#965. Univalued Binary Tree: judge every node val same or not
 public boolean isUnivalTree(TreeNode root) {
	if(root == null)
		return true;
	if(root.left!=null && root.val != root.left.val)
		return false;            
	if(root.right!=null && root.val != root.right.val)
		return false;           
	return isUnivalTree(root.left) && isUnivalTree(root.right);	
}
//#226. Invert Binary Tree
 public TreeNode invertTree(TreeNode root) {
	if(root == null)
		return null;
	//apply tempory space to store left tree
	TreeNode newTmp = root.left;
	root.left = invertTree(root.right);
	root.right = invertTree(newTmp);
	return root;	
}
//#637. Average of Levels in Binary Tree: BFS
public List<Double> averageOfLevels(TreeNode root) {
	if(root == null)
		return null;
	List<Double> ans = new ArrayList<>();
	Queue<TreeNode> level = new LinkedList<TreeNode>();
	//ans.add(Double.valueOf(root.val));
	level.add(root);
	// Traversing level by level  
	while(level.size()>0){
		int sum = 0, cnt = 0;
		Queue<TreeNode> tmp = new LinkedList<TreeNode>();
		while(level.size()>0){
			TreeNode current = level.peek();
			sum += level.peek().val;
			cnt++;
			if(current.left != null)
				tmp.add(current.left);
			if(current.right != null)
				tmp.add(current.right);
			level.poll();//calculate the current node then remove                
		}
		//this level is finish
		ans.add(Double.valueOf(sum)/cnt);
		//move next level node to level queue
		level = tmp;
	}
	return ans;
}
//#606. Construct String from Binary Tree
//"1(2()(4))(3)" if left is null but right not null, use () to occupy the left position
class Solution {
	public String tree2str(TreeNode t) {
		if(t==null)
			return "";
		List<String> ans = preOrder(t);
		String res = "";
		for(int i=0;i<ans.size();++i){
			res += ans.get(i);
		}
		return res.substring(1,res.length()-1);
	}
	public List<String> preOrder(TreeNode t){
		List<String> ans = new ArrayList<>();
		if(t==null){
			return ans;
		}
		ans.add("(");
		ans.add(String.valueOf(t.val));
		if(t.left==null && t.right!=null)//remove this condition is traditional preOrder
			ans.add("()");
		ans.addAll(preOrder(t.left));
		ans.addAll(preOrder(t.right));
		ans.add(")");
		return ans;
	}
}
//#100. Same Tree
class Solution {
     public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null)
            return true;
        if(p==null || q==null)
            return false;
        if(p.val != q.val)
            return false;
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);      
    }
}
//#101. Symmetric Tree
 class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root,root);
    }
    public boolean isMirror(TreeNode r, TreeNode l){
        if(r==null && l==null)
            return true;
        if(r==null || l==null)
            return false;
        if(r.val != l.val)
            return false;
        return isMirror(r.right,l.left) && isMirror(r.left,l.right);
    }
}
//#257. Binary Tree Paths: all paths
public List<String> binaryTreePaths(TreeNode root) {
	List<String> ans = new ArrayList<>();
	getTreePaths(root,"",ans);
	return ans;
}
public void getTreePaths(TreeNode root, String cur, List<String> ans){
	if(root == null)
		return;
	if(root.left==null && root.right==null){
		cur += String.valueOf(root.val);
		ans.add(cur);
		return;
	}
	getTreePaths(root.left, cur+String.valueOf(root.val)+"->",ans);
	getTreePaths(root.right, cur+String.valueOf(root.val)+"->",ans);
}
//to one specific node path: if 2 different nodes have same value the output:[1->3->4->7,1->2->7]
 public static List<String> binaryTreePaths(TreeNode root, int key) {
  List<String> ans = new ArrayList<>();
  getTreePaths(root,"",ans,key);
  return ans;
}
public static void getTreePaths(TreeNode root, String cur, List<String> ans, int key){
  if(root == null)
    return;
  if(root.val==key){
    cur += String.valueOf(root.val);//if we only want ancestors exclude key node, just comment this line
    ans.add(cur);
    return;
  }
  getTreePaths(root.left, cur+String.valueOf(root.val)+"->",ans,key);
  getTreePaths(root.right, cur+String.valueOf(root.val)+"->",ans,key);
}
//#563. Binary Tree Tilt
class Solution {
    public int findTilt(TreeNode root) {
        List<Integer> list = getTilt(root);
        int ans = 0;
        for(int i=0;i<list.size();++i)
            ans+=list.get(i);
        return ans;
    }
    public List<Integer> getTilt(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root==null)
            return ans;
        if(root.left == null && root.right == null)
            ans.add(0);
        if(root.left==null)
            ans.add(Math.abs(getSum(root.right)));
        if(root.right==null)
            ans.add(Math.abs(getSum(root.left)));
        if(root.left != null && root.right != null)
            ans.add(Math.abs(getSum(root.right)-getSum(root.left)));
        ans.addAll(getTilt(root.left));
       ans.addAll(getTilt(root.right));   
        return ans;
    }
    public int getSum(TreeNode root){
        if(root == null)
            return 0;
        int sum = root.val + getSum(root.left)+getSum(root.right);
        return sum;
    }
}
//solution2: not need list
class Solution {
    public int findTilt(TreeNode root) {
        if(root==null)
            return 0;
        int sum = 0;
        if(root.left==null)
            sum += Math.abs(getSum(root.right));
        if(root.right==null)
            sum += Math.abs(getSum(root.left));
        if(root.left != null && root.right != null)
            sum += Math.abs(getSum(root.right)-getSum(root.left));
        sum += findTilt(root.left) + findTilt(root.right);   
        return sum;
    }
    public int getSum(TreeNode root){
        if(root == null)
            return 0;
        int sum = root.val+ getSum(root.left)+getSum(root.right);
        return sum;
    }
}
//#993. Cousins in Binary Tree: different parent but same depth
class Solution {
	//initial depth and sameParent check value as global, because they will change during the function
    int xd = 0, yd=0;
    boolean isSameParent = false;
    public boolean isCousins(TreeNode root, int x,int y){   
    check(root,x,y,0);
    System.out.println(isSameParent);
    return (xd==yd) && !isSameParent;
    }
	public void check(TreeNode root, int x,int y, int curDepth){
    if(root==null)
      return;
    if(root.val==x) xd = curDepth;
    if(root.val==y) yd = curDepth;
    if(root.left !=null && root.right!=null){
      int l = root.left.val;
      int r = root.right.val;
      if((l==x && r==y) || (l==y && r==x)) isSameParent = true;
      check(root.left,x,y,curDepth+1);
      check(root.right,x,y,curDepth+1);
    }   
  }
}
//#404. Sum of Left Leaves
public int sumOfLeftLeaves(TreeNode root) {
	 if(root == null)
		return 0;
	int sum = 0;
	if(root.left!=null && root.left.left==null && root.left.right==null)//left leaf
		sum += root.left.val;
	sum += sumOfLeftLeaves(root.left);
	sum += sumOfLeftLeaves(root.right);
	return sum;	
}
//#543. Diameter of Binary Tree
 public int diameterOfBinaryTree(TreeNode root) {
        if(root==null) return 0;
        int lHeight = height(root.left);
        int rHeight = height(root.right);
        int lDiameter = diameterOfBinaryTree(root.left);
        int rDiameter = diameterOfBinaryTree(root.right);
        return Math.max(1+lHeight+rHeight, Math.max(lDiameter,lDiameter));        
}
//get the height of the binary tree
public int height(TreeNode root){
	if(root==null) return 0;
	return (1+Math.max(height(root.left),height(root.right)));
}
//#111. Minimum Depth of Binary Tree
 public int minDepth(TreeNode root) {
	if(root==null)
		return 0;
	int l=minDepth(root.left);
	int r = minDepth(root.right);
	if(l==0) return r+1;
	if(r==0) return l+1;
	return Math.min(l,r)+1;
}
#671. Second Minimum Node In a Binary Tree
class Solution1 {//using recursion to get the second value
    int first=0;
    int second = Integer.MAX_VALUE;
    public int findSecondMinimumValue(TreeNode root) {
        if(root==null) return -1;
        first=root.val;//set global value,it will not be changed
        helper(root);
        return (first==second || second==Integer.MAX_VALUE) ? -1 : second;
    }
    public void helper(TreeNode root){
        if(root==null) return;
        if(root.val < second && root.val!=first)
            second = root.val;
        helper(root.left);
        helper(root.right);
    }
}
class Solution2 {//using queue to transfer the whole tree
    public int findSecondMinimumValue(TreeNode root) {
        if(root==null) return -1;
        int first=root.val, second = Integer.MAX_VALUE;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode top = q.peek();
            q.poll();
            if(top.val<second && top.val != first)
                second = top.val;
            if(top.left!=null) q.add(top.left);
            if(top.right!=null) q.add(top.right);
        }
        return (first==second || second==Integer.MAX_VALUE) ? -1 : second;
    }
}
//#112. Path Sum
public boolean hasPathSum(TreeNode root, int targetSum) {
	if(root==null)
		return false;
	//recusion end condition: get to the leaf and sum equals target
	if(root.left==null && root.right==null && root.val==targetSum)
		return true;
	return hasPathSum(root.right,targetSum-root.val) || hasPathSum(root.left,targetSum-root.val);
}
//#110. Balanced Binary Tree
 class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root==null) return true;
        if(Math.abs(getDepth(root.left)-getDepth(root.right)) > 1)
            return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }
    public int getDepth(TreeNode root){
        if(root==null)
            return 0;
        return 1+Math.max(getDepth(root.left),getDepth(root.right));
    }
}