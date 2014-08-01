import java.util.Iterator;
import edu.neumont.util.List;


public class ArrayList<T> implements List<T>
{
	T t[]=(T[])new Object[10];
	public int currentIndex=0;

	@Override
	public boolean add(T t)
	{
		if(currentIndex==this.t.length)
		{
			T newT[]=(T[])new Object[this.t.length*2];
			
			for(int i=0; i<this.t.length; i++)
			{
				newT[i]=this.t[i];
			}
			this.t=newT;
		}
		
		this.t[currentIndex]=t;
		
		return this.t[currentIndex++]!=null;
	}

	@Override
	public T get(int index)
	{
		if(index>=size())
			return null;
		return t[index];
	}

	@Override
	public boolean remove(T t)
	{
		boolean del=false;
		
		for(int i=0; i<this.t.length; i++)
		{
			if(t==this.t[i])
			{
				del=true;
			}
			
			if(del && (i+1)<this.t.length)
			{
				this.t[i]=this.t[i+1];
			}
		}
		
		if(!del)
			return del;
		
		currentIndex--;
		
		return del;
	}

	@Override
	public int size()
	{
		return currentIndex;
	}

	@Override
	public Iterator<T> iterator()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
