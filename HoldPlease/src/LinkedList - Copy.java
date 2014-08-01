import java.util.Iterator;

import edu.neumont.util.Queue;


public class LinkedList<T> implements Queue<T>
{
	public int size=0;
	Node start;
	Node tail;
	
	@Override
	public Iterator<T> iterator() {
		Iterator<T> it = new Iterator<T>()
		{
			Node current=null;
			Node last=tail;
			
			@Override
			public boolean hasNext() {
				
				if(current!=last)
				{
					return true;
				}
				
				return false;
			}

			@Override
			public T next() {
				
				if(current==null)
				{
					current=start;
					return current.value;
				}
				else if(current!=last)
				{
					current=current.nextNode;
					return current.value;
				}
				
				return null;
			}
		};
		
		return it;
	}

	@Override
	public T poll() {
		Node ret=start;
		
		if(start==null)
			return null;
		
		start=start.nextNode;
		
		if(start==null)
			tail=null;
		
		size--;
		
		return ret.value;
	}

	@Override
	public boolean offer(T t) {
		if(size==0)
		{
			start=new Node();
			start.value=t;
			tail=start;
			size++;
			return true;
		}
		else if(size>0)
		{
			Node n=new Node();
			n.value=t;
			
			tail.nextNode=n;
			tail=tail.nextNode;
			
			size++;
			return true;
		}
		
		return false;
	}

	@Override
	public T peek()
	{
		if(start==null)
			return null;
		
		return start.value;
	}
	
	private class Node
	{
		Node nextNode;
		T value;
	}

}
