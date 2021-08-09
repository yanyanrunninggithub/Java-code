//#832. Flipping an Image
//reverse each row and invert each ele
public int[][] flipAndInvertImage(int[][] image) {
	for(int i=0; i<image.length; ++i){
		int n = image[0].length;
		//n-1)/2+1: if n=odd, get n/2+1, if n is even, get n/2
		for(int j=0; j<(n-1)/2+1; ++j){
			int tmp = image[i][j];
			image[i][j] = Math.abs(image[i][n-1-j]-1);
			image[i][n-1-j] = Math.abs(tmp-1);
		}
	}
	return image;
}
//#766. Toeplitz Matrix
//A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements
public boolean isToeplitzMatrix(int[][] matrix) {
	int m = matrix.length;
	int n = matrix[0].length;
	//start node is in first row
	for(int i=0;i<n;++i){
		int key = matrix[0][i];
		for(int j=1; j<m && (i+j)<n; ++j){
			if(matrix[j][i+j] != key)
				return false;
		}
	}
	//start node is in first col
	for(int i=1;i<m;++i){
		int key = matrix[i][0];
		for(int j=1; j<n && (i+j)<m; ++j){
			if(matrix[i+j][j] != key)
				return false;
		}
	}
	return true;
}
//#1582. Special Positions in a Binary Matrix
//count the matrix special position,A position (i,j) is called special if mat[i][j] == 1 and all other elements in row i and column j are 0
public int numSpecial(int[][] mat) {
	int cnt = 0;
	int[] row = new int[mat.length];
	int[] col = new int[mat[0].length];
	for(int i=0; i<mat.length; ++i){
		for(int j=0; j<mat[0].length; ++j){
		   row[i] += mat[i][j];
			col[j] += mat[i][j];
		}
	}
	for(int i=0; i<mat.length; ++i){
		for(int j=0; j<mat[0].length; ++j){
		//when the position is 1, its row and col both 1, means the other elements are 0
		  if((mat[i][j] == 1) && (row[i]==1) && (col[j]==1))
			  cnt++;
		}
	}
	return cnt;
}
//#566. Reshape the Matrix
//e.g m*n to n*m, if cannot return original matrix
public int[][] matrixReshape(int[][] nums, int r, int c) {
	int m = nums.length;
	int n = nums[0].length;
	if(r*c != m*n || (r==m && c==n))
		return nums;
	int[][] ans = new int[r][c];
	int ansI=0,ansJ=0;
	for(int i=0;i<m;++i){
		for(int j=0;j<n;++j){
			if(ansJ==c){
			   ansI++;
				ansJ = 0;
			}
				
			ans[ansI][ansJ] = nums[i][j];
			ansJ++;
		}
	}
	return ans;
}
//#812. Largest Triangle Area
//get each 3 points triangle area = (x2 * y3 + x1 * y2 + x3 * y1 - x3 * y2 - x2 * y1 - x1 * y3)/2
 public double largestTriangleArea(int[][] points) {
	double res = 0;
	for (int i = 0; i < points.length; ++i) {
		for (int j = i + 1; j < points.length; ++j) {
			for (int k = j + 1; k < points.length; ++k) {
				int x1 = points[i][0], y1 = points[i][1];
				int x2 = points[j][0], y2 = points[j][1];
				int x3 = points[k][0], y3 = points[k][1];
				double area = Math.abs(0.5 * (x2 * y3 + x1 * y2 + x3 * y1 - x3 * y2 - x2 * y1 - x1 * y3));
				res = Math.max(res, area);
			}
		}
	}
	return res;
}
//#118. Pascal's Triangle
 public List<List<Integer>> generate(int numRows) {
	List<List<Integer>> ans = new ArrayList<List<Integer>>();
	for(int i=0;i<numRows;++i){
		List<Integer> row = new ArrayList<>();
		int[] rowi = new int[i+1];
		Arrays.fill(rowi,1);
		for(int j=1;j<i;++j){
		   rowi[j] = ans.get(i-1).get(j-1)+ans.get(i-1).get(j); 
		}
		for (int num : rowi){
		   row.add(num);
		}
		ans.add(row);            
	}
	return ans;
}
//#661. Image Smoother
 public int[][] imageSmoother(int[][] M) {
	int m = M.length,n=M[0].length;
	int[][] ans = new int[m][n];
	int[][] dir = new int[][]{{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};//direction array
	for(int i=0;i<m;++i){
		for(int j=0; j<n;++j){
			int sum = M[i][j], cnt = 1;
			for(int k=0;k<dir.length;++k){
				int x = i+dir[k][0], y = j+dir[k][1];
				if(x<0 || x>=m || y<0 || y>=n) continue;
				sum += M[x][y];
				cnt++;
			}
			ans[i][j] = sum/cnt;
		}
	}
	return ans;
}