 //#1047. Remove All Adjacent Duplicates In String
//Input: "abbaca"Output: "ca"
//Explanation: xiaoxiaole
//solution1
 public String removeDuplicates(String S) {
	String res = "";
	if(S.length() <2)
		return S;
	int i = 0;
	while(i<S.length()-1){
		if(S.charAt(i+1)==S.charAt(i)){
			String pattern = Character.toString(S.charAt(i))+Character.toString(S.charAt(i+1));
			S=S.replaceAll(pattern,"");
			i=0;
			continue;
		}
		i++;
	}
	return S;
}
}
//solution2: stack better
 public String removeDuplicates(String S) {
	Stack<Character> sta = new Stack<>();
	String res = "";
	if(S.length() <2)
		return S;
	int i = 0;
	while(i<S.length()){
		if(sta.size()==0)
		   sta.push(S.charAt(i));
		else{
			if(sta.peek() != S.charAt(i))
				sta.push(S.charAt(i));
			else{
				sta.pop();
			}
		}
		i++;
	}
	for(int j=0;j<sta.size();++j){
		res += Character.toString(sta.get(j));
	}
	return res;
}
//#1544. Make The String Great: if one letter's lower and upper are adjacent, remove both of them
//Input: s = "leEeetcode" Output: "leetcode"
public String makeGood(String s) {
	Stack<Character> sta = new Stack<Character>();
	if(s.length()<2)
		return s;
	String ans = "";
	int i=0;
	while(i<s.length()){
		char ch = s.charAt(i); 
		if(sta.size()==0)
			sta.push(ch);
		else{
			if((Character.isUpperCase(sta.peek()) && ch== Character.toLowerCase(sta.peek())) || (Character.isLowerCase(sta.peek()) && ch== Character.toUpperCase(sta.peek())) )
				sta.pop();
			else
				sta.push(ch);
		}
		i++;
	}
	for(i=0; i<sta.size();++i){
		ans += Character.toString(sta.get(i));
	}
	return ans;
}