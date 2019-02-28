package p5_package;

/**
 *Stack Class, this class uses the BasicArrayClass to structure its data while 
 * the class itself adds functionality to the stack
 * @author djg32
 */
public class IteratorClass extends BasicArrayClass{
    /**
     * Current index of iterator
     */
    private int currentIndex;
    
    /**
     * Constant character for display
     */
    private final char LEFT_BRACKET = 91;
    
    /**
     * Constant character for display
     */
    private final char	RIGHT_BRACKET = 93;
    
    /**
     * Constant character for display
     */
    private final char SPACE = 32;
    
    BasicArrayClass stackData;
    
    /**
     * Default constructor for IteratorClass
     */
    IteratorClass()
    {
        stackData = new BasicArrayClass();
        currentIndex=0;
    }
    
    /**
     * Initialization constructor for IteratorClass
     * @param initCapacity integer value at which to set initial array capacity
     */
    IteratorClass(int initCapacity)
    {
        stackData= new BasicArrayClass(initCapacity);
        currentIndex=0;
    }
    
    /**
     * Copy constructor for IteratorClass
     * @param copied teratorClass object to be copied
     */
    IteratorClass(IteratorClass copied)
    {
        stackData= new BasicArrayClass(copied);
        currentIndex=0;
    }
    
    /**
     * Clears array
     */
    public void clear()
    {
        super.clear();
        currentIndex=0;
    }
    
    /**
     * Gets value at iterator cursor location
     * @return integer value returned; FAILED_ACCESS if not found
     */
    public int getAtCurrent()
    {
        if(stackData.getAtIndex(currentIndex)==BasicArrayClass.FAILED_ACCESS)
        {
            return BasicArrayClass.FAILED_ACCESS;
        }
        return stackData.getAtIndex(currentIndex);
    }
    
    /**
     * Reports if iterator cursor is at beginning
     * <p>
     * Must consider whether list is empty
     * @return Boolean result of action; true if at beginning, false otherwise
     */
    public boolean isAtBeginning()
    {
        if(isEmpty())
        {
            return false;
        }
        else if(currentIndex==0)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Reports if iterator cursor is at end
     * <p>
     * Must consider whether list is empty
     * @return Boolean result of action; true if at end, false otherwise
     */
    public boolean isAtEnd()
    {
        if(isEmpty())
        {
            return false;
        }
        else if(currentIndex==stackData.getCurrentCapacity()-1)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Reports if list is empty
     * @return Boolean result of action; true if empty, false otherwise
     */
    public boolean isEmpty()
    {
        return super.isEmpty();
    }
    
    /**
     * If possible, moves iterator cursor one position to the right, or next
     * <p>
     * Must consider whether list is empty
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean moveNext()
    {
        if(stackData.isEmpty()||
        stackData.getCurrentCapacity()==stackData.getCurrentSize())
        {
            return false;
        }
        if(currentIndex>=stackData.getCurrentCapacity())
        {
            currentIndex+=1;
            return true;
        }
        return false;
        
    }
    
    /**
     * If possible, moves iterator cursor one position to the left, or previous
     * <p>
     * Must consider whether list is empty
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean movePrev()
    {
        if(stackData.isEmpty())
        {
            return false;
        }
        if(currentIndex>=1)
        {
            currentIndex-=1;
            return true;
        }
        return false;
    }
    
    /**
     * Removes and returns a data value from the iterator cursor position
     * <p>
     * Note: cursor must be located at succeeding element unless last item 
     * removed
     * @return integer value removed from list, or FAILED_ACCESS if not found
     */
    public int removeAtCurrent()
    {
        return stackData.removeAtIndex(currentIndex);
    }
    
    /**
     * Replaces value at iterator cursor with new value
     * @param newValue integer value to be inserted in list
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean replaceAtCurrent(int newValue)
    {
        return stackData.setAtIndex(currentIndex, newValue, REPLACE);
    }
    
    /**
     * Shows space-delimited list with cursor location indicated
     */
    public void runDiagnosticDisplay()
    {
        if(isEmpty())
        {
            System.out.println("No Data");
        }
        else
        {
            boolean flag=false;
            int iteration;
            for(iteration=0;iteration<this.getCurrentSize();iteration++)
            {
                if(iteration==currentIndex)
                {
                    System.out.print(LEFT_BRACKET);
                    System.out.print(this.getAtIndex(iteration));
                    System.out.print(RIGHT_BRACKET + SPACE); 
                }
                else
                {
                    System.out.print(this.getAtIndex(iteration)+ SPACE);
                }
                flag=moveNext();
                
            }
        }
        System.out.println();
    }
    
    /**
     * Inserts new value after value at iterator cursor
     * <p>
     * Note: Current value must remain the same after data set
     * @param newValue integer value to be inserted in list
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean setAfterCurrent(int newValue)
    {
        return stackData.setAtIndex(currentIndex, newValue, INSERT_AFTER);
    }
    
    /**
     * Inserts new before value at iterator cursor
     * <p>
     * Note: Current value must remain the same after data set
     * @param newValue integer value to be inserted in list
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean setBeforeCurrent(int newValue)
    {
        return stackData.setAtIndex(currentIndex, newValue, INSERT_BEFORE);
    }
    
    /**
     * Sets iterator cursor to beginning of list
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean setToBeginning()
    {
        if(isEmpty())
        {
            return false;
        }
        currentIndex=0;
        return true;
    }
    
    /**
     * Sets iterator cursor to the end of the list
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean setToEnd()
    {
        if(isEmpty())
        {
            return false;
        }
        currentIndex=stackData.getCurrentSize()-1;
        stackData.checkForResize();
        return true;
    }
}
