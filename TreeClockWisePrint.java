import java.io.*;
import java.util.*;

class TreeNode{
  int val;
  TreeNode left;
  TreeNode right;
  public TreeNode(int val)
  {
    this.val = val;
  }
  public TreeNode(int val, TreeNode left, TreeNode right){
    this.val = val;
    this.left = left;
    this.right = right;
  }
}


class Solution {
  public static void main(String[] args){
    TreeNode root = new TreeNode(1);
    TreeNode three = new TreeNode(3);
    TreeNode two = new TreeNode(2);
    TreeNode four = new TreeNode(4);
    TreeNode five = new TreeNode(5);
    TreeNode six = new TreeNode(6);
    TreeNode sevenR = new TreeNode(7);
    TreeNode sevenL = new TreeNode(7);
    TreeNode eight = new TreeNode(8);
    TreeNode nine = new TreeNode(9);
    TreeNode eleven = new TreeNode(11);
    TreeNode twelve = new TreeNode(12);    
    root.left = three;
    root.right = two;
    three.left = four;
    three.right = five;
    two.left = six;
    two.right = sevenR;
    four.left = sevenL;
    four.right = eight;
    five.left = nine;
    sevenR.left = eleven;
    sevenR.right = twelve;        
    System.out.println(getLeafNodes(root)); 
    System.out.println(leftSide(root));
    System.out.println(rightSide(root));
    System.out.println(clockWiseTree(root));
	//List<List<Integer>> paths of the tree
	System.out.println(findPaths(root));
  }
  //get all leaf in the tree
   private static List<Integer> getLeafNodes(TreeNode root) {
    List<Integer> leaf = new ArrayList<>();//List initial before root null
    
    if (root == null) {
     return leaf; 
    }
    
    if (root.left == null && root.right == null) {
      leaf.add(root.val); 
    }
    
    leaf.addAll(getLeafNodes(root.left));
    leaf.addAll(getLeafNodes(root.right));    
    return leaf;    
  }
  
  
  private static List<Integer> leftSide(TreeNode root) {
   List<Integer> left = new ArrayList<>();    
    if (root == null) {
      return left;
    }    
    left.add(root.val);
    //until there is no left treenode
    if (root.left != null) left.addAll(leftSide(root.left));
    //if (root.left == null) ans.addAll(leftSide(root.right));    
    return left;    
  }
  private static List<Integer> rightSide(TreeNode root){
    List<Integer> right= new ArrayList<>();
    if(root == null){
      return right;
    }
    right.add(root.val);
    if(root.right != null)
      right.addAll(rightSide(root.right));
    return right;
  }
  private static List<Integer> clockWiseTree(TreeNode root){
    List<Integer> clock = new ArrayList<>();
    if(root == null)
      return clock;
    clock.addAll(leftSide(root));
    clock.remove(clock.size()-1);
    clock.addAll(getLeafNodes(root));
    clock.remove(clock.size()-1);
    List<Integer> right= rightSide(root);
    Collections.reverse(right);
    clock.addAll(right);
    clock.remove(clock.size()-1);
    return clock;
  }
  //all of the paths of the tree
private static List<List<Integer>> findPaths(TreeNode node){
    List<List<Integer>> paths = new ArrayList<List<Integer>>();
    if (node == null) return paths;

    List<List<Integer>> left_subtree = findPaths(node.left);
    List<List<Integer>> right_subtree = findPaths(node.right);
     
    for(int i=0;i<left_subtree.size();++i){
        List<Integer> new_path = new ArrayList<Integer>();
        new_path.add(node.val);
        new_path.addAll(left_subtree.get(i));
        paths.add(new_path);
    }

    for(int i=0;i<right_subtree.size();++i){
        List<Integer> new_path = new ArrayList<Integer>();
        new_path.add(node.val);
        new_path.addAll(right_subtree.get(i));
        paths.add(new_path);
    }
    if(paths.size() == 0){
        paths.add(new ArrayList<Integer>());
        paths.get(0).add(node.val);
    }  
    return paths;
}
  
} 
  
  