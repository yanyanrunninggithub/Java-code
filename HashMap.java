//#501. Find Mode in Binary Search Tree
//hashmap solution, another solution without extra space to see BinarySearchTree.java
class Solution {
    public int[] findMode(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        inOrder(root,map);
        int max = 0;
        for(var kvp: map.entrySet()){
			//search the largest cnt
            if(kvp.getValue()>max){
                ans.clear();
                ans.add(kvp.getKey());
                max = kvp.getValue();
            }
            else if(kvp.getValue()==max){
                ans.add(kvp.getKey());
            }
        }
        int[] modes = new int[ans.size()];
        for(int i=0;i<ans.size();++i){
            modes[i] = ans.get(i);
        }
        return modes;
    }
    public void inOrder(TreeNode root, Map<Integer,Integer> map){
        if(root==null)
            return;
        inOrder(root.left,map);
        if(map.containsKey(root.val)){
            map.put(root.val,map.get(root.val)+1);
        }
        else
            map.put(root.val,1);
        inOrder(root.right,map);
    }
}
//#1128. Number of Equivalent Domino Pairs
public int numEquivDominoPairs(int[][] dominoes) {
	/*one testcase failed, time cost high for large size dominoes
	int cnt = 0;
	if(dominoes.length==1) return 0;
	for(int i=0;i<dominoes.length-1;++i){
		for(int j=i+1;j<dominoes.length;++j){
			if((dominoes[i][0]==dominoes[j][0] && dominoes[i][1]==dominoes[j][1]) || 
			   (dominoes[i][1]==dominoes[j][0] && dominoes[i][0]==dominoes[j][1]))
				cnt++;
		}
	}
	return cnt;*/
	//using map, key can be max*10+min, value is cnt
	Map<Integer,Integer> map = new HashMap<Integer,Integer>();
	int ans = 0;//each map value >1, c(2,cnt)
	for(var arr : dominoes){
		int key = Math.max(arr[0],arr[1])*10+Math.min(arr[0],arr[1]);
		if(map.containsKey(key))
			map.put(key,map.get(key)+1);
		else
			map.put(key,1);
	}
	for(var kvp : map.entrySet()){
		int v = kvp.getValue();
		if(v>1)
			ans += (v-1)*v/2;
	}
	return ans;
}