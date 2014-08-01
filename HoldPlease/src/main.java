import java.util.Iterator;

import edu.neumont.util.Client;


public class main {

	public static void BankTest()
	{
		Bank b=new Bank(3);
		
		Client a=new Client(9);
		Client c=new Client(3);
		Client d=new Client(13);
		Client e=new Client(7);
		Client f=new Client(6);
		Client g=new Client(12);
		
		b.addClient(a);
		b.addClient(c);
		b.addClient(d);
		b.addClient(e);
		b.addClient(f);
		b.addClient(g);
		
		while(!b.noMoreClients())
		{
			for(Client var : b.teller)
			{
				if(var!=null)
				{
					System.out.println("Teller Time: " + b.getClientWaitTime(var) + " Teller Service Time: " + var.getExpectedServiceTime());
				}
			}
			for(int i=0; i<b.cust.currentIndex; i++)
			{
				System.out.println("Wait Time: " + b.getClientWaitTime(b.cust.get(i)) + " Service Time: " + b.cust.get(i).getExpectedServiceTime());
			}
			System.out.println("Average Wait Time: " + b.getAverageClientWaitTime());
			b.advanceMinute();
		}
	}
	
	public static void GroceryTest()
	{
		GroceryStore gs=new GroceryStore(2);
		
		Client a=new Client(9);
		Client c=new Client(3);
		Client d=new Client(3);
		Client e=new Client(7);
		Client f=new Client(6);
		Client g=new Client(12);
		
		gs.addClient(a);
		gs.addClient(c);
		gs.addClient(d);
		gs.addClient(e);
		gs.addClient(f);
		gs.addClient(g);
		
		while(!gs.noMoreCustomers())
		{
			for(int i=0; i<gs.beingServed.currentIndex; i++)
			{
				boolean first=true;
				for(Client var : gs.beingServed.get(i))
				{
					if(first)
					{
						first=false;
						System.out.println("At Register Wait Time: " + gs.getClientWaitTime(var) + " At Register Service Time: " + var.getExpectedServiceTime() + " Register number: " + i);
					}
					else
					{
						System.out.println("Wait Time: " + gs.getClientWaitTime(var) + " Service Time: " + var.getExpectedServiceTime() + " Register number: " + i);
					}
				}
			}
			
			System.out.println("Average wait time: " + gs.getAverageClientWaitTime());
			gs.advanceMinute();
		}
	}
	
	public static void ArrayListTest()
	{
		ArrayList<Integer> al=new ArrayList<Integer>();
		al.remove(10);
		System.out.println(al.size());
		al.add(2);
		al.remove(4);
		System.out.println(al.size());
		al.add(4);
		al.add(3);
		al.add(5);
		
		System.out.println("Size of ArrayList: " + al.size());
		
		while(al.size()>0)
		{
			System.out.println("Remove at 0 ArrayList: " + al.remove(al.get(0)));
			for(int i=0; i<al.size(); i++)
			{
				System.out.println("After Remove: " + al.get(i));
				System.out.println("Size of ArrayList: " + al.size());
			}
		}
		
		System.out.println("Getting from ArrayList of size 0: " + al.get(0));
		
		System.out.println("Getting from ArrayList out of range: " + al.get(200));
	}
	
	public static void LinkedListTest()
	{
LinkedList<Integer> ll=new LinkedList<Integer>();
		ll.poll();
		Iterator it = ll.iterator();
		while ( it.hasNext() ) {
			System.out.println(it.next());
		}
		ll.offer(2);
		ll.offer(4);
		ll.offer(3);
		ll.offer(5);
		
		System.out.println("Size of LinkedList: " + ll.size);
		
		for(Integer var : ll)
		{
			System.out.println("Using LinkedList Iterator: " + var);
		}
		
		while(ll.size>0)
		{
			System.out.println("Peek LinkedList: " +ll.peek());
			System.out.println("Poll LinkedList: " + ll.poll());
		}
		
		System.out.println("Size of LinkedList: " + ll.size);
		System.out.println("Peeking LinkedList size of 0: " + ll.peek());
		System.out.println("Polling LinkedList size of 0: " + ll.poll());
		
		for(Integer var : ll)
		{
			System.out.println("Using LinkedList Iterator: " + var);
		}
	}
	
	public static void main(String[] args)
	{	
		//BankTest();
		
		//GroceryTest();
		
		ArrayListTest();
		
		//LinkedListTest();
	}

}
