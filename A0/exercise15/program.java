import java.util.Vector;

public class program
{
	public int[] test(int input[])
	{
		/*
		Exercise 15: Remove zeros- Given an array of integers return an array in the
		same order with all 0’s removed. For e.g. is the input is {1,2,3,4,5,0,1,2,0,0,2}
		the expected output is {1,2,3,4,5,1,2,2}. If the input is {0,0,1,2} the output is
		{1,2}. If the input is {0,0,0,0} the expected output is {}.
		*/
		
		Vector<Integer> v=new Vector<Integer>();
		int x=0;
		
		for(int i=0;i<input.length;i++)
		{
			if(input[i]!=0)
				v.add(input[i]);
		}
		
		int[] output=new int[v.size()];
		
		for(int i=0;i<v.size();i++)
			output[i]=v.get(i);
		
		return output;
	}
}