package p5_package;


/**
 *Stack Class, this class uses the BasicArrayClass to structure its data while 
 * the class itself adds functionality to the stack
 * @author djg32
 */
public class StackClass {
    /**
     * A Stack of data structured around the BasicArrayClass
     */
    private BasicArrayClass stackData;
    
    /**
     * Stack default constructor
     */
    StackClass()
    {
        stackData = new IteratorClass();
    }
    
    /**
     * Stack initialization constructor
     * @param capacitySetting integer value for setting initial capacity of 
     * array 
     */
    public StackClass(int capacitySetting)
    {
        stackData = new IteratorClass(capacitySetting);
    }
    
    /**
     * Copy constructor
     * @param copied  StackClass object to be copied
     */
    public StackClass(StackClass copied)
    {
        stackData=copied.stackData;
    }
    
    /**
     * Clears stack
     */
    public void clear()
    {
        stackData.clear();
    }
    
    /**
     * Displays stack
     */
    public void displayStack()
    {
        int iteration;
        System.out.println("TOP STACK:");
        for(iteration=0;iteration<stackData.getCurrentSize()-1;iteration++)
        {
            System.out.println(stackData.getAtIndex(iteration)+", ");
        }
        System.out.println(stackData.getAtIndex(iteration));
        System.out.println(":BOTTOM STACK");
    }
    
    /**
     * Reports stack empty status
     * @return Boolean evidence of empty list
     */
    public boolean isEmpty()
    {
        return stackData.isEmpty();
    }
    
    /**
     * provides peek at top of stack
     * @return value if successful, FAILED_ACCESS if not
     */
    public int peekTop()
    {
        return stackData.getAtIndex(stackData.getCurrentSize()-1);
    }
    
    /**
     * Removes and returns data from top of stack
     * @return value if successful, FAILED_ACCESS if not
     */
    public int pop()
    {
        return stackData.removeAtIndex(stackData.getCurrentSize()-1);
    }
    
    /**
     * Places data item on top of the stack
     * @param newVal integer value to pushed onto stack
     */
    public void push(int newVal)
    {
        stackData.setAtIndex(stackData.getCurrentSize()-1, newVal, 
        stackData.INSERT_AFTER);
    }
}
