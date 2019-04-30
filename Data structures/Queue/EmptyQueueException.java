public class EmptyQueueException extends Exception
{
    public EmptyQueueException()
    {
        super("Sorry, the queue is empty.");
    }
}