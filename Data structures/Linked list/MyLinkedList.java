interface MyLinkedList_interface<E>
{
    public void addFirst(E x);
    public void addLast(E x);
    public void addAfter(MyListNode<E> x, E y);
    public E removeAfter(MyListNode<E> x) throws EmptyListException;
    public int size();
    public boolean isEmpty();
    public E getFirst() throws EmptyListException;
    public E getLast() throws EmptyListException;
    public MyDoublyListNode<E> getHead();
    public MyDoublyListNode<E> getTail();
}

public class MyLinkedList<E> implements MyLinkedList_interface<E>
{
    private MyListNode<E> head=null;
    private MyListNode<E> tail=null;
    private int num_nodes;

    public void addFirst(E x)
    {        
        if(isEmpty())
        {
            head=new MyListNode<E>(x);
            tail=head;
        }
        else
            head=new MyListNode<E>(x,head);

        num_nodes++;
    }s

    public void addLast(E x)
    {
        if(isEmpty())
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
            addFirst(y);
        else
            x.setNext(new MyListNode<E>(y,x.getNext()));
        
        num_nodes++;
    }

    public E removeAfter(MyListNode<E> x) throws EmptyListException
    {
        if(isEmpty())
            throw new EmptyListException();
        
        if(x==null)
        {
            MyListNode<E> y=head;
            head=y.getNext();
            num_nodes--;

            return y.getElement();
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
        if(isEmpty())
            throw new EmptyListException();

        return head.getElement();        
    }

    public E getLast() throws EmptyListException
    {
        if(isEmpty())
            throw new EmptyListException();
        
        return tail.getElement();
    }

    public MyDoublyListNode<E> getHead()
    {
        return head;
    }

    public MyDoublyListNode<E> getTail()
    {
        return tail;
    }
}