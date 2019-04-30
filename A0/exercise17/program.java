import java.util.Vector;

public class program
{
	public String[] test(String fileNames[])
	{
		/*
		Exercise 17: Java files- You have been given the list of the names
		of the files in a directory.
		You have to select Java files from them.
		A file is a Java file if itâ€™s name ends with â€�.javaâ€�.
		For e.g. â€�File-Names.javaâ€� is a Java file, â€�FileNames.java.pdfâ€� is not.
		If the input is {â€�can.javaâ€�,â€�nca.docâ€�,â€�and.javaâ€�,â€�dan.txtâ€�,â€�can.javaâ€�,â€�andjava.pdfâ€�} 
		the expected output is {â€�can.javaâ€�,â€�and.javaâ€�,â€�can.javaâ€�}
		*/
		
		Vector<String> v=new Vector<String>();
		int n=0;
		
		for(int i=0;i<fileNames.length;i++)
		{
			n=fileNames[i].length();
			
			if(n>5 && fileNames[i].substring(n-5).equals(".java"))
				v.add(fileNames[i]);
		}
		
		String[] output=new String[v.size()];
		
		for(int i=0;i<v.size();i++)
			output[i]=v.get(i);
		
		return output;
	}
}