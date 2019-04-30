public class program
{
	public int[][] test(int M1[][], int M2[][])
	{
		/*
		Exercise 19: Matrix addition- Given two matrices M1 and M2, the objective to
		add them. Each matrix is provided as an int[][], a 2 dimensional integer array.
		The expected output is also 2 dimensional integer array.
		*/
		
		int m=M1.length;
		int n=M1[0].length;
		int[][] M=new int[m][n];
		
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
				M[i][j]=M1[i][j]+M2[i][j];
		}
		
		return M;
	}
}