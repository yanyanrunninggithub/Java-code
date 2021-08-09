//1. string:
//trim: remove all spaces before and after the string, "  start a   to bbb  to c  "--->"start a   to bbb  to c"
public static String trim(String s) {
	int start =0, end = s.length()-1;
	while(s.charAt(start) == ' ')
	  start++;
	while(s.charAt(end) == ' ')
	  end--;
	return s.substring(start,end+1);
}
//split: "  start a   to bbb  to c  "--->["start","a","to","bbb","to","c"]
public static List<String> split(String s) {
	List<String> ans = new ArrayList<>();
	for(int i=0; i<s.length();++i){
	  int start = i;
	  while(s.charAt(i) != ' '){
		i++;
	  }
	  if(i>start){
		ans.add(s.substring(start,i));
	  }
	}
	return ans;
}
//count each word
public static void count(List<String> words){
	Map<String, Integer> map = new HashMap<>();
	for(String w : words) {
	  if(map.containsKey(w)){
		map.put(w,map.get(w)+1);
	  }
	  else{
		map.put(w,1);
	  }
	}
	//print map
	for(var kvp : map.entrySet()){
	  System.out.println(String.format("%s cnt is %d", kvp.getKey(), kvp.getValue()));
	}
}

//2.linked list:
class LinkedList {
  int val;
  LinkedList next;
  LinkedList(int val) {
    this.val = val;
  }
  LinkedList(int val, LinkedList next) {
    this.val = val;
    this.next = next;
  }
}
class Solution {
  public static void main(String[] args) {
    LinkedList head = new LinkedList(1);
    LinkedList h2 = new LinkedList(2);
    LinkedList h3 = new LinkedList(3);
    LinkedList h4 = new LinkedList(4);
    LinkedList h5 = new LinkedList(5);
    head.next = h2;
    h2.next = h3;
    h3.next = h4;
    h4.next = h5;
    h5.next = null;
    //reverse the link of any of the 2 elements in a linkedlist   
    LinkedList h = swapElements(head);
    while(h!=null) {
      System.out.println(h.val);
      h = h.next;
    }    
  }
  ////reverse the link of any of the 2 elements in a linkedlist, e.g [1,2,3,4]--->[2,1,4,3] or [1,2,3,4,5]--->[2,1,4,3,5]
  public static LinkedList swapElements(LinkedList head){
    if(head == null)
      return null;
    if(head.next == null)
      return head;
    LinkedList h1 = head, h2 = head.next, p=null, q=null;
    //divide the linked list to odd list and even list
    while(head != null) {
      p = head;
      q = head.next;
      head = (head.next == null) ? null : head.next.next;
      p.next = head;
      if(q!=null)
        q.next = (head == null) ? null : head.next;
    }
    p = h1;
    q = h2.next;
    LinkedList h = h2;
    boolean flag = true;
    while(h != null) {
	//merge 2 linked list, even list size must smaller than odd
      if(q==null){
        h.next = p;
        break;
      }
      if(flag){
        h.next = p;
        p = p.next;
        flag = false;
      }
      else{
        h.next = q;
        q = q.next;
        flag = true;
      }
      h = h.next;
    }   
    return h2;   
  }  
}