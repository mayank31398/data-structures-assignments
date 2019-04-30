public interface MyArrayStack_interface<E>
{
	public void push(E o);
	public E pop() throws EmptyStackException;
	public E top() throws EmptyStackException;
	public int size();
	public boolean isEmpty();
}