import java.util.Vector;
import java.util.EmptyStackException;

public class MyVectorStack<E> implements MyVectorStack_interface<E>
{
	private Vector<E> x=new Vector<E>();
	
	public void push(E o)
	{
		x.add(o);
	}
	
	public E pop() throws EmptyStackException
	{
		if(x.size()==0)
			throw new EmptyStackException();
		E y=this.top();
		x.remove(x.size()-1);
		return y;
	}
	
	public E top() throws EmptyStackException
	{
		if(x.size()==0)
			throw new EmptyStackException();
		return x.get(size()-1);
	}
	
	public int size()
	{
		return x.size();
	}
	
	public boolean isEmpty()
	{
		if(this.size()==0)
			return true;
		return false;
	}
}