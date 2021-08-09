//#66. Plus One
public int[] plusOne(int[] digits) {
	int len = digits.length;
	if(digits[len-1]<9){
		digits[len-1] +=1;
		return digits;
	}
	int i=len-1;
	while(i>=0 && digits[i]==9)
		i--;
	if(i<0){//all digits are 9
	   int[] ans = new int[len+1];
		ans[0] = 1;
		return ans;
	}
	else{
		digits[i] += 1;
		for(int j=i+1;j<digits.length;++j)
			digits[j] = 0;
	}
	return digits;
}
//#989. Add to Array-Form of Integer
 public List<Integer> addToArrayForm(int[] num, int k) {
	List<Integer> ans = new ArrayList<Integer>();
	int i=num.length-1;
	int heavy = 0;
	while(i>=0 || k!=0){
	   int digit = k%10+heavy;
	   if(i>=0)
		   digit += num[i];
	   heavy = (digit>9) ? 1: 0;
		digit = (digit>9) ? (digit-10) : digit;
		ans.add(digit);
		k = k/10;
		i--;
	}
	if(heavy!=0)
		ans.add(1);
	Collections.reverse(ans);
	return ans;
}