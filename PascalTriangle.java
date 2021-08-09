//#119. Pascal's Triangle II
public List<Integer> getRow(int rowIndex) {
	List<List<Integer>> res = new ArrayList<List<Integer>>();
	List<Integer> row= new ArrayList<>();
	row.add(1);
	res.add(row);
	if(rowIndex==0){
		return row;
	}         
	if(rowIndex == 1){
		row.add(1);
		return row;
	}
	row.add(1);
	res.add(row);
	for(int i=2; i<=rowIndex; ++i){
		List<Integer> rowi= new ArrayList<>();//initial in the loop, because if initial outside, everytime update row list, res old row will also got updated
		rowi.add(1);
		for(int j=1; j<i;++j){
			rowi.add(res.get(i-1).get(j-1)+res.get(i-1).get(j));
		}
		rowi.add(1);
		res.add(rowi);
	}
	return res.get(rowIndex);
}