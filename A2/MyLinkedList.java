public class MyLinkedList<E>
{
    protected MyListNode<E> head;
    protected MyListNode<E> tail;
    private int num_nodes;

    public void addFirst(E x)
    {
        head=new MyListNode<E>(x,head);
        num_nodes++;
    }

    public void addLast(E x)
    {
        if(this.isEmpty())
        {
            tail=new MyListNode<E>(x);
            head=tail;
        }
        else
        {
            tail.setNext(new MyListNode<E>(x));
            tail=tail.getNext();
        }

        num_nodes++;
    }

    public void addAfter(MyListNode<E> x, E y)
    {
        if(x==null)
            head=new MyListNode<E>(y,head);
        else
            x.setNext(new MyListNode<E>(y,x.getNext()));
        num_nodes++;
    }

    public E removeAfter(MyListNode<E> x) throws EmptyListException
    {
        if(x==null)
        {
            if(head!=null)
            {
                MyListNode<E> y=head;
                head=y.getNext();
                return y.getElement();                
            }

            throw new EmptyListException();
        }

        MyListNode<E> y=x.getNext();
        x.setNext(y.getNext());
        num_nodes--;
        return y.getElement();
    }

    public int size()
    {
        return num_nodes;
    }

    public boolean isEmpty()
    {
        return num_nodes==0;
    }

    public E getFirst() throws EmptyListException
    {
        if(this.isEmpty())
            throw new EmptyListException();

        return head.getElement();        
    }

    public void print() throws EmptyListException
    {
        if(head==null)
            throw new EmptyListException();

        System.out.print(head.getElement());
        for(MyListNode<E> n=head.getNext();n!=null;n=n.getNext())
            System.out.print(", "+n.getElement());
    }
}