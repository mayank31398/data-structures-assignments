package col106.a3;

import java.util.ArrayList;
import java.util.Vector;
import java.util.List;

public class BTree<Key extends Comparable<Key>,Value> implements DuplicateBTree<Key,Value>
{
    Node<Key,Value> root=null;
    int b;
    int size=0;

    public BTree(int b) throws bNotEvenException
    {
        if(b%2!=0)
            throw new bNotEvenException();
        this.b=b;
    }

    @Override
    public boolean isEmpty()
    {
        return size==0;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public int height()
    {
        if(root==null)
            return -1;
        
        int height=0;
        Node<Key,Value> x=root;
        while(x.children.size()!=0)
        {
            height++;
            x=x.children.get(0);
        }
        
        return height;
    }

    @Override
    public List<Value> search(Key key) throws IllegalKeyException
    {
        if(root==null)
        {
            List<Value> x=new Vector<Value>();
            return x;
        }

        return root.LookFor(key);
    }

    private void SplitNode(Node<Key,Value> x)
    {
        if(x==root)
            root=x.Split();
        else
            x.Split();
    }

    @Override
    public void insert(Key key, Value val)
    {
        size++;

        if(size==1)
        {
            root=new Node<Key,Value>(b);
            root.blocks.add(new Block<Key,Value>(key, val));
            root.parent=null;
        }
        else
            insert(root,key,val);
    }

    private void insert(Node<Key,Value> x, Key key, Value val)
    {
        if(x.blocks.size()==b-1)
        {
            SplitNode(x);
            
            int index=x.parent.blocks.size();
            for(int i=0;i<x.parent.blocks.size();i++)
                if(key.compareTo(x.parent.blocks.get(i).key)<0)
                {
                    index=i;
                    break;
                }

            insert(x.parent.children.get(index),key,val);
        }
        else
        {
            int index=x.blocks.size();
            for(int i=0;i<x.blocks.size();i++)
                if(key.compareTo(x.blocks.get(i).key)<0)
                {
                    index=i;
                    break;
                }
            
            if(x.children.size()!=0)
                insert(x.children.get(index),key,val);
            else
                x.InsertInBetween(key,val);
        }
    }

    public void Remove(Key key)
    {        
        root.Remove(key);

        if(root.blocks.size()==0)
        {
            if(root.children.size()==0)
                root=null;
            else
                root=root.children.get(0);
        }
    }

    @Override
    public void delete(Key key) throws IllegalKeyException
    {
        if(size==0)
            throw new IllegalKeyException();

        Key a=root.getMin();
        Key b=root.getMax();
        
        if(a.compareTo(b)==0 && key.compareTo(a)==0)
        {
            root=null;
            size=0;
            return;
        }
        else if(a.compareTo(b)==0 && !((key).compareTo(a)==0))
            throw new IllegalKeyException();
        else
        {
            Vector<Value> x=root.LookFor(key);
            size-=x.size();
    
            if(x.size()==0)
                throw new IllegalKeyException();
    
            for(int i=0;i<x.size();i++)
                Remove(key);
        }
    }

    public String toString()
    {
        if(size==0)
            return "[]";
        return root.toString();
    }
}

class Block<Key extends Comparable<Key>,Value>
{
    Key key;
    Value value;

    public Block(Key x, Value y)
    {
        key=x;
        value=y;
    }
}

class Node<Key extends Comparable<Key>,Value>
{
    ArrayList<Block<Key,Value>> blocks;
    ArrayList<Node<Key,Value>> children;
    Node<Key,Value> parent;
    int t;

    public Node(int b)
    {
        blocks=new ArrayList<Block<Key,Value>>(b-1);
        children=new ArrayList<Node<Key,Value>>(b);
        t=b/2;
    }

    public void InsertInBetween(Key x,Value y)
    {
        Block<Key,Value> a=new Block<Key,Value>(x,y);
        int num_blocks=blocks.size();
        int index=num_blocks;

        for(int i=0;i<num_blocks;i++)
            if((a.key).compareTo(blocks.get(i).key)<0)
            {
                index=i;
                break;
            }

        blocks.add(index,a);
    }

    public Node<Key,Value> Split()
    {
        int index=0;
        Node<Key,Value> x=new Node<Key,Value>(2*t);
        Node<Key,Value> y=new Node<Key,Value>(2*t);

        for(int i=0;i<2*t-1;i++)
            if(i<t-1)
                x.blocks.add(blocks.get(i));
            else if(i>t-1)
                y.blocks.add(blocks.get(i));

        if(children.size()!=0)
            for(int i=0;i<2*t;i++)
                if(i<t)
                {
                    x.children.add(children.get(i));
                    children.get(i).parent=x;
                }
                else if(i>=t)
                {
                    y.children.add(children.get(i));
                    children.get(i).parent=y;
                }

        if(parent!=null)
        {
            int n=parent.blocks.size();
            
            for(int i=0;i<n+1;i++)
                if(parent.children.get(i)==this)
                {
                    index=i;
                    break;
                }
            
            parent.blocks.add(index,blocks.get(t-1));
            parent.children.add(index+1,y);
            parent.children.set(index,x);
            x.parent=parent;
            y.parent=parent;
        }
        else
        {
            Node<Key,Value> q=new Node<Key,Value>(2*t);
            q.parent=null;
            q.blocks.add(blocks.get(t-1));
            q.children.add(x);
            x.parent=q;
            q.children.add(y);
            y.parent=q;
            parent=q;
        }
        
        return x.parent;
    }

