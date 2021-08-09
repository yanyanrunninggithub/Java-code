//#1614 Maximum Nesting Depth of the Parentheses
// s = "(1)+((2))+(((3)))" Output: 3
public int maxDepth(String s) {
	int l =0;
	//int r = 0;
	int max = 0;
	for(int i=0;i<s.length();++i){
		if(s.charAt(i)=='(')
			l++;
		if(s.charAt(i)==')'){
			max = Math.max(max,l);
			l--;
		}
	}
	return max;	
}
//#1021. Remove Outermost Parentheses
//Input: "(()())(())"Output: "()()()"
//Explanation: The input string is "(()())(())", with primitive decomposition "(()())" + "(())". After removing outer parentheses of each part, this is "()()" + "()" ="()()()".
public String removeOuterParentheses(String S) {
	//count left and right parenthese 
	int lp=0, rp=0;
	//record the index of the start and end of the divide places
	int index = 0;;
	String res = "";
	for(int i=0; i<S.length(); ++i){
		if(S.charAt(i)=='(')
			lp++;
		if(S.charAt(i)==')')
			rp++;
		if(lp==rp){
			res += S.substring(index+1,i);
			index = i+1;
			lp=0;
			rp=0;
		   }        
	}
	return res;
}