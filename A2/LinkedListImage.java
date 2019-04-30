import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class LinkedListImage implements CompressedImageInterface
{
    public int width;
    public int height;
    public MyLinkedList[] list;

	public LinkedListImage(String filename)
	{
        try
        {
            FileReader a=new FileReader(filename);
            BufferedReader input=new BufferedReader(a);
            String[] line=input.readLine().split(" ");
            
            width=Integer.parseInt(line[0]);
            height=Integer.parseInt(line[1]);
            int value;
            MyLinkedList<Integer> l;
            int first=1;
            list=new MyLinkedList[height];
    
            for(int i=0;i<height;i++)
            {
                line=input.readLine().split(" ");
                l=new MyLinkedList<Integer>();
                for(int j=0;j<width;j++)
                {
                    value=Integer.parseInt(line[j]);
    
                    if(value==0 && first==1)
                    {
                        l.addLast(j);
                        first=0;
                    }
    
                    if(value==1 && first==0)
                    {
                        l.addLast(j-1);
                        first=1;
                    }
    
                    if(value==0 && j==width-1)
                    {
                        if(first==1)
                            l.addLast(j);
                        l.addLast(j);
                        first=1;
                    }
                }
    
                l.addLast(-1);
    
                first=1;
                list[i]=l;
            }
            
            input.close();
        }
        catch(Exception e)
        {
            System.out.println("File read exception.");
        }
	}

    //a=width, b=height
    public LinkedListImage(boolean[][] grid, int a, int b)
    {
        MyLinkedList<Integer> l;
        int first=1;
        width=a;
        height=b;
        list=new MyLinkedList[height];

        for(int i=0;i<height;i++)
        {
            l=new MyLinkedList<Integer>();

            for(int j=0;j<width;j++)
            {
                boolean value=grid[i][j];

                if(value==false && first==1)
                {
                    l.addLast(j);
                    first=0;
                }

                if(value==true && first==0)
                {
                    l.addLast(j-1);
                    first=1;
                }

                if(value==false && j==width-1)
                {
                    if(first==1)
                        l.addLast(j);
                    l.addLast(j);
                    first=1;
                }
            }

            l.addLast(-1);

            first=1;
            list[i]=l;
        }
    }

    public LinkedListImage(LinkedListImage x)
    {
        width=x.width;
        height=x.height;
        list=new MyLinkedList[height];
        MyListNode<Integer> b;
        MyLinkedList<Integer> l;
        for(int i=0;i<height;i++)
        {
            b=x.list[i].head;
            l=new MyLinkedList<Integer>();
            
            while(b.getElement()!=-1)
            {
                l.addLast(b.getElement());
                b=b.getNext();
            }

            l.addLast(-1);
            list[i]=l;
        }
    }

    //Here x is vertical coordinate & y is horiontal coordinate.
    public boolean getPixelValue(int x, int y) throws PixelOutOfBoundException
    {
        if(x<0 || x>=height || y<0 || y>=width)
            throw new PixelOutOfBoundException("Pixel is out of bounds.");

        MyLinkedList<Integer> a=list[x];

        MyListNode<Integer> b=a.head;
        int start=-1;
        int end=-1;
        while(b.getElement()!=-1)
        {
            start=b.getElement();
            end=b.getNext().getElement();

            if(y>=start && y<=end)
                return false;

            b=b.getNext().getNext();
        }
        
        return true;
    }

    public void setPixelValue(int x, int y, boolean val) throws PixelOutOfBoundException
    {
        if(x<0 || x>=height || y<0 || y>=width)
            throw new PixelOutOfBoundException("Pixel is out of bounds.");
        
        MyLinkedList<Integer> a=list[x];
        MyListNode<Integer> b=a.head;
        int start=-1;
        int end=-1;
        int index=0;

        if(val)
        {
            MyListNode<Integer> t=null;

            while(b.getElement()!=-1)
            {
                start=b.getElement();
                end=b.getNext().getElement();

                if(start==end && y==start)
                {
                    if(index==0)
                    {
                        try
                        {
                            a.removeAfter(null);
                            a.removeAfter(null);
                        }
                        catch(EmptyListException e){}
                    }
                    else
                    {
                        try
                        {
                            a.removeAfter(t);
                            a.removeAfter(t);
                        }
                        catch(EmptyListException e){}
                    }

                    break;
                }
                else if(start<y && y<end)
                {
                    a.addAfter(b,y-1);
                    a.addAfter(b.getNext(),y+1);
                    break;
                }
                else if(start==y)
                {
                    b.changeElement(y+1);                    
                    break;
                }
                else if(end==y)
                {
                    b.getNext().changeElement(y-1);
                    break;
                }

                t=b.getNext();
                index++;
                b=b.getNext().getNext();
            }
        }
        else
        {
            MyListNode<Integer> t=b;

            if(b.getElement()==-1)
            {
                a.addAfter(null, y);
                a.addAfter(null, y);
            }
            else
                while(b.getElement()!=-1)
                {
                    start=b.getElement();
                    end=b.getNext().getElement();

                    if(y<start-1)
                    {
                        if(index==0)
                        {
                            a.head=new MyListNode<Integer>(y,a.head);
                            a.head=new MyListNode<Integer>(y,a.head);
                        }
                        else
                        {
                            a.addAfter(t,y);
                            a.addAfter(t,y);
                        }

                        break;
                    }
                    else if(y>end+1)
                    {
                        if(y<b.getNext().getNext().getElement()-1)
                        {
                            a.addAfter(b.getNext(), y);
                            a.addAfter(b.getNext().getNext(), y);
                            break;
                        }
                        else if(b.getNext().getNext().getElement()==-1)
                        {
                            a.addAfter(b.getNext(), y);
                            a.addAfter(b.getNext(), y);
                            break;
                        }
                    }
                    else if(y==start-1)
                    {
                        b.changeElement(y);
                        break;
                    }
                    else if(y==end+1)
                    {
                        if(y==b.getNext().getNext().getElement()-1)
                        {
                            try
                            {
                                a.removeAfter(b);
                                a.removeAfter(b);
                            }
                            catch(EmptyListException e){}
                        }
                        else
                            b.getNext().changeElement(y);

                        break;
                    }
                    else if(start<=y && y<=end)
                        break;

                    t=b.getNext();
                    index++;
                    b=b.getNext().getNext();
                }
        }
    }
    
    public int[] numberOfBlackPixels()
    {
        int[] x=new int[height];
        MyLinkedList<Integer> a;
        MyListNode<Integer> b;
        int start;
        int end;
        int count=0;

        for(int i=0;i<height;i++)
        {
            a=list[i];
            b=a.head;
            while(b.getElement()!=-1)
            {
                start=b.getElement();
                end=b.getNext().getElement();
                count+=end-start+1;
                b=b.getNext().getNext();
            }
            x[i]=count;
            count=0;
        }

        return x;
    }

    public void invert()
    {
        int start=0;
        int end=15;
        MyLinkedList<Integer> a;
        MyListNode<Integer> b;
        MyLinkedList<Integer> l;

        for(int i=0;i<height;i++)
        {
            a=list[i];
            b=a.head;
            l=new MyLinkedList<Integer>();

            while(b.getElement()!=-1)
            {
                end=b.getElement()-1;

                if(start<=end)
                {
                    l.addLast(start);
                    l.addLast(end);
                }

                start=b.getNext().getElement()+1;
                b=b.getNext().getNext();
            }
            
            end=15;
            if(start<=end)
            {
                l.addLast(start);
                l.addLast(end);
            }

            l.addLast(-1);

            list[i]=l;
            start=0;
        }
    }
    
    public void performAnd(CompressedImageInterface img) throws BoundsMismatchException
    {
        if(width!=((LinkedListImage)img).width || height!=((LinkedListImage)img).height)
            throw new BoundsMismatchException("The dimensions of the two images are different.");
        
        invert();
        img.invert();
        performOr(img);
        invert();
        img.invert();
    }
    
    public void performOr(CompressedImageInterface img) throws BoundsMismatchException
    {
        if(width!=((LinkedListImage)img).width || height!=((LinkedListImage)img).height)
            throw new BoundsMismatchException("The dimensions of the two images are different.");

        int start;
        int end;
        MyLinkedList<Integer> a1;
        MyLinkedList<Integer> a2;
        MyListNode<Integer> b1;
        MyListNode<Integer> b2;
        MyLinkedList<Integer> l;

        for(int i=0;i<height;i++)
        {
            a1=list[i];
            a2=((LinkedListImage)img).list[i];
            b1=a1.head;
            b2=a2.head;
            l=new MyLinkedList<Integer>();

            while(b1.getElement()!=-1 && b2.getElement()!=-1)
            {
                if(b1.getElement()<=b2.getElement())
                    start=b2.getElement();
                else
                    start=b1.getElement();

                if(b1.getNext().getElement()<=b2.getNext().getElement())
                {
                    end=b1.getNext().getElement();
                    b1=b1.getNext().getNext();
                }
                else
                {
                    end=b2.getNext().getElement();
                    b2=b2.getNext().getNext();
                }

                if(end>=start)
                {
                    l.addLast(start);
                    l.addLast(end);
                }
            }
            
            l.addLast(-1);
            list[i]=l;
        }
    }
    
    public void performXor(CompressedImageInterface img) throws BoundsMismatchException
    {
        if(width!=((LinkedListImage)img).width || height!=((LinkedListImage)img).height)
            throw new BoundsMismatchException("The dimensions of the two images are different.");
        
        LinkedListImage x=new LinkedListImage(this);
        LinkedListImage y=new LinkedListImage((LinkedListImage)img);
        x.invert();
        y.invert();
        x.performAnd(img);
        performAnd(y);
        performOr(x);
    }
    
    public String toStringUnCompressed()
    {
        String answer=width+" "+height;

        try
        {
            for(int i=0;i<height;i++)
            {
                answer+=",";

                for(int j=0;j<width;j++)
                {
                    if(getPixelValue(i, j))
                        answer+=" 1";
                    else
                        answer+=" 0";
                }
            }
        }
        catch(PixelOutOfBoundException e){}

        return answer;
    }

    public String toStringCompressed()
    {
        String answer=width+" "+height;

        MyLinkedList<Integer> a;
        MyListNode<Integer> b;

        for(int i=0;i<height;i++)
        {
            a=list[i];
            b=a.head;
            answer+=",";
            while(b!=null)
            {
                answer+=" "+b.getElement();
                b=b.getNext();
            }
        }

        return answer;
    }
    public static void main(String[] args)
    {
    	// testing all methods here :
        boolean success = true;

    	// check constructor from file
        CompressedImageInterface img1 = new LinkedListImage("sampleInputFile.txt");

    	// check toStringCompressed
        String img1_compressed = img1.toStringCompressed();
        
    	String img_ans = "16 16, -1, 5 7 -1, 3 7 -1, 2 7 -1, 2 2 6 7 -1, 6 7 -1, 6 7 -1, 4 6 -1, 2 4 -1, 2 3 14 15 -1, 2 2 13 15 -1, 11 13 -1, 11 12 -1, 10 11 -1, 9 10 -1, 7 9 -1";
    	success = success && (img_ans.equals(img1_compressed));

    	if (!success)
    	{
    		System.out.println("Constructor (file) or toStringCompressed ERROR");
    		return;
        }
        
    	// check getPixelValue
    	boolean[][] grid = new boolean[16][16];
    	for (int i = 0; i < 16; i++)
    		for (int j = 0; j < 16; j++)
    		{
                try
                {
        			grid[i][j] = img1.getPixelValue(i, j);                
                }
                catch (PixelOutOfBoundException e)
                {
                    System.out.println("Errorrrrrrrr");
                }
    		}

    	// check constructor from grid
    	CompressedImageInterface img2 = new LinkedListImage(grid, 16, 16);
    	String img2_compressed = img2.toStringCompressed();
    	success = success && (img2_compressed.equals(img_ans));

    	if (!success)
    	{
    		System.out.println("Constructor (array) or toStringCompressed ERROR");
    		return;
        }

    	// check Xor
        try
        {
        	img1.performXor(img2);       
        }
        catch (BoundsMismatchException e)
        {
            System.out.println("Errorrrrrrrr");
        }
    	for (int i = 0; i < 16; i++)
    		for (int j = 0; j < 16; j++)
    		{
                try
                {
        			success = success && (!img1.getPixelValue(i,j));                
                }
                catch (PixelOutOfBoundException e)
                {
                    System.out.println("Errorrrrrrrr");
                }
    		}

    	if (!success)
    	{
    		System.out.println("performXor or getPixelValue ERROR");
    		return;
    	}

    	// check setPixelValue
    	for (int i = 0; i < 16; i++)
        {
            try
            {
    	    	img1.setPixelValue(i, 0, true);            
            }
            catch (PixelOutOfBoundException e)
            {
                System.out.println("Errorrrrrrrr");
            }
        }

    	// check numberOfBlackPixels
    	int[] img1_black = img1.numberOfBlackPixels();
    	success = success && (img1_black.length == 16);
    	for (int i = 0; i < 16 && success; i++)
    		success = success && (img1_black[i] == 15);
    	if (!success)
    	{
    		System.out.println("setPixelValue or numberOfBlackPixels ERROR");
    		return;
    	}

    	// check invert
        img1.invert();
        for (int i = 0; i < 16; i++)
        {
            try
            {
                success = success && !(img1.getPixelValue(i, 0));            
            }
            catch (PixelOutOfBoundException e)
            {
                System.out.println("Errorrrrrrrr");
            }
        }
        if (!success)
        {
            System.out.println("invert or getPixelValue ERROR");
            return;
        }

    	// check Or
        try
        {
            img1.performOr(img2);        
        }
        catch (BoundsMismatchException e)
        {
            System.out.println("Errorrrrrrrr");
        }
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 16; j++)
            {
                try
                {
                    success = success && img1.getPixelValue(i,j);
                }
                catch (PixelOutOfBoundException e)
                {
                    System.out.println("Errorrrrrrrr");
                }
            }
        if (!success)
        {
            System.out.println("performOr or getPixelValue ERROR");
            return;
        }

        // check And
        try
        {
            img1.performAnd(img2);    
        }
        catch (BoundsMismatchException e)
        {
            System.out.println("Errorrrrrrrr");
        }
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 16; j++)
            {
                try
                {
                    success = success && (img1.getPixelValue(i,j) == img2.getPixelValue(i,j));
                }
                catch (PixelOutOfBoundException e)
                {
                    System.out.println("Errorrrrrrrr");
                }
            }

        if (!success)
        {
            System.out.println("performAnd or getPixelValue ERROR");
            return;
        }

    	// check toStringUnCompressed
        String img_ans_uncomp = "16 16, 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1, 1 1 1 1 1 0 0 0 1 1 1 1 1 1 1 1, 1 1 1 0 0 0 0 0 1 1 1 1 1 1 1 1, 1 1 0 0 0 0 0 0 1 1 1 1 1 1 1 1, 1 1 0 1 1 1 0 0 1 1 1 1 1 1 1 1, 1 1 1 1 1 1 0 0 1 1 1 1 1 1 1 1, 1 1 1 1 1 1 0 0 1 1 1 1 1 1 1 1, 1 1 1 1 0 0 0 1 1 1 1 1 1 1 1 1, 1 1 0 0 0 1 1 1 1 1 1 1 1 1 1 1, 1 1 0 0 1 1 1 1 1 1 1 1 1 1 0 0, 1 1 0 1 1 1 1 1 1 1 1 1 1 0 0 0, 1 1 1 1 1 1 1 1 1 1 1 0 0 0 1 1, 1 1 1 1 1 1 1 1 1 1 1 0 0 1 1 1, 1 1 1 1 1 1 1 1 1 1 0 0 1 1 1 1, 1 1 1 1 1 1 1 1 1 0 0 1 1 1 1 1, 1 1 1 1 1 1 1 0 0 0 1 1 1 1 1 1";
        success = success && (img1.toStringUnCompressed().equals(img_ans_uncomp)) && (img2.toStringUnCompressed().equals(img_ans_uncomp));

        if (!success)
        {
            System.out.println("toStringUnCompressed ERROR");
            return;
        }
        else
            System.out.println("ALL TESTS SUCCESSFUL! YAYY!");
    }
}