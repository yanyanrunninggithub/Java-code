//#121. Best Time to Buy and Sell Stock
public int maxProfit(int[] prices) {
	/*O(n*n) time out
	int max = 0;
	for(int i=0; i<prices.length-1;++i){
		for(int j=i+1;j<prices.length;++j){
			max = Math.max(max, prices[j]-prices[i]);
		}
	}
	return max;*/
	//setup min
	int max = 0, min = prices[0];
	for(int i=1; i<prices.length;++i){
		min = Math.min(prices[i],min);
		max = Math.max(prices[i]-min,max);
	}
	return max;
}
//#122. Best Time to Buy and Sell Stock II
//you must sell after buy, you can buy and sell at the same day
//Input: prices = [7,1,5,3,4,6] Output: 7  (buy at 3 sell at 4 then buy 4 sell at 6, same profit with buy 3 sell 6)
public int maxProfit(int[] prices) {
	int max = 0;
	for(int i=1; i<prices.length;++i){
		if(prices[i]>prices[i-1]){
			max += prices[i]-prices[i-1];
			//i++;
		}
	}
	return max;
}
//#309. Best Time to Buy and Sell Stock with Cooldown
//#714. Best Time to Buy and Sell Stock with Transaction Fee
