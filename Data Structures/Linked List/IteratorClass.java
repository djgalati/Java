package p6_package;

/**
 *
 * @author djg32
 */
public class IteratorClass extends BasicLinkedListClass{
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
    
    
    /**
     * Default constructor for IteratorClass
     */
    IteratorClass()
    {
        currentIndex=0;
    }
    
    /**
     * Copy constructor for IteratorClass
     * @param copied IteratorClass object to be copied
     */
    IteratorClass(IteratorClass copied)
    {
        currentIndex=copied.currentIndex;
    }
    
    /**
     * Clears array
     */
    @Override
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
        return getAtIndex(currentIndex);
    }
    
    /**
     * Reports if iterator cursor is at beginning
     * <p>
     * Note: Empty list does not exist
     * @return Boolean result of action; true if at beginning, false otherwise
     */
    public boolean isAtBeggining()
    {
        if(currentIndex!=0) return false;
        return true;
    }
    
    /**
     * Reports if iterator cursor is at end
     * <p>
     * Note: Empty list does not exist
     * @return Boolean result of action; true if at end, false otherwise
     */
    public boolean isAtEnd()
    {
        if(currentIndex==getCurrentSize()) return true;
        return false;
    }
    
    /**
     * Reports if list is empty
     * @Override isEmpty in class BasicLinkedListClass
     * @return Boolean result of action; true if empty, false otherwise
     */
    @Override
    public boolean isEmpty()
    {
        return super.isEmpty();
    }
    
    /**
     * Moves iterator cursor one position to the right, or next, of existing 
     * list
     * <p>
     * Note: Empty list does not exist
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean moveNext()
    {
        if(isEmpty() || isAtEnd())
        {
            return false;
        }
        currentIndex+=1;
        return true;
        
    }
    
    /**
     * Moves iterator cursor one position to the left, or previous, of 
     * existing list
     * <p>
     * Note: Empty list does not exist
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean movePrev()
    {
        if(isEmpty() || isAtBeggining())
        {
            return false;
        }
        currentIndex-=1;
        return true;
    }
    
    /**
     * Removes and returns a data value from the iterator cursor position
     * <p>
     * Note: cursor must remain in same relative position after removal unless
     * the last item is removed
     * @return integer value removed from list
     */
    public int removeAtCurrent()
    {
        int temp = super.removeAtIndex(currentIndex);
        if(isAtEnd()) currentIndex--;
        return temp;
    }
    
    /**
     * Replaces value at iterator cursor with new value
     * @param newValue integer value to be inserted in list
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean replaceAtCurrent(int newValue)
    {
        return super.setAtIndex(currentIndex, newValue, REPLACE);
    }
    
    /**
     * Shows space-delimited list with cursor location indicated
     * <p>
     * Indicates cursor with left/right brackets (e.g., "[##]")
     */
    public void runDiagnosticDisplay()            
    {
        int iteration;
        if(isEmpty())
        {
            System.out.println("No Data");
        }
        for(iteration=0;iteration<getCurrentSize();iteration++)
        {
            if(iteration==currentIndex)
            {
                System.out.print(LEFT_BRACKET);
                System.out.print(getAtIndex(iteration));
                System.out.print(RIGHT_BRACKET + SPACE); 
            }
            else
            {
                System.out.print(getAtIndex(iteration)+ SPACE);
            }
        }
        System.out.println();
    }
    
    /**
     * Sets value to location after current index
     * @param newValue integer value to be inserted in list
     * @return Boolean result of operation
     */
    public boolean setAfterCurrent(int newValue)
    {
        return super.setAtIndex(currentIndex, newValue, INSERT_AFTER);
    }
    
    /**
     * Sets value to location before current index
     * @param newValue integer value to be inserted in list
     * @return Boolean result of operation
     */
    public boolean setBeforeCurrent(int newValue)
    {
        return super.setAtIndex(currentIndex, newValue, INSERT_BEFORE);
    }
    
    /**
     * Sets iterator cursor to beginning of list
     * <p>
     * Note: Empty list does not exist
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
     * <p>
     * Note: Empty list does not exist
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean setToEnd()
    {
        if(isEmpty())
        {
            return false;
        }
        currentIndex=super.getCurrentSize()-1;
        return true;
    }
}
