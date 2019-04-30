public class ArrayDequeue implements DequeInterface
{
	private Object[] x=new Object[2];
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
		
	public static void main(String[] args){
		int  N = 10;
		DequeInterface myDeque = new ArrayDequeue();
		for(int i = 0; i < N; i++) {
			myDeque.insertFirst(i);
			myDeque.insertLast(-1*i);
		}
	 
		int size1 = myDeque.size();
		System.out.println("Size: " + size1);
		System.out.println(myDeque.toString());
		
		if(size1 != 2*N){
			System.err.println("Incorrect size of the queue.");
		}
		
		//Test first() operation
		try{
			int first = (int)myDeque.first();
			int size2 = myDeque.size(); //Should be same as size1
			if(size1 != size2) {
				System.err.println("Error. Size modified after first()");
			}
		}
		catch (EmptyDequeException e){
			System.out.println("Empty queue");
		}
		
		//Remove first N elements
		for(int i = 0; i < N; i++) {
			try{
				int first = (Integer)myDeque.removeFirst();
			}
			catch (EmptyDequeException e) {
				System.out.println("Cant remove from empty queue");
			}
			
		}
		
		
		int size3 = myDeque.size();
		System.out.println("Size: " + myDeque.size());
		System.out.println(myDeque.toString());
		
		if(size3 != N){
			System.err.println("Incorrect size of the queue.");
		}
		
		try{
			int last = (int)myDeque.last();
			int size4 = myDeque.size(); //Should be same as size3
			if(size3 != size4) {
				System.err.println("Error. Size modified after last()");
			}
		}
		catch (EmptyDequeException e){
			System.out.println("Empty queue");
		}
		
		//empty the queue  - test removeLast() operation as well
		while(!myDeque.isEmpty()){
				try{
					int last = (int)myDeque.removeLast();
				}
				catch (EmptyDequeException e) {
					System.out.println("Cant remove from empty queue");
				}
		}
		
		int size5 = myDeque.size();
		if(size5 != 0){
			System.err.println("Incorrect size of the queue.");
		} 
	}
}