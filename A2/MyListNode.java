public class MyListNode<E>
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