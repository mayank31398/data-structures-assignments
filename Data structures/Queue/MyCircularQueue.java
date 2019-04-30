public class MyCircularQueue<E> implements MyCircularQueue_interface<E>
{
    private E[] x=(E[])new Object[1];
	private int a=0;
	private int b=0;
	
	public void enqueue(E o)
	{
		if(this.size()==x.length-1)
		{
			E[] y=(E[])new Object[x.length*2];
			b=x.length-1;
			
			for(int i=0;i<x.length-1;i++)
			{
				y[i]=x[(a+i)%x.length];
			}
			
			a=0;
			x=y;
		}
		
		x[b]=o;
		b=(b+1)%x.length;
	}
	
	public E dequeue() throws EmptyQueueException
	{
		a=(a+1)%x.length;
		
		if(b==(a+x.length-1)%x.length)
			throw new EmptyQueueException();
		
		return x[(a+x.length-1)%x.length];
	}
	
	public E front() throws EmptyQueueException
	{
		if(b==(a+x.length-1)%x.length)
            throw new EmptyQueueException();
            
		return x[a];
	}
	
	public int size()
	{
		if(b>=a)
			return b-a;
			
		return x.length-a+b;
	}
	
	public boolean isEmpty()
	{
		if(this.size()==0)
            return true;
            
		return false;
	}
}