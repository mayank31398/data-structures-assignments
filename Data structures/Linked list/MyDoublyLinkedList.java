interface MyDoublyLlinkedList_interface<E>
{
    public void addFirst(E x);
    public void addLast(E x);
    public void addAfter(MyDoublyListNode<E> x,E y);
    public E removeAfter(MyDoublyListNode<E> x) throws EmptyListException;
    public int size();
    public boolean isEmpty();
    public E getFirst() throws EmptyListException;
    public E getLast() throws EmptyListException;
    public MyDoublyListNode<E> getHead();
    public MyDoublyListNode<E> getTail();
}

public class MyDoublyLinkedList<E> implements MyDoublyLlinkedList_interface<E>
{
    private MyDoublyListNode<E> head=null;
    private MyDoublyListNode<E> tail=null;
    private int num_nodes;

    public void addFirst(E x)
    {
        if(isEmpty())
        {
            head=new MyDoublyListNode<E>(x);
            tail=head;
        }
        else
        {
            MyDoublyListNode<E> y=null;
            head=new MyDoublyListNode<E>(x,y,head);
            head.getNext().setPrevious(head);
        }

        num_nodes++;
    }

    public void addLast(E x)
    {
        if(isEmpty())
        {
            tail=new MyDoublyListNode<E>(x);
            head=tail;
        }
        else
        {
            MyDoublyListNode<E> y=null;
            tail.setNext(new MyDoublyListNode<E>(x,tail,y));
            tail=tail.getNext();
        }

        num_nodes++;
    }

    public void addAfter(MyDoublyListNode<E> x,E y)
    {
        if(x==null)
            addFirst(y);
        else
        {
            x.setNext(new MyDoublyListNode<E>(y,x,x.getNext()));
            x.getNext().getNext().setPrevious(x.getNext());
        }

        num_nodes++;
    }

    public E removeAfter(MyDoublyListNode<E> x) throws EmptyListException
    {
        if(isEmpty())
            throw new EmptyListException();
        
        if(x==null)
        {
            MyDoublyListNode<E> y=head;
            head=y.getNext();
            head.setPrevious(null);
            num_nodes--;

            return y.getElement();
        }

        MyDoublyListNode<E> y=x.getNext();        
        x.setNext(y.getNext());
        y.getNext().setPrevious(x);
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

    public void print() throws EmptyListException
    {
        if(isEmpty())
            throw new EmptyListException();

        System.out.print(head.getElement());
        for(MyDoublyListNode<E> n=head.getNext();n!=null;n=n.getNext())
            System.out.print(", "+n.getElement());
    }
}