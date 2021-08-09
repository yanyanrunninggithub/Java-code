//#1496. Path Crossing
//Input: path = "NESWW" Output: true
public boolean isPathCrossing(String path) {
	List<List<Integer>> pos = new ArrayList<List<Integer>>();
	List<Integer> id = new ArrayList<>() ;
	id.add(0);
	id.add(0);
	pos.add(id);
	int x=0,y=0;
	for(int i=0;i<path.length();++i){
		List<Integer> xy = new ArrayList<>();
		if(path.charAt(i)=='N')
			y++;
		else if(path.charAt(i)=='S')
			y--;
		else if(path.charAt(i)=='E')
			x++;
		else
			x--;
		xy.add(x);
		xy.add(y);
		if(pos.contains(xy))
			return true;
		pos.add(xy);
	}
	return false;        
}