//#1232. Check If It Is a Straight Line
//check its slope
public boolean checkStraightLine(int[][] coordinates) {
	int x1 = coordinates[0][0];
	int y1 = coordinates[0][1];
	int x2 = coordinates[1][0];
	int y2 = coordinates[1][1];
	for(int i=2;i<coordinates.length;++i){
		int x = coordinates[i][0];
		int y = coordinates[i][1];
		if((x-x1)*(y2-y1)!=(y-y1)*(x2-x1))
			return false;
	}
	return true;
}
//#836. Rectangle Overlap
 public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
   // x1 < x4 && x3 < x2 && y3 < y2 && y1 < y4
	if(rec1[0]<rec2[2] && rec2[0]<rec1[2] && rec2[1]<rec1[3] && rec1[1]<rec2[3])
		return true;
	return false;
}