//#1512 Number of Good Pairs
//Given an array of integers nums.A pair (i,j) is called good if nums[i] == nums[j] and i < j. Return the number of good pairs.
//Input: nums = [1,2,3,1,1,3]Output: 4
//Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
public int numIdenticalPairs(int[] nums) {
	if(nums.length<2)
		return 0;
	int res = 0;
	//<value,list of index>
	Map<Integer,List<Integer>> map = new HashMap<>();
	for(int i=0; i<nums.length; ++i){
		if(map.containsKey(nums[i])){
			map.get(nums[i]).add(i);
		}
		else{
			List<Integer> index = new ArrayList<>();
			index.add(i);
			map.put(nums[i],index);
		}                
	}
	int pair = 0;
	for(Map.Entry<Integer,List<Integer>> kvp : map.entrySet()){
		int n = kvp.getValue().size();
		if(n>1){
			pair = n*(n-1)/2;//C n 2 permutation
			res += pair;
		}
	}
	return res;
}
//only return same element max pairs number
 public static int numIdenticalPairs(int[] nums) {
	if(nums.length<2)
		return 0;
	int res = 0;
	//<value,list of index>
	Map<Integer,Integer> map = new HashMap<>();
	for(int i=0; i<nums.length; ++i){
		if(map.containsKey(nums[i])){
			map.put(nums[i],map.get(nums[i])+1);
		}
		else{
			map.put(nums[i],1);
		}
	}

	//sorted
	//TreeMap<Integer, Integer> sorted = new TreeMap<>(map);
	/*Map<Integer,Integer> result = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));*/
	List<Integer> t = new ArrayList<>(map.values());
	Collections.sort(t);
	int max =  t.get(t.size()-1);
	return max*(max-1)/2;
}