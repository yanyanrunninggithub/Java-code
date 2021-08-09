//Two sum: Given nums = [2, 7, 11, 15], target = 9,
//Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1]

public static List<int> find2Sum(int[] arr, int target)
{
	HashMap<Int,Int> dic = new HashMap<Int,Int>();
	List<int> res = null;
	for(int i=0;i<arr.Length;i++)
	{	
	//if dulplicate, just store the latest index, because the scan is from the start to end, if meet case like 4=2+2, we scan the first 2 and the dic store the second 2 index, it meets the condition
		dic.put(arr[i], i);
	}
	for(int i=0;i<arr.Length;i++)
	{
		int find = target-arr[i];
		if(dic.containsKey(find) && dic.get(find)!=i)
		{
			res.put(i);
			res.put(dic[find]);
		}
	}
	return res;
}