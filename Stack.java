import java.io.PushbackInputStream;

import javax.lang.model.util.Elements;

public class Stack {
	private int top;
	private Object[] elements;
	
	public Stack(int capasity) {
		elements = new Object[capasity];
		top = -1;
	}
	public boolean isFull()
	{
		return elements.length == top+1;
	}
	public boolean isEmpty()
	{
		return top ==-1;
	}
	public int size()
	{
		return top +1;
	}
	public void push(Object data) {
		if(isFull())
			System.out.print("Stack overflow");
		else {
			top++;
			elements[top] = data;
		}			
	}
	public Object pop() {
		if(isEmpty()) {
			System.out.println("Stack is empty");
			return null;
		}
		
		else {
			Object retData = elements[top];
			elements[top] = null;
			top --;
			return retData;
		}
	}
	public Object peek() {
		if(isEmpty()) {
			System.out.println("Stack is empty");
			return null;
		}
		
		else {
			return elements[top];
		}
	}
}
