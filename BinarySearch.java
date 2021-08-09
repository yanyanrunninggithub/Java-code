//#69. Sqrt(x)
public int mySqrt(int x) {
	/*solution1： straightforward
	int i=1;
	while(i*i<x){
		i++;
	}
	return i*i==x ? i : i-1;*/
	if(x<=1) return x;
	int start=0, end=x;
	while(start<=end) {
		int mid = start + (end-start)/2;
		if(x/mid==mid) 
			return mid;
		else if(x/mid<mid)
			end = mid-1;
		else
			start = mid+1;
	}
	return end;
}
//#744. Find Smallest Letter Greater Than Target
 public char nextGreatestLetter(char[] letters, char target) {
	if(letters[0]>target || letters[letters.length-1]<=target)
		return letters[0];
	int left =0,right=letters.length-1;
	while(left<right){//not <=
		int mid = left + (right-left)/2;
		if(target>=letters[mid-1] && target<letters[mid])
			return letters[mid];
		if(target>=letters[mid])
			left = mid+1;
		else
			right = mid;//not mid-1, because the result maybe includes mid
	}
	return letters[left];//rturn left
}
//#374. Guess Number Higher or Lower
 public int guessNumber(int n) {
	if (guess(n) == 0) return n;
	int left = 1, right = n;
	while (left < right) {//not <=
		int mid = left + (right - left) / 2, t = guess(mid);
		if (t == 0) return mid;
		if (t == 1) left = mid + 1;
		else right = mid-1;//mid-1 or mid both are ok
	}
	return left;//return left
}