    public Vector<Value> LookFor(Key key)
    {
        Vector<Value> x=new Vector<Value>();
        int start=blocks.size();
        int end=blocks.size();
        Vector<Value> y=new Vector<Value>();

        if(children.size()==0)
            for(int i=0;i<blocks.size();i++)
            {
                if((key).compareTo((blocks.get(i).key))==0)
                    x.add(blocks.get(i).value);
                else if((key).compareTo((blocks.get(i).key))<0)
                    break;
            }
        else
        {
            for(int i=0;i<blocks.size();i++)
                if((key).compareTo((blocks.get(i).key))<=0)
                {
                    start=i;
                    break;
                }
            
            for(int i=start;i<blocks.size();i++)
                if((key).compareTo((blocks.get(i).key))<0)
                {
                    end=i;
                    break;
                }

            for(int i=start;i<=end;i++)
            {
                y=children.get(i).LookFor(key);
                for(int j=0;j<y.size();j++)
                    x.add(y.get(j));
                
                if(i<end)
                    x.add(blocks.get(i).value);
            }
        }

        return x;
    }

    public Key getMin()
    {
        Node<Key,Value> x=this;
        while(x.children.size()!=0)
            x=x.children.get(0);
        return x.blocks.get(0).key;
    }

    public Key getMax()
    {
        Node<Key,Value> x=this;
        while(x.children.size()!=0)
            x=x.children.get(x.blocks.size());
        return x.blocks.get(x.blocks.size()-1).key;
    }

    public String toString()
    {
        StringBuilder x=new StringBuilder();
        x.append("[");

        if(children.size()==0)
        {
            x.append(blocks.get(0).key+"="+blocks.get(0).value);
            for(int i=1;i<blocks.size();i++)
                x.append(", "+blocks.get(i).key+"="+blocks.get(i).value);
            x.append("]");
        }
        else
        {
            for(int i=0;i<blocks.size();i++)
                x.append(children.get(i).toString()+", "+blocks.get(i).key+"="+blocks.get(i).value+", ");
            x.append(children.get(blocks.size()).toString()+"]");
        }

        return x.toString();
    }

    public void Remove(Key key)
    {
        int index=blocks.size();
        for(int i=0;i<blocks.size();i++)
            if((key).compareTo((blocks.get(i).key))<=0)
            {
                index=i;
                break;
            }

        if(index<blocks.size() && (key).compareTo((blocks.get(index).key))==0)
        {
            if(children.size()==0)
                LeafDelete(index);
            else
                NonLeafDelete(index);
        }
        else
        {
            if(children.size()==0)
                return;

            boolean e=(index==blocks.size());

            if(children.get(index).blocks.size()<t)
                Make(index);
            
            if(index>blocks.size() && e)
                children.get(index-1).Remove(key);
            else
                children.get(index).Remove(key);
        }
    }

    public void Crush(int index, boolean first)
    {
        Node<Key,Value> x=children.get(index);

        if(first)
        {
            while(x.children.size()!=0)
            {
                if(x.children.get(0).blocks.size()<t)
                    x.Make(0);
                x=x.children.get(0);
            }

            x.blocks.remove(0);
        }
        else
        {
            while(x.children.size()!=0)
            {
                if(x.children.get(x.blocks.size()).blocks.size()<t)
                    x.Make(x.blocks.size());
                x=x.children.get(x.blocks.size());
            }

            x.blocks.remove(x.blocks.size()-1);
        }
    }

    public void LeafDelete(int index)
    {
        blocks.remove(index);
    }

    public void NonLeafDelete(int index)
    {
        Key a=blocks.get(index).key;
        
        if(children.get(index).blocks.size()>=t)
        {
            Node<Key,Value> x=JustBefore(index);
            blocks.set(index, x.blocks.get(x.blocks.size()-1));
            Crush(index,false);
        }
        else if(children.get(index+1).blocks.size()>=t)
        {
            Node<Key,Value> x=JustAfter(index);
            blocks.set(index,x.blocks.get(0));
            Crush(index+1,true);
        }
        else
        {
            Merge(index);
            children.get(index).Remove(a);
        }
    }

    public void Make(int index)
    {
        if(index!=0 && children.get(index-1).blocks.size()>=t)
            RotateRight(index);
        else if(index<blocks.size() && children.get(index+1).blocks.size()>=t)
            RotateLeft(index);
        else if(index<blocks.size())
            Merge(index);
        else
            Merge(index-1);
    }

    public Node<Key,Value> JustBefore(int index)
    {
        Node<Key,Value> x=children.get(index);
        while(x.children.size()!=0)
            x=x.children.get(x.children.size()-1);
        return x;
    }

    public Node<Key,Value> JustAfter(int index)
    {
        Node<Key,Value> x=children.get(index+1);
        while(x.children.size()!=0)
            x=x.children.get(0);
        return x;
    }

    public void RotateLeft(int index)
    {
        children.get(index).blocks.add(blocks.get(index));
        blocks.set(index,children.get(index+1).blocks.remove(0));

        if(children.get(0).children.size()!=0)
            children.get(index).children.add(children.get(index+1).children.remove(0));
    }

    public void RotateRight(int index)
    {
        children.get(index).blocks.add(0,blocks.get(index-1));
        blocks.set(index-1,children.get(index-1).blocks.remove(children.get(index-1).blocks.size()-1));

        if(children.get(0).children.size()!=0)
            children.get(index).children.add(0,children.get(index-1).children.remove(children.get(index-1).children.size()-1));
    }

    public void Merge(int index)
    {
        children.get(index).blocks.add(blocks.remove(index));
        for(int i=0;i<children.get(index+1).blocks.size();i++)
            children.get(index).blocks.add(children.get(index+1).blocks.get(i));
        
        for(int i=0;i<children.get(index+1).children.size();i++)
            children.get(index).children.add(children.get(index+1).children.get(i));
        
        children.remove(index+1);
    }
}