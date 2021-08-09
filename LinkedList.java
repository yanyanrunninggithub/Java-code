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
 
//#1290. Convert Binary Number in a Linked List to Integer
//Input: head = [1,0,1] Output: 5 Explanation: (101) in base 2 = (5) in base 10
public int getDecimalValue(ListNode head) {
	//scan the linkedList and store in a list
	List<Integer> l = new ArrayList<>();
	while(head!=null){
		l.add(head.val);
		head = head.next;
	}
	int n = l.size()-1;
	int res = 0;
	for(int i=0; i<l.size()-1; ++i){
		res += Math.pow(l.get(i)*2, n);
		n--;
	}
	//Math.pow(0,0) == 1, so need judge the last element 
	if(l.get(l.size()-1)==1)
		res +=1;
	return res;
}
//#876. Middle of the Linked List
 public ListNode middleNode(ListNode head) {
	if(head == null || head.next == null)
		return head;
	ListNode p = head, p2 = head;
	//length is even, jump out condition is p2 != null
	//length is odd, jump out condition is p2.next != null
	while(p2 != null && p2.next!=null)
	{
		p2 = p2.next.next;
		p = p.next;
	}
	return p;
}
//237. Delete Node in a Linked List
//You will not be given access to the head of the list, It is guaranteed that the node to be deleted is not a tail node in the list.
//so convert the question to delete the next node and reset node value to next value
public void deleteNode(ListNode node) {
	node.val = node.next.val;
	node.next = node.next.next;       
}
//reverse linkedlist
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
//#203. Remove Linked List Elements
 public ListNode removeElements(ListNode head, int val) {
	if(head==null)
		return null;
	ListNode pre = null, cur = head, post = null;
	ListNode head2 = null;
	boolean flag = true;
	while(cur !=null){
		post = cur.next;
		if(cur.val == val){
			if(flag)//head.val == val means pre==null
				cur = post;
			else{
				pre.next = post;
				cur = post;                    
			}
			continue;
		}
		if(flag){//return head is null, set a new head
			head2 = cur;
			flag = false;
		}
		pre = cur;
		cur = post;
	}
	return head2;
}
//#160. Intersection of Two Linked Lists
//solution1: get 2 links length, if equal, one by one compare, if not, long link move the diff, then one by one
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        int lenA = getLength(headA), lenB = getLength(headB);
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; ++i) headA = headA.next;
        } else {
            for (int i = 0; i < lenB - lenA; ++i) headB = headB.next;
        }
        while (headA != null && headB != null && headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return (headA != null && headB != null) ? headA : null;
    }
    public int getLength(ListNode head) {
        int cnt = 0;
        while (head != null) {
            ++cnt;
            head = head.next;
        }
        return cnt;
    }
}
//solution2: think as a circle link, add 2 link together to scan, until they meet or both get the end
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null || headB==null) return null;
        ListNode p=headA,q=headB;
        while(p!=q){
            p = (p!=null) ? p.next : headB;
            q = (q!=null) ? q.next : headA;
        }       
        return p;
    }
}
//#83. Remove Duplicates from Sorted List
 public ListNode deleteDuplicates(ListNode head) {
	ListNode p =head;
	ListNode pre = null;
	while(p!=null){
		pre = p;
		while(p!=null && p.val == pre.val){
			p = p.next;
		}
		pre.next = p;
	}
	return head;
}