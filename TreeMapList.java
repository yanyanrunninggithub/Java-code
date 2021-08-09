//#1337. The K Weakest Rows in a Matrix
class Solution {
	public int[] kWeakestRows(int[][] mat, int k) {
		int[] res = new int[k];
		TreeMap<Integer,List<Integer>> map = new TreeMap<>();
		for(int i=0; i<mat.length; ++i){
			int cnt1 = 0;
			
			for(int j=0;j<mat[0].length; ++j)
			{
			  if(mat[i][j]==1)
				  cnt1++;
			  else
				  break;
			} 
			if(map.containsKey(cnt1))
			{
				List<Integer> row = map.get(cnt1);
				row.add(i);
				map.put(cnt1,row);
			}
			else{
				List<Integer> row = new ArrayList<>();
				row.add(i);
				map.put(cnt1,row);
			}
		}
		List<Integer> rowIdx = new ArrayList<>();
		for(var kvp : map.entrySet()){
			rowIdx.addAll(kvp.getValue());
		}
		for(int i=0; i<k; ++i){
			res[i] = rowIdx.get(i);
		}

		return res;
	}
}