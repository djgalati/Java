package p6_package;

/**
 *
 * @author djg32
 */
public class QueueClass {
    /**
     * Variable holding the Queue
     */
    private BasicLinkedListClass queueData;
    
    /**
     * Default Constructor
     */
    QueueClass()
    {
        queueData = new BasicLinkedListClass();
    }
    
    /**
     * Copy constructor
     * @param copied  QueueClass object to be copied
     */
    QueueClass(QueueClass copied)
    {
        queueData = new BasicLinkedListClass(copied.queueData);
    }
    
    /**
     * Clears queue
     */
    public void clear()
    {
        queueData.clear();
    }
    
    /**
     * Displays queue as comma-delimited list
     */
    public void displayQueue()
    {
        int iteration;
        if(isEmpty())
        {
            System.out.println("No Data");
        }
        else
        {
            System.out.print("Queue Tail-> ");
            for(iteration=queueData.getCurrentSize();iteration>0;iteration--)
            {
                System.out.print(queueData.getAtIndex(iteration)+ " ");
            }
            System.out.print("<- Queue Front");
        }
        System.out.println();
    }
    
    /**
     * Enqueues value
     * @param newValue  Value to be enqueued
     */
    public void enqueue(int newValue)
    {
        if(isEmpty())
        {
            queueData = new BasicLinkedListClass();
            queueData.setAtIndex(0, newValue, queueData.REPLACE);
        }
        else
        {
            queueData.setAtIndex(queueData.getCurrentSize(), newValue, 
            queueData.INSERT_BEFORE);
        }
    }
    
    /**
     * Removes and returns value from front of queue
     * @return integer value if successful, FAILED_ACCESS if not
     */
    public int dequeue()
    {
        return queueData.removeAtIndex(queueData.getCurrentSize());
    }
    
    /**
     * Reports queue empty state
     * @return Boolean evidence of empty list
     */
    public boolean isEmpty()
    {
        if(queueData.isEmpty()) return true;
        return false;
    }
    
    /**
     * Provides peek at front of queue
     * @return integer value if successful, FAILED_ACCESS if not
     */
    public int peekFront()
    {
        return queueData.getAtIndex(queueData.getCurrentSize());
    }
}
