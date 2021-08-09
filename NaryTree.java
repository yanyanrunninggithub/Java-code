// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
//#589. N-ary Tree Preorder Traversal (very similar, just exchange the place addAll and add
//#590. N-ary Tree Postorder Traversal Input: root = [1,null,3,2,4,null,5,6] Output: [5,6,3,2,4,1] because the children may have many elements so there is no inorder scan
 public List<Integer> postorder(Node root) {
	List<Integer> list = new ArrayList<>();//List initial before root null
	if(root == null)
		return list;
	for(int i=0; i<root.children.size(); ++i){
		list.addAll(postorder(root.children.get(i)));
	}
	list.add(root.val);
	return list;
}
//#559. Maximum Depth of N-ary Tree
 public int maxDepth(Node root) {
	if(root == null) return 0;
	int max = 0;//result integer initial after root null
	for(int i=0;i<root.children.size();++i){
		max = Math.max(max,maxDepth(root.children.get(i)));
	}
	return max+1;
}