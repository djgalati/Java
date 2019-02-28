package p5_package;

/**
 * Project 5, week 6
 * @author djg32
 */
public class BasicArrayClass {
    /**
    *Member data
    */
    private int arrayCapacity;
    
    /**
    *Member data
    */
    private int arraySize;
    
    /**
     * Default constant capacity
     */
    private static final int DEFAULT_CAPACITY = 10;
    
    /**
     * Default failed access constant
     */
    static final int FAILED_ACCESS = -999999;
    
    /**
     * Constant used for allowing setAtIndex to insert value at index
     */
    static final int INSERT_AFTER = 1003;
    
    /**
     * Constant used for allowing setAtIndex to insert value at index
     */
    static final int INSERT_BEFORE = 1002;
    
    /**
     * Member - integer array
     */
    private int[] localArray;
    
    /**
     * Constant used for allowing accessAtIndex to remove an item
     */
    static final int REMOVE = 1004;
    
    /**
     * Constant used for allowing setAtIndex to replace value at index
     */
    static final int REPLACE = 1001;
    
    /**
     * Constant used for allowing accessAtIndex to retrieve an item
     */
    static final int RETRIEVE = 1005;
    
    /**
     * Default constructor, initializes array to default capacity
     */
    protected BasicArrayClass()
    {
        arrayCapacity=DEFAULT_CAPACITY;
        localArray=new int[arrayCapacity];
        arraySize=0;
    }
    
    /**
     *Copy constructor, initializes array to size and capacity of copied array,
     *then copies only the elements up to the given size
     * @param copied BasicArrayClass object to be copied
     */
    protected BasicArrayClass(BasicArrayClass copied)
    {
        int iteration;
        localArray=new int[copied.getCurrentCapacity()];
        for(iteration=0;iteration<copied.getCurrentCapacity();iteration++)
        {
            localArray[iteration]=copied.getAtIndex(iteration);
        }
        arrayCapacity=copied.getCurrentCapacity();
        arraySize=copied.getCurrentSize();
    }
    
    /**
     *Initializing constructor, initializes array to specified capacity
     *@param capacity integer maximum capacity specification for the array
     */
    protected BasicArrayClass(int capacity)
    {
        localArray=new int[capacity];
        arrayCapacity=capacity;
        arraySize=0;
    }
    
    /**
     * Utility method used by getAtIndex and removeAtIndex to access and 
     * possibly remove element depending on control code
     * <p>
     * Note: Uses only one loop
     * @param controlCode integer value with either RETRIEVE or REMOVE to 
     * control operations
     * @param index integer index of element to be retrieved or removed
     * @return integer value at element or FAILED_ACCESS if attempt to access 
     * data out of bounds
     */
    private int accessAtIndex(int controlCode,int index)
    {
        if(this.isEmpty())
        {
                return FAILED_ACCESS;
        }
        else if(controlCode==RETRIEVE && index<this.getCurrentSize())
        {
            return localArray[index];
        }
        else if(controlCode==REMOVE)
        {
            int iteration;
            int temp = localArray[index];
            for(iteration=index;iteration<this.getCurrentSize()-1;iteration++)
            {
                localArray[index]=localArray[index+1];
            }
            arraySize--;
            return temp;
        }
        else
        {
            return FAILED_ACCESS;
        }
    }
    
    /**
     * Checks for need to resize; if this is necessary, creates new array with 
     * double the original capacity, loads data from original array to new one, 
     * then sets localArray to new array
     */
    protected void checkForResize()
    {
        if(this.getCurrentCapacity()-1==this.getCurrentSize())
        {
            arrayCapacity*=2;
            int[] temp = new int[arrayCapacity];
            int iteration;
            for(iteration=0;iteration<arrayCapacity;iteration++)
            {
                temp[iteration]=localArray[iteration];
            }
            localArray=temp;
        }
    }
    
    /**
     * Clears array of all valid values by setting array size to zero, values 
     * remain in array but are not accessible
     */
    protected void clear()
    {
        arraySize=0;
    }
    
    /**
     * Accesses item in array at specified index if index within array size 
     * bounds
     * <p>
     * Note: Calls accessAtIndex with RETRIEVE to conduct action
     * @param accessIndex integer index of requested element value
     * @return integer accessed value if successful, FAILED_ACCESS if not
     */
    protected int getAtIndex(int accessIndex)
    {
        if(accessIndex<getCurrentSize())
        {
            return this.accessAtIndex(RETRIEVE, accessIndex);
        }
        return FAILED_ACCESS;
    }
    
    /**
     * Gets current capacity of array
     * <p>
     * Note: capacity of array indicates number of values the array can hold
     * @return integer capacity of array
     */
    protected int getCurrentCapacity()
    {
        return arrayCapacity;
    }
    
    /**
     * Gets current size of array
     * <p>
     * Note: size of array indicates number of valid or viable values in the 
     * array
     * @return integer size of array
     */
    protected int getCurrentSize()
    {
        return arraySize;
    }
    
    /**
     * Tests for size of array equal to zero, no valid values stored in array
     * @return Boolean result of test for empty
     */
    protected boolean isEmpty()
    {
        return (this.getCurrentSize()<=0);
    }
    
    /**
     * Removes item from array at specified index if index within array size 
     * bounds
     * <p>
     * Note: Each data item from the element immediately above the remove index
     * to the end of the array is moved down by one element
     * <p>
     * Note: Calls accessAtIndex with REMOVE to conduct action
     * @param removeIndex integer index of element value to be removed
     * @return removed integer value if successful, FAILED_ACCESS if not
     */
    protected int removeAtIndex(int removeIndex)
    {
        return this.accessAtIndex(REMOVE,removeIndex);
    }
    
    /**
     * sets item in array at specified index
     * <p>
     * Note: If constant REPLACE is used, new value overwrites value at given 
     * index
     * <p>
     * Note: If constant INSERT_BEFORE is used, new value is inserted prior to 
     * the value at the given index moving all other elements up by one
     * <p>
     * Note: If constant INSERT_AFTER is used, new value is inserted after the 
     * value at the given index moving all other elements up by one
     * <p>
     * Note: Method checks for available array capacity and adjusts it as 
     * needed prior to inserting new item
     * <p>
     * Note: Method must also check for correct array boundaries depending upon
     * INSERT/REPLACE state
     * @param setIndex integer index of element where value is to be inserted
     * @param newValue integer value to be placed in array
     * @param replaceFlag integer flag to indicate insertion or replacement in
     * the array
     * @return Boolean success if inserted, or failure if incorrect index was 
     * used
     */
    protected boolean setAtIndex(int setIndex,int newValue,int replaceFlag)
    {
        switch(replaceFlag)
        {
            case REPLACE:
                localArray[setIndex]=newValue;
                break;
            case INSERT_BEFORE:
                if(this.isEmpty() || setIndex==0)
                {
                    return false;
                }
                localArray[setIndex-1]=newValue;
                break;
            case INSERT_AFTER:
                this.checkForResize();
                localArray[setIndex+1]=newValue;
                break;
            default:
                return false;               
        }
        return true;
    }
}
