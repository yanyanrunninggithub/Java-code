//#401. Binary Watch
class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<String>();
        List<Integer> h = new ArrayList<Integer>();
        List<Integer> m = new ArrayList<Integer>();
        if(turnedOn>8)
            return ans;
        for(int i=0;i<=3;++i){
            int j=turnedOn-i;
            if(j>5)
                continue;

            h = getPossible(i, true);
            m = getPossible(j,false);
            for(int k=0;k<h.size();++k){
                if(h.get(k)>11)
                    continue;
                for(int p=0;p<m.size();++p){
                    if(m.get(p)>59)
                        continue;
                    String min = String.valueOf(m.get(p));
                    if(min.length()<2)
                        min = "0" + min;                        
                    ans.add(String.valueOf(h.get(k)) + ":" + min);
                }
            }
        }
        return ans;
    }
    public List<Integer> getPossible(int n, boolean flag){
        List<Integer> res = new ArrayList<Integer>();
        //flag==true, hour mode
        if(flag){
           int[] H = new int[]{1,2,4,8}; 
            helper(H, n, 0, 0, res);//dfs helper
        }
        else{//minute mode
           int[] M = new int[]{1,2,4,8,16,32}; 
            helper(M, n, 0, 0, res);
        }
        return res;
    }
    public void helper(int[] nums,int cnt, int start, int out, List<Integer> res){
        //recursion end condition
        if(cnt==0){
            res.add(out);
            out = 0;
            return;
        }
        for(int i=start;i<nums.length;++i){
            helper(nums,cnt-1,i+1,out+nums[i],res);
        }
    }
}