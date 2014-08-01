import edu.neumont.util.Client;
import edu.neumont.util.QueueableService;


public class GroceryStore implements QueueableService
{
	ArrayList<LinkedList<Client>> beingServed;
	
	public GroceryStore(int numberOfLines)
	{
		beingServed=new ArrayList<LinkedList<Client>>();
		for(int i=0; i<numberOfLines; i++)
		{
			LinkedList<Client> adder=new LinkedList<Client>();
			beingServed.add(adder);
		}
	}
	
	public boolean noMoreCustomers()
	{
		boolean ret=true;
		for(int i=0; i<beingServed.size(); i++)
		{
			if(beingServed.get(i).size!=0)
				ret=false;
		}
		
		return ret;
	}

	@Override
	public double getAverageClientWaitTime()
	{
		int allClientTime=0;
		int allClients=0;
		
		for(int i=0; i<beingServed.size(); i++)
		{
			allClients+=beingServed.get(i).size-1;
			
			boolean first=true;
			for(Client var : beingServed.get(i))
			{
				if(first)
				{
					first=false;
				}
				else
				{
					allClientTime+=getClientWaitTime(var);
				}
			}
		}
		
		if(allClients<1)
			return 0;
		
		return (double)allClientTime/(double)allClients;
	}

	@Override
	public double getClientWaitTime(Client client)
	{
		for(int i=0; i<beingServed.size(); i++)
		{
			int ret=0;
			boolean found=false;
			for(Client var : beingServed.get(i))
			{
				if(var==client)
					return ret;
				else
					ret+=var.getExpectedServiceTime();
			}
		}
		return 0;
	}

	@Override
	public boolean addClient(Client client) {
		int index=0;
		int min=-1;

		for(int i=0; i<beingServed.size(); i++)
		{
			if(min==-1 || min>beingServed.get(i).size)
			{
				index=i;
				min=beingServed.get(i).size;
			}
		}
		
		return beingServed.get(index).offer(client);
	}

	@Override
	public void advanceMinute()
	{
		for(int i=0; i<beingServed.currentIndex; i++)
		{
			if(beingServed.get(i).peek()!=null && (beingServed.get(i).peek().servedMinute()==0 || beingServed.get(i).peek().getExpectedServiceTime() < 1))
			{
				beingServed.get(i).poll();
			}
		}
	}

}
