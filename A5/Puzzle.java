import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;

public class Puzzle
{
    public static void main(String[] args) throws Exception
    {
        HashMap<String,Node> marked;
        Puzzle puz=new Puzzle();
        int n;
        BufferedReader input=new BufferedReader(new FileReader(args[0]));
        BufferedWriter output=new BufferedWriter(new FileWriter(args[1]));
        int input_size=Integer.parseInt(input.readLine());
        String[] input_temp;
        String start_state;
        String end_state;
        int[] costs=new int[9];
        costs[0]=-1;
        ArrayHeap heap;
        LinkedList<Node> t;
        Node r;
        Iterator<Node> it;
        Node a;
        int distance;
        int moves;
        LinkedList<String> path;
        Iterator<String> path_iterator;

        for(int i=0;i<input_size;i++)
        {
            input_temp=input.readLine().split(" ");
            start_state=input_temp[0];
            end_state=input_temp[1];
            input_temp=input.readLine().split(" ");
            heap=new ArrayHeap();
            marked=new HashMap<String,Node>();

            for(int j=1;j<=8;j++)
                costs[j]=Integer.parseInt(input_temp[j-1]);

            if(puz.possible(start_state,end_state))
            {
                r=new Node(start_state);
                r.distance=0;
                heap.indexes.put(r.state,1);
                marked.put(r.state,r);
                while(!r.state.equals(end_state))
                {
                    t=puz.StateGenerator(r.state,costs);
                    it=t.iterator();

                    while(it.hasNext())
                    {
                        a=it.next();

                        if(marked.containsKey(a.state))
                            a=marked.get(a.state);

                        if(a.distance>a.cost+r.distance)
                        {
                            a.distance=r.distance+a.cost;
                            a.moves=r.moves+1;
                            if(!marked.containsKey(a.state))
                            {
                                heap.insert(a);
                                marked.put(a.state,a);
                            }
                            else
                            {
                                n=heap.indexes.get(a.state);
                                heap.Perc(n);
                            }
                            a.previous=r;
                        }
                        else if(a.distance==a.cost+r.distance)
                        {
                            if(a.moves>r.moves+1)
                            {
                                a.moves=r.moves+1;
                                if(!marked.containsKey(a.state))
                                {
                                    heap.insert(a);
                                    marked.put(a.state,a);
                                }
                                else
                                {
                                    n=heap.indexes.get(a.state);
                                    heap.Perc(n);
                                }
                                a.previous=r;
                            }
                        }
                    }

                    r=heap.getMin();
                }

                distance=(int)r.distance;
                moves=r.moves;

                path=new LinkedList<String>();
                while(r!=null && r.change!=null)
                {
                    path.addFirst(r.change);
                    r=r.previous;
                }

                path_iterator=path.iterator();

                output.write(moves+" "+distance+"\n");

                if(path_iterator.hasNext())
                    output.write(path_iterator.next());
                
                while(path_iterator.hasNext())
                    output.write(" "+path_iterator.next());
            }
            else
                output.write("-1 -1\n");
            
            output.write("\n");
        }

        input.close();
        output.close();
    }

    public boolean possible(String x,String y)
    {
        int X=x.indexOf('G');
        int Y=y.indexOf('G');
        int count_x=0;
        int count_y=0;
        String a=x.substring(0,X)+x.substring(X+1);
        String b=y.substring(0,Y)+y.substring(Y+1);

        for(int i=0;i<7;i++)
            for(int j=i+1;j<8;j++)
                if(a.charAt(i)>a.charAt(j))
                    count_x++;
        
        for(int i=0;i<7;i++)
            for(int j=i+1;j<8;j++)
                if(b.charAt(i)>b.charAt(j))
                    count_y++;
        
        return count_x%2==count_y%2;
    }

