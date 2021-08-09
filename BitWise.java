//#1720 decode XORed Array
//It was encoded into another integer array encoded of length n - 1, such that encoded[i] = arr[i] XOR arr[i + 1]. For example, if arr = [1,0,2,1], then encoded = [1,2,3].
//a^b=c--->a^c=b, b^c=a
public int[] decode(int[] encoded, int first) {
	int[] res = new int[encoded.length+1];
	res[0] = first;
	for(int i=1; i<=encoded.length; ++i){
		res[i] = res[i-1] ^ encoded[i-1];
	}
	return res;
}
//#461. Hamming Distance
//Input: x = 1, y = 4 Output: 2  Explanation:
//1   (0 0 0 1) 4   (0 1 0 0) total 2 positions are different
//solution 1: directly compare each position
public int hammingDistance(int x, int y) {
	int dis = 0;
	while(x!=0 || y!=0){
		if((x&1) != (y&1))
			dis++;
		x = x>>1;
		y = y>>1;
	}
	return dis;
}
//solution2: change to bit array to compare
public int hammingDistance(int x, int y) {
	List<Integer> intx = getBit(x);
	List<Integer> inty = getBit(y);
	int xx =0, yy=0, dis = 0;
	for(int i=0; i<Math.max(intx.size(),inty.size()); ++i){
		if(i>=intx.size()) 
			xx = 0;
		else
			xx = intx.get(i);
		
		if(i>=inty.size()) 
			yy = 0;
		else
			yy = inty.get(i);
		 
		if(xx!=yy)
			dis++;
	}
	return dis;
}
private List<Integer> getBit(int n){
	//from low bit to high
	List<Integer> arr = new ArrayList<>();
	while(n!=0){
		arr.add(n&1);
		n=n>>1;
	}
	return arr;
}
//#1009. Complement of Base 10 Integer
//n+complement(n) = all bitwise is 1(pow(2,len)-1)
public int bitwiseComplement(int N) {
	if(N==0)
		return 1;
	int cnt = 0;
	int n = N;
	while(n>0){
		n = n>>1;
		cnt++;
	}
	return (int)Math.pow(2,cnt)-1-N;
}
//#693. Binary Number with Alternating Bits
//if two adjacent bits will always have different values， return true, else false
//n=5 true; n=3 false
public boolean hasAlternatingBits(int n) {
	if(n<2)
		return true;
	int pre = (n&1);
	int cur = 0;
	n = n>>1;
	while(n!=0){
		cur = (n&1);
		if(pre == cur)//check previous and current value
			return false;
		n = n>>1;
		pre = cur;//update pre
	}      
	return true;
}
//#190. Reverse Bits
public int reverseBits(int n) {
	int ans = 0;
	for(int i=0;i<32;++i){
		ans += n&1;
		n = (n>>1);
		ans = (i<31) ? (ans<<1) : ans;
	}
	return ans;
}