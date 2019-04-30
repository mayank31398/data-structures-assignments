interface MyListNode_interface<E>
{
    public MyListNode<E> getNext();
    public E getElement();
    public void changeElement(E x);
    public void setNext(MyListNode<E> x);
    public boolean hasNext();
}

public class MyListNode<E> implements MyListNode_interface<E>
{
    private E element;
    private MyListNode<E> next;

    public MyListNode(E x)
    {
        this(x,null);
    }

    public MyListNode(E x,MyListNode<E> y)
    {
        element=x;
        next=y;
    }

    public MyListNode<E> getNext()
    {
        return next;
    }

    public E getElement()
    {
        return element;
    }

    public void changeElement(E x)
    {
        element=x;
    }

    public void setNext(MyListNode<E> x)
    {
        next=x;
    }

    public boolean hasNext()
    {
        return next!=null;
    }
}