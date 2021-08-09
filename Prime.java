//#204. Count Primes
solution 1: check each number is prime or not
public int countPrimes(int n) {
	if(n<=2) return 0;
	
	int cnt = 0;
	for(int i=2;i<n;i++){
		if(isPrime(i))
			cnt++;
	}
	return cnt;
}
public boolean isPrime(int num){
	for(int i=2;i<=Math.sqrt(num);++i){
		if(num%i==0)
			return false;
	}
	return true;
}
//#solution 2: set each non-prime to false
public int countPrimes(int n) {
	if(n<=2) return 0;
	boolean[] isPrime = new boolean[n];
	Arrays.fill(isPrime,true);
	for(int i=2;i*i<n;++i){
		if(isPrime[i]){
		   for(int times = i;times*i<n;times++)
			{
				isPrime[i * times] = false;
			}
		}
	}
	int cnt = 0;
	for(int i=2;i<n;++i){
		if(isPrime[i]){
			cnt++;
		}
	}
	return cnt;
}