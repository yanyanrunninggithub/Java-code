//#70. Climbing Stairs
 public int climbStairs(int n) {
	int[] dp = new int[n+1];
	if(n<=0)
		return 0;
	if(n==1)
		return 1;
	dp[0] = 1;
	dp[1] = 1;
	for(int i=2;i<=n;++i)
		dp[i] = dp[i-1]+dp[i-2];
	return dp[n];
}
//#746. Min Cost Climbing Stairs
public int minCostClimbingStairs(int[] cost) {
	int[] dp = new int[cost.length+1];
	dp[1] = 0;
	for(int i=2;i<dp.length;++i){
		dp[i] = Math.min(dp[i-2]+cost[i-2],dp[i-1]+cost[i-1]);
	}
	return dp[dp.length-1];
}