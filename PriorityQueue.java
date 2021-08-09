//#703. Kth Largest Element in a Stream
class KthLargest {
    List<Integer> list = new ArrayList<>();
    int K = 0;
    Queue<Integer> qqq = new PriorityQueue<>(); 
    //int ans1 = 0;
    public KthLargest(int k, int[] nums) {
        K = k;
        //Arrays.sort(nums);
        for(int i=0; i<nums.length;++i){
            qqq.add(nums[i]);
            if(qqq.size()>K)
                qqq.poll();
        }        
    }
    
    public int add(int val) {
        /*list.add(val);
        Collections.sort(list);
        return list.get(list.size()-K);*/
        if(qqq.size()<K)
            qqq.add(val);
        else{
            if(val>qqq.peek()){
                qqq.poll();
                qqq.add(val);
            }
        }
        
        return qqq.peek();
    }
}