 //Min stair cost: give stairs cost cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1], pay cost[i] you can go up 1 or 2 steps, the start position can be the 1st or the 2nd
 //return get the top stair the min cost
 public static int minCost(int[] cost){
	if(cost.length<=2)
		return 0;
	int[] dp = new int[cost.length];
	dp[0] = 0;
	dp[1]=0;
	for(int i=2; i<cost.length;i++){
		dp[i] = Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]);

	}
	return dp[cost.length-1];
}