import java.util.HashMap;

public class ArrayHeap
{
    Node[] heap;
    int size=0;
    HashMap<String,Integer> indexes=new HashMap<String,Integer>();

    public ArrayHeap()
    {
        heap=new Node[362881];
        heap[0]=null;
    }
    
    public void put(Node x)
    {
        size++;
        heap[size]=x;
    }

    public void insert(Node x)
    {
        size++;
        heap[size]=x;

        int i=size;
        Node t;
        while(i/2!=0 && heap[i].distance<=heap[i/2].distance)
        {
            if(heap[i].distance<heap[i/2].distance || (heap[i].distance==heap[i/2].distance && heap[i].moves<heap[i/2].moves))
            {
                t=heap[i];
                heap[i]=heap[i/2];
                heap[i/2]=t;
                indexes.put(heap[i].state,i);
                indexes.put(heap[i/2].state,i/2);
            }

            i=i/2;
        }

        indexes.put(x.state,i);
    }

    public void HeapifyAll()
    {
        for(int i=size/2;i>=1;i--)
            HeapifyAt(i);
    }

    public Node getMin()
    {
        Node result=heap[1];
        heap[1]=heap[size];
        heap[size]=null;
        size--;
        HeapifyAt(1);
        return result;
    }

    public void Perc(int x)
    {
        Node t;
        int n=x/2;
        if(n!=0 && Compare(heap[n],heap[x])==1)
        {
            t=heap[n];
            heap[n]=heap[x];
            heap[x]=t;
            indexes.put(heap[n].state,n);
            indexes.put(heap[x].state,x);
            Perc(n);
        }
    }
    
    public void HeapifyAt(int x)
    {
        Node t;
        if(2*x==size)
        {
            t=heap[x];
            heap[x]=heap[2*x];
            heap[2*x]=t;
            indexes.put(heap[x].state,x);
            indexes.put(heap[2*x].state,2*x);
        }
        else if(2*x+1<=size)
        {
            if(Compare(heap[x],heap[2*x])==1 || Compare(heap[x],heap[2*x+1])==1)
            {
                if(Compare(heap[2*x],heap[2*x+1])==-1)
                {
                    t=heap[x];
                    heap[x]=heap[2*x];
                    heap[2*x]=t;
                    indexes.put(heap[x].state,x);
                    indexes.put(heap[2*x].state,2*x);
                    HeapifyAt(2*x);
                }
                else
                {
                    t=heap[x];
                    heap[x]=heap[2*x+1];
                    heap[2*x+1]=t;
                    indexes.put(heap[x].state,x);
                    indexes.put(heap[2*x+1].state,2*x+1);
                    HeapifyAt(2*x+1);
                }
            }
        }
    }

    public static int Compare(Node x,Node y)
    {
        if(x.distance<y.distance)
            return -1;
        else if(x.distance>y.distance)
            return 1;
        else
        {
            if(x.moves<y.moves)
                return -1;
            else if(x.moves>y.moves)
                return 1;
            else
                return 0;
        }
    }
}