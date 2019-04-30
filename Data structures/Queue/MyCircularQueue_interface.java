public interface MyCircularQueue_interface<E>
{
    public void enqueue(E o);
	public E dequeue() throws EmptyQueueException;
	public E front() throws EmptyQueueException;
	public int size();
	public boolean isEmpty();
}