interface MyDoublyListNode_interface<E>
{
    public E getElement();
    public MyDoublyListNode<E> getPrevious();
    public boolean hasPrevious();
    public void setPrevious(MyDoublyListNode<E> x);
    public MyDoublyListNode<E> getNext();
    public boolean hasNext();
    public void setNext(MyDoublyListNode<E> x);
    public void changeElement(E x);
}

public class MyDoublyListNode<E> implements MyDoublyListNode_interface<E>
{
    private E element;
    private MyDoublyListNode<E> previous;
    private MyDoublyListNode<E> next;

    public MyDoublyListNode(E x)
    {
        this(x,null,null);
    }

    public MyDoublyListNode(E x,MyDoublyListNode<E> y,MyDoublyListNode<E> z)
    {
        element=x;
        previous=y;
        next=z;
    }

    public E getElement()
    {
        return element;
    }

    public MyDoublyListNode<E> getPrevious()
    {
        return previous;
    }

    public boolean hasPrevious()
    {
        return previous!=null;
    }

    public void setPrevious(MyDoublyListNode<E> x)
    {
        previous=x;
    }
    
    public MyDoublyListNode<E> getNext()
    {
        return next;
    }

    public boolean hasNext()
    {
        return next!=null;
    }

    public void setNext(MyDoublyListNode<E> x)
    {
        next=x;
    }

    public void changeElement(E x)
    {
        element=x;
    }
}