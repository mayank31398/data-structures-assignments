public class program
{
	public String test(String s1, String s2, int m, int n)
	{
		/*
		Exercise 20: String concat- Given two strings s1,s2 and two indices m,n return a
		string having chars from index m to end of s1 and then 0 to n of s2 (both m and n
		are inclusive). For eg, if s1=â€�helloâ€�, s2=â€�worldâ€�, m=3, n=0, then answer is â€�lowâ€�
		*/

		return s1.substring(m)+s2.substring(0,n+1);
	}
}