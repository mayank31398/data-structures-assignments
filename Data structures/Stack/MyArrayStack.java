import java.util.EmptyStackException;

public class MyArrayStack<E> implements MyArrayStack_interface<E>
{
	private E[] x=(E[])new Object[16];
	private int t=-1;
	
	public void push(E o)
	{
		t++;
		if(t==x.length)
		{
			E[] y=(E[])new Object[x.length*2];
			for(int i=0;i<t;i++)
				y[i]=x[i];
			x=y;
		}
		
		x[t]=o;
	}
	
	public E pop() throws EmptyStackException
	{
		if(t==-1)
			throw new EmptyStackException();
		t--;
		return x[t+1];
	}
	
	public E top() throws EmptyStackException
	{
		if(t==-1)
			throw new EmptyStackException();
			
		return x[t];
	}
	
	public int size()
	{
		return t+1;
	}
	
	public boolean isEmpty()
	{
		if(this.size()==0)
			return true;

		return false;
	}
}