import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.EmptyStackException;
import java.util.Vector;
import java.util.Collections;

interface MyStack_interface<E>
{
	public void push(E o);
	public E pop() throws EmptyStackException;
	public E top() throws EmptyStackException;
	public int size();
	public boolean isEmpty();
}

public class Breakup
{
    public static void main(String[] args) throws Exception
    {
        String InputFileName=args[0];
		String OutputFileName=args[1];
		
		FileReader a=new FileReader(InputFileName);
		BufferedReader input=new BufferedReader(a);

		FileWriter b= new FileWriter(OutputFileName);
		BufferedWriter output=new BufferedWriter(b);

		int n=Integer.parseInt(input.readLine());
		Vector<Integer> score=new Vector<Integer>();
		String[] input_string=new String[3];
		int[] input_int=new int[3];
		int ShirtWanted;
		int ScoreOfShirt;
		int ShirtsToppled;

		MyStack<Integer> PileOfClothes=new MyStack<Integer>();

		for(int i=0;i<n;i++)
		{
			input_string=input.readLine().split(" ");

			for(int j=0;j<input_string.length;j++)
				input_int[j]=Integer.parseInt(input_string[j]);

			if(input_int[1]==1)
			{
				score.add(input_int[2]);
				PileOfClothes.push(input_int[2]);
			}

			if(input_int[1]==2)
			{
				Collections.sort(score);
				ShirtWanted=score.remove(score.size()-1);
				ShirtsToppled=0;

				while(true)
				{
					try
					{
						ScoreOfShirt=PileOfClothes.pop();

						if(ScoreOfShirt==ShirtWanted)
							break;

						ShirtsToppled++;
					}
					catch(EmptyStackException e)
					{
						ShirtsToppled=-1;
						break;
					}
				}

				output.write(input_int[0]+" "+ShirtsToppled+"\n");
			}
		}

		input.close();
		output.close();
	}
}


//Implementation of stack
class MyStack<E> implements MyStack_interface<E>
{
	private E[] x=(E[])new Object[1];
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
		if(t==-1)
			return true;
		return false;
	}
}