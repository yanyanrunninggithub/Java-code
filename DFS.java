//#690. Employee Importance  <id,importance,subordinates>
//Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1   Output: 11
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
}
class Solution {
    private int sum = 0;
    private Set<Integer> visited = new HashSet<>();
    private Map<Integer, Employee> map = new HashMap<>();

    public int getImportance(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        dfs(map.get(id));
        return sum;
    }

    private void dfs(Employee employee) {
        if (!visited.contains(employee.id)) {
            visited.add(employee.id);
            sum += employee.importance;
            for (Integer id : employee.subordinates) {
                dfs(map.get(id));
            }
        }
    }
}
//#733. Flood Fill: make connected 4-directionally to the starting pixel of the same color to the newColor
//Solution 1: DFS
public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
	int[][] visited = new int[image.length][image[0].length];
	int old = image[sr][sc];//after update image[sr][sc], we will use old to check its four direction
	getFlood(image,sr,sc,newColor,old,visited);
	return image;
}
public void getFlood(int[][] image, int sr, int sc, int newColor,int old, int[][]visited){
	if(visited[sr][sc]!=0)
		return;
	else{
		image[sr][sc]=newColor;
		visited[sr][sc] = 1;
		if(sr-1>=0 && image[sr-1][sc]==old)
			getFlood(image,sr-1,sc,newColor,old,visited);
		if(sr+1<image.length && image[sr+1][sc]==old)
			getFlood(image,sr+1,sc,newColor,old,visited);
		if(sc-1>=0 && image[sr][sc-1]==old)
			getFlood(image,sr,sc-1,newColor,old,visited);
		if(sc+1<image[0].length && image[sr][sc+1]==old)
			getFlood(image,sr,sc+1,newColor,old,visited);           
	}        
}
//solution 2: BFS queue, worse performance than DFS
public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
	int m = image.length, n = image[0].length, oldColor = image[sr][sc];
	Queue<int[]> q = new LinkedList<>();
	int[] idx = {sr,sc};
	q.add(idx);
	int[][] dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};//4 directions
	
	while(q.size()>0){
		for(int i=0;i<q.size();++i){
			int x= q.peek()[0], y = q.peek()[1];
			image[x][y] = newColor;
			q.poll();
			for(int j=0;j<4;++j){
			   int[] newIdx = new int[2];
				newIdx[0] = x+dir[j][0];
				newIdx[1] = y+dir[j][1];
				if(newIdx[0]<0 || newIdx[0]>=m || newIdx[1]<0 || newIdx[1]>=n || image[newIdx[0]][newIdx[1]]!= oldColor)
					continue;
				q.add(newIdx);
			}
			
		}
	}
	return image;
}