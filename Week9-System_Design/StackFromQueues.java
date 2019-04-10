class MyStack {
    Queue<Integer> inQueue;
    Queue<Integer> outQueue;
    /** Initialize your data structure here. */
    public MyStack() {
        inQueue = new LinkedList<Integer>();
        outQueue = new LinkedList<Integer>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        inQueue.add(x);
        while (outQueue.peek()!=null) {
            inQueue.add(outQueue.poll());
        }
        outQueue = inQueue;
        inQueue = new LinkedList<Integer>(); 
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return outQueue.poll();
    }
    
    /** Get the top element. */
    public int top() {
        if (outQueue.peek() == null) {
            return 0;
        }
        return outQueue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return outQueue.peek()==null;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */