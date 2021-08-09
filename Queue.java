//#1700. Number of Students Unable to Eat Lunch
public int countStudents(int[] students, int[] sandwiches) {
	Queue<Integer> stu = new PriorityQueue<>();
	Queue<Integer> swt = new PriorityQueue<>();
	for (int i = 0; i < sandwiches.length; i++) {
			stu.add(students[i]);
			swt.add(sandwiches[i]);
	}
	while(stu.size()>0){
		if(stu.peek() == swt.peek())
		{
			stu.poll();
			swt.poll();
		}
		else{
			if(!stu.contains(swt.peek()))
				break;
			stu.add(stu.peek());
			stu.poll();
		}
	}
	return stu.size();
}
//#225. Implement Stack using Queues
//solution: mock 2 queues, actually using 1 queue
class MyStack {
    Queue<Integer> q = new LinkedList<Integer>();
    /** Initialize your data structure here. */
    public MyStack() {      
    }//be empty
    
    /** Push element x onto stack. */
    public void push(int x) {
        q.add(x);
        //if(q.size()>1) we push data then we will add again the before data
        for(int i=0;i<q.size()-1;++i){
            q.add(q.peek());
            q.poll();
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int x = q.peek();
        q.poll();
        return x;    
    }
    
    /** Get the top element. */
    public int top() {
        return q.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
    }
}