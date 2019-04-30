import java.util.Collections;
import java.util.Vector;
import java.util.HashMap;

public class program
{	
	public static String test(String hex)
	{
		/*
		Exercise 16: Hex to binary- Given a string representing a number in hexadecimal
		format, convert it into its equivalent binary string. For e.g. if the input if â€�1F1â€�
		then its binary equivalent is â€�111110001â€�. If the input is â€�13AFFFFâ€�, the output
		should be â€�1001110101111111111111111â€�.
		*/
		HashMap<String,Integer> hm=new HashMap<String,Integer>();
		hm.put("1",1);
		hm.put("2",2);
		hm.put("3",3);
		hm.put("4",4);
		hm.put("5",5);
		hm.put("6",6);
		hm.put("7",7);
		hm.put("8",8);
		hm.put("9",9);
		hm.put("A",10);
		hm.put("B",11);
		hm.put("C",12);
		hm.put("D",13);
		hm.put("E",14);
		hm.put("F",15);
		
		int n=hex.length();
		int decimal=0;
		for(int i=0;i<n;i++)
		{
			decimal+=Math.pow(16,n-i-1)*hm.get(hex.substring(i,i+1));
			//charAt(i) cannot be used in the place of substring()
		}
		
		return decimal_to_binary(decimal);
	}
	
	public static String decimal_to_binary(int x)
	{
		String y="";
		
		while(x!=0)
		{
			y=(x%2)+y;
			x/=2;
		}
		
		return y;
	}
}