    public LinkedList<Node> StateGenerator(String x,int[] costs)
    {
        StringBuilder result=new StringBuilder();
        LinkedList<Node> y=new LinkedList<Node>();
        Node t;

        switch(x.indexOf('G'))
        {
            case 0:
                result.append(x.substring(3,4));
                result.append(x.substring(1,3));
                result.append("G");
                result.append(x.substring(4));
                t=new Node(result.toString());
                t.change=x.substring(3,4)+"U";
                t.cost=costs[Integer.parseInt(x.substring(3,4))];
                y.addLast(t);
    
                result=new StringBuilder();
                result.append(x.substring(1,2));
                result.append("G");
                result.append(x.substring(2));
                t=new Node(result.toString());
                t.change=x.substring(1,2)+"L";
                t.cost=costs[Integer.parseInt(x.substring(1,2))];
                y.addLast(t);
    
                break;
            case 1:
                result.append("G");
                result.append(x.substring(0,1));
                result.append(x.substring(2));
                t=new Node(result.toString());
                t.change=x.substring(0,1)+"R";
                t.cost=costs[Integer.parseInt(x.substring(0,1))];
                y.addLast(t);
    
                result=new StringBuilder();
                result.append(x.substring(0,1));
                result.append(x.substring(2,3));
                result.append("G");
                result.append(x.substring(3));
                t=new Node(result.toString());
                t.change=x.substring(2,3)+"L";
                t.cost=costs[Integer.parseInt(x.substring(2,3))];
                y.addLast(t);
    
                result=new StringBuilder();
                result.append(x.substring(0,1));
                result.append(x.substring(4,5));
                result.append(x.substring(2,4));
                result.append("G");
                result.append(x.substring(5));
                t=new Node(result.toString());
                t.change=x.substring(4,5)+"U";
                t.cost=costs[Integer.parseInt(x.substring(4,5))];
                y.addLast(t);

                break;
            case 2:
                result.append(x.substring(0,1));
                result.append("G");
                result.append(x.substring(1,2));
                result.append(x.substring(3));
                t=new Node(result.toString());
                t.change=x.substring(1,2)+"R";
                t.cost=costs[Integer.parseInt(x.substring(1,2))];
                y.addLast(t);
    
                result=new StringBuilder();
                result.append(x.substring(0,2));
                result.append(x.substring(5,6));
                result.append(x.substring(3,5));
                result.append("G");
                result.append(x.substring(6));
                t=new Node(result.toString());
                t.change=x.substring(5,6)+"U";
                t.cost=costs[Integer.parseInt(x.substring(5,6))];
                y.addLast(t);

                break;
            case 3:
                result.append("G");
                result.append(x.substring(1,3));
                result.append(x.substring(0,1));
                result.append(x.substring(4));
                t=new Node(result.toString());
                t.change=x.substring(0,1)+"D";
                t.cost=costs[Integer.parseInt(x.substring(0,1))];
                y.addLast(t);
    
                result=new StringBuilder();
                result.append(x.substring(0,3));
                result.append(x.substring(4,5));
                result.append("G");
                result.append(x.substring(5));
                t=new Node(result.toString());
                t.change=x.substring(4,5)+"L";
                t.cost=costs[Integer.parseInt(x.substring(4,5))];
                y.addLast(t);
    
                result=new StringBuilder();
                result.append(x.substring(0,3));
                result.append(x.substring(6,7));
                result.append(x.substring(4,6));
                result.append("G");
                result.append(x.substring(7));
                t=new Node(result.toString());
                t.change=x.substring(6,7)+"U";
                t.cost=costs[Integer.parseInt(x.substring(6,7))];
                y.addLast(t);

                break;
            case 4:
                result.append(x.substring(0,3));
                result.append("G");
                result.append(x.substring(3,4));
                result.append(x.substring(5));
                t=new Node(result.toString());
                t.change=x.substring(3,4)+"R";
                t.cost=costs[Integer.parseInt(x.substring(3,4))];
                y.addLast(t);
    
                result=new StringBuilder();
                result.append(x.substring(0,4));
                result.append(x.substring(5,6));
                result.append("G");
                result.append(x.substring(6));
                t=new Node(result.toString());
                t.change=x.substring(5,6)+"L";
                t.cost=costs[Integer.parseInt(x.substring(5,6))];
                y.addLast(t);
    
                result=new StringBuilder();
                result.append(x.substring(0,1));
                result.append("G");
                result.append(x.substring(2,4));
                result.append(x.substring(1,2));
                result.append(x.substring(5));
                t=new Node(result.toString());
                t.change=x.substring(1,2)+"D";
                t.cost=costs[Integer.parseInt(x.substring(1,2))];
                y.addLast(t);
                
                result=new StringBuilder();
                result.append(x.substring(0,4));
                result.append(x.substring(7,8));
                result.append(x.substring(5,7));
                result.append("G");
                result.append(x.substring(8,9));
                t=new Node(result.toString());
                t.change=x.substring(7,8)+"U";
                t.cost=costs[Integer.parseInt(x.substring(7,8))];
                y.addLast(t);

                break;
            case 5:
                result.append(x.substring(0,2));
                result.append("G");
                result.append(x.substring(3,5));
                result.append(x.substring(2,3));
                result.append(x.substring(6));
                t=new Node(result.toString());
                t.change=x.substring(2,3)+"D";
                t.cost=costs[Integer.parseInt(x.substring(2,3))];
                y.addLast(t);
    
                result=new StringBuilder();
                result.append(x.substring(0,4));
                result.append("G");
                result.append(x.substring(4,5));
                result.append(x.substring(6));
                t=new Node(result.toString());
                t.change=x.substring(4,5)+"R";
                t.cost=costs[Integer.parseInt(x.substring(4,5))];
                y.addLast(t);
    
                result=new StringBuilder();
                result.append(x.substring(0,5));
                result.append(x.substring(8,9));
                result.append(x.substring(6,8));
                result.append("G");
                t=new Node(result.toString());
                t.change=x.substring(8,9)+"U";
                t.cost=costs[Integer.parseInt(x.substring(8,9))];
                y.addLast(t);

                break;
            case 6:
                result.append(x.substring(0,3));
                result.append("G");
                result.append(x.substring(4,6));
                result.append(x.substring(3,4));
                result.append(x.substring(7));
                t=new Node(result.toString());
                t.change=x.substring(3,4)+"D";
                t.cost=costs[Integer.parseInt(x.substring(3,4))];
                y.addLast(t);
    
                result=new StringBuilder();
                result.append(x.substring(0,6));
                result.append(x.substring(7,8));
                result.append("G");
                result.append(x.substring(8,9));
                t=new Node(result.toString());
                t.change=x.substring(7,8)+"L";
                t.cost=costs[Integer.parseInt(x.substring(7,8))];
                y.addLast(t);

                break;
            case 7:
                result.append(x.substring(0,4));
                result.append("G");
                result.append(x.substring(5,7));
                result.append(x.substring(4,5));
                result.append(x.substring(8,9));
                t=new Node(result.toString());
                t.change=x.substring(4,5)+"D";
                t.cost=costs[Integer.parseInt(x.substring(4,5))];
                y.addLast(t);
    
                result=new StringBuilder();
                result.append(x.substring(0,6));
                result.append("G");
                result.append(x.substring(6,7));
                result.append(x.substring(8,9));
                t=new Node(result.toString());
                t.change=x.substring(6,7)+"R";
                t.cost=costs[Integer.parseInt(x.substring(6,7))];
                y.addLast(t);
    
                result=new StringBuilder();
                result.append(x.substring(0,7));
                result.append(x.substring(8,9));
                result.append("G");
                t=new Node(result.toString());
                t.change=x.substring(8,9)+"L";
                t.cost=costs[Integer.parseInt(x.substring(8,9))];
                y.addLast(t);

                break;
            case 8:
                result.append(x.substring(0,5));
                result.append("G");
                result.append(x.substring(6,8));
                result.append(x.substring(5,6));
                t=new Node(result.toString());
                t.change=x.substring(5,6)+"D";
                t.cost=costs[Integer.parseInt(x.substring(5,6))];
                y.addLast(t);
    
                result=new StringBuilder();
                result.append(x.substring(0,7));
                result.append("G");
                result.append(x.substring(7,8));
                t=new Node(result.toString());
                t.change=x.substring(7,8)+"R";
                t.cost=costs[Integer.parseInt(x.substring(7,8))];
                y.addLast(t);

                break;
        }

        return y;
    }
}