public class program
{
	public int test(int n, int m)
	{
		/*
		Exercise 10: Least common multiple- Given two integers n and m, the objective
		is to compute the LCM (least common multiple) of n and m. LCM is the smallest
		number that is divisble by both n amd m. For e.g. is n is 12 and m is 14, the
		LCM is 84. If n is 32 and m is 16, the LCM is 32.
		*/
		
		int HCF=1;

		if (m<n)
		{
			int t=m;
			m=n;
			n=t;
		}
		
		for (int i=1;i<=n;i++)
		{
			if (n%i==0 && m%i==0)
				HCF=i;
		}
		
		int LCM = m*n/HCF;
		return LCM;
	}
}