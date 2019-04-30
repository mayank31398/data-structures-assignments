public class Node
{
    String state;
    double distance=Double.POSITIVE_INFINITY;
    Node previous=null;
    int moves=0;
    String change;
    int cost;

    public Node(String x)
    {
        state=x;
    }
    
    public Node(String x,double y)
    {
        state=x;
        distance=y;
    }
}