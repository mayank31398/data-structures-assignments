class intro
{
	public int age ;
	public static int credits ;
	
	public intro ( int a )
	{
		age = a ;
	}

	public void setage ( int newage )
	{
		age = newage ;
	}

	public static int getcredit ()
	{
		return credits ;
	}

	public static void main ( String args [])
	{
		intro x = new intro(5);
		x.setage(10);
		System.out.println(x.age);
	}
}