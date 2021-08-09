//#9. Palindrome Number
//solution 2: convert to a tring and 2 pointer check
public boolean isPalindrome(int x) {
	if(x<0) return false;
	String str = "";
	while(x!=0){
		str+= String.valueOf(x%10);
		x=x/10;
	}
	int p=0,q=str.length()-1;
	while(p<=q){
		if(str.charAt(p)!=str.charAt(q))
			return false;
		p++;
		q--;
	}
	return true; 
}
//solution 2: better, reverse the number and check it's same with the original one
public boolean isPalindrome(int x) {
	if(x<0) return false;
	int y = reverse(x);
	return x==y;        
}
public int reverse(int x){
	int y = 0;
	while(x!=0){
		y = y*10 + x%10;
		x = x/10;
	}
	return y;
}
//#125. Valid Palindrome
public boolean isPalindrome(String s) {
	if(s.length()==1) return true;
	int p=0,q=s.length()-1;
	while(p<q){
		if(!Character.isLetter(s.charAt(p)) &&!Character.isDigit(s.charAt(p))) {p++;continue;}
		if(!Character.isLetter(s.charAt(q)) &&!Character.isDigit(s.charAt(q))) {q--;continue;}
		if(Character.toLowerCase(s.charAt(p))!=Character.toLowerCase(s.charAt(q))){
		   return false;
		}
		p++;
		q--;
	}
	return true;
}
//#680. Valid Palindrome II: return true if the s can be palindrome after deleting at most one character from it.
public boolean validPalindrome(String s) {
	if(s.length()<3) return true;
	int p=0,q=s.length()-1;
	while(p<q){
		//if not equal char, verify remove left or right char then it's palindrome or not
		if(s.charAt(p)!=s.charAt(q)){
		   return isValid(s,p,q-1) || isValid(s,p+1,q);
		}
		p++;
		q--;
	}
	return true; 
}
public boolean isValid(String s, int left, int right) {
	 while(left<right){
		if(s.charAt(left)!=s.charAt(right)){
			return false;
		}
		 left++;
		 right--;
	}
	return true;
}
//#234. Palindrome Linked List
public boolean isPalindrome(ListNode head) {
	if(head.next == null) return true;
   //reverse the 2nd half linked list and compare with the original 1st half
	ListNode p=head,q=head;
	while(q!=null && q.next!=null){
		p = p.next;
		q=q.next.next;
	}
	if(p.next==null)//only 2 nodes
		return (p.val==head.val) ? true : false;
	//no matter even or odd length, reverse start from p
	ListNode head2 = reverseLink(p);
	p=head;
	q=head2;
	while(p!=null && q!=null){
		if(p.val!=q.val)//compare value not the node
			return false;
		p = p.next;
		q = q.next;
	}
	return true;
}
public ListNode reverseLink(ListNode head){
	ListNode pre = null, cur = head, post = null;
	while(cur!=null){
		post = cur.next;
		cur.next = pre;
		pre = cur;
		cur = post;		
	}
	return pre;
}
//#409. Longest Palindrome
public int longestPalindrome(String s) {
	//longest len = all even cnt + (odd cnt-1) +1
	int[] cnt = new int[52];
	int ans = 0;
	for(int i=0; i<s.length(); ++i){
		char ch = s.charAt(i);
		if(Character.isUpperCase(ch)){
			cnt[ch-'A'+26]++;
		}
		else
			cnt[ch-'a']++;
	}
	//int maxOdd = 0;
	boolean hasOdd = false;
	for(int i=0;i<52;++i){
		if(cnt[i]%2==0)
			ans += cnt[i];
		else{
			ans += cnt[i]-1;
			hasOdd = true;
		}
	}
	return hasOdd ? (ans+1) : ans;
}