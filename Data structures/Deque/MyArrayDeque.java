public class MyArrayDequeue implements MyArrayDeque_interface
{
	private Object[] x=new Object[1];
	private int a=0;
	private int b=0;

	public void insertFirst(Object o)
	{
		if(this.size()==x.length-1)
		{
			Object[] y=new Object[x.length*2];
			b=x.length-1;

			for(int i=0;i<x.length;i++)
				y[i]=x[(a+i)%x.length];

			a=0;
			x=y;
		}

		a=(a+x.length-1)%x.length;
		x[a]=o;
	}
	
	public void insertLast(Object o)
	{
		if(this.size()==x.length-1)
		{
			Object[] y=new Object[x.length*2];
			b=x.length-1;

			for(int i=0;i<x.length-1;i++)
				y[i]=x[(a+i)%x.length];

			a=0;
			x=y;
		}

		x[b]=o;
		b=(b+1)%x.length;
	}
	
	public Object removeFirst() throws EmptyDequeException
	{
		a=(a+1)%x.length;
		
		if(b==(a+x.length-1)%x.length)
			throw new EmptyDequeException("A");
		
		return x[(a+x.length-1)%x.length];
	}
	
	public Object removeLast() throws EmptyDequeException
	{
		if(this.isEmpty()==true)
			throw new EmptyDequeException("A");

		b=(b+x.length-1)%x.length;
		return x[b];
	}

	public Object first() throws EmptyDequeException
	{
		if(this.isEmpty()==true)
			throw new EmptyDequeException("A");

		return x[a];
	}
	
	public Object last() throws EmptyDequeException
	{
		if(this.isEmpty()==true)
			throw new EmptyDequeException("A");

		return x[(b-1+x.length)%x.length];
	}
	
	public int size()
	{
		if(b>=a)
			return b-a;
		
		return x.length+b-a;
	}

	public boolean isEmpty()
	{
		if(this.size()==0)
			return true;
		
		return false;
	}

	public String toString()
	{
		int n=this.size();
		String y="[";

		for(int i=0;i<n;i++)
			y+=x[(a+i)%x.length].toString()+", ";

		return y.substring(0,y.length()-2)+"]";
	}
}