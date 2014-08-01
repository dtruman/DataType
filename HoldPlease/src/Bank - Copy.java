import edu.neumont.util.Client;
import edu.neumont.util.QueueableService;


public class Bank implements QueueableService
{
	ArrayList<Client> cust=new ArrayList<Client>();
	Client[] teller;
	
	public Bank(int numberOfTellers)
	{
		teller=new Client[numberOfTellers];
	}
	
	@Override
	public double getAverageClientWaitTime()
	{
		double ret=0;
		for(int i=0; i<cust.size(); i++)
		{
			ret+=getClientWaitTime(cust.get(i));
		}
		if(cust.size()==0)
			return 0;
		
		ret=ret/(double)cust.size();

		return ret;
	}

	@Override
	public double getClientWaitTime(Client client)
	{
		double ret=-1;
		double[] tellTimes=new double[teller.length];
		
		for(int i=0; i<teller.length; i++)
		{
			if(client==teller[i] || teller[i]==null)
				return 0;
			tellTimes[i]=teller[i].getExpectedServiceTime();
		}
		
		for(int i=0; i<cust.size() && cust.get(i)!=client; i++)
		{
			int indexUse=-1;
			int otherTell=-1;
			
			for(int j=0; j<teller.length; j++)
			{
				if(tellTimes[j] < otherTell || otherTell==-1)
				{
					otherTell=teller[j].getExpectedServiceTime();
					indexUse=j;
				}
			}
			
			tellTimes[indexUse]+=cust.get(i).getExpectedServiceTime();
		}
		
		for(int i=0; i<tellTimes.length; i++)
		{
			if(tellTimes[i]<ret || ret==-1)
				ret=tellTimes[i];
		}
		
		return ret;
	}
	
	public boolean noMoreClients()
	{
		boolean ret=true;
		for(int i=0; i<teller.length; i++)
		{
			if(teller[i]!=null)
				ret=false;
		}
			
		return ret;
	}

	@Override
	public boolean addClient(Client client)
	{
		for(int i=0; i<teller.length; i++)
		{
			if(teller[i]==null)
			{
				teller[i]=client;
				return true;
			}
		}
		return cust.add(client);
	}

	@Override
	public void advanceMinute()
	{
		for(int i=0; i<teller.length; i++)
		{
			if(teller[i]!=null && (teller[i].servedMinute()==0 || teller[i].getExpectedServiceTime()<0))
			{
				teller[i]=null;
				for(int j=0; j<cust.size() && cust.get(j)!=null && teller[i]==null; j++)
				{
					if(!(cust.get(j).getExpectedServiceTime()==0 ||cust.get(j).getExpectedServiceTime()<0))
					{
						teller[i]=cust.get(j);
						cust.remove(cust.get(j));
					}
					else
					{
						cust.remove(cust.get(j));
					}
				}
			}
		}
	}

}
