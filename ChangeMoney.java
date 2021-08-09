//#860. Lemonade Change
//one time only buy one lemon($5), Input: [5,5,5,10,20] Output: true
//general solution: can suilable to all number money
public boolean lemonadeChange(int[] bills) {
	if(bills.length==0 || bills[0]>5)
		return false;
	//Queue<Integer> cash = new PriorityQueue<>(Collections.reverseOrder()); 
	List<Integer> cash = new ArrayList<>();
	for(int i=0; i<bills.length;++i){
		int change = bills[i]-5;
		if(change == 0){
			cash.add(bills[i]);
			continue;
		}
		if(cash.isEmpty())
			return false;
		else{
			Collections.sort(cash);
			int idx = cash.size()-1;
			while(change>0 && idx>=0){
				if(change>=cash.get(idx)){
					change -= cash.get(idx);
					cash.remove(idx);
					//idx--;
				}
				idx--;
			}
			if(change>0 && idx<0)
				return false;
		}
		cash.add(bills[i]);
	}
	return true;
}
//better solution: only record five and ten cnt, divide the situation to 5,10,20
public boolean lemonadeChange(int[] bills) {
	if(bills.length==0 || bills[0]>5)
		return false;
	int five=0, ten=0;
	for(int i=0; i<bills.length;++i){
		if(bills[i]==5)
			five++;
		else if(bills[i]==10){
			if(five<0)
				return false;
			five--;
			ten++;                
		}
		else{//$20
		   if(ten>0 && five>0){
			   ten--;
			   five--;
		   } 
			else if(five>=3)
				five -= 3;
			else
				return false;
		}
	}
	return true;
}