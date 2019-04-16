package p10_package;

/**
 *
 * @author djg32
 */
public class GenericHashClass<GenericData extends 
        java.lang.Comparable<GenericData>>
{
    /**
     * Table size default
     */
    private final int DEFAULT_TABLE_SIZE = 10;
    
    /**
     * Constant for returning item not found with search
     */
    public final int ITEM_NOT_FOUND = -1;
    
    /**
     * Constant for setting linear probing
     */
    public static final int LINEAR_PROBING = 101;
    
    /**
     * Flag for setting linear or quadratic probing
     */
    private int probeFlag;
    
    /**
     * Constant for setting quadratic probing
     */
    public static final int QUADRATIC_PROBING = 102;
    
    /**
     * Array for hash table
     */
    private Object[] tableArray;
    
    /**
     * Size of the array table
     */
    private int tableSize;
    
    /**
     * Default constructor
     * <p>
     * Initializes to default table size with probe flag set to linear probing
     */
    public GenericHashClass()
    {
        probeFlag = LINEAR_PROBING;
        tableArray = new StudentClass[DEFAULT_TABLE_SIZE];
        tableSize = DEFAULT_TABLE_SIZE;
    }
    
    /**
     * Initialization constructor
     * <p>
     * Initializes to default table size with probe flag set to probe parameter
     * @param inProbeFlag sets linear or quadratic probing
     */
    public GenericHashClass(int inProbeFlag)
    {
        probeFlag = inProbeFlag;
        tableArray = new StudentClass[DEFAULT_TABLE_SIZE];
        tableSize = DEFAULT_TABLE_SIZE;
    }
    
    /**
     * Initialization constructor
     * @param inTableSize sets table size (capacity) but does not allow table 
     * size to be less than default capacity
     * @param inProbeFlag sets linear or quadratic probing
     */
    public GenericHashClass(int inTableSize, int inProbeFlag)
    {
        probeFlag = inProbeFlag;
        tableArray = new StudentClass[inTableSize];
        tableSize = inTableSize;
    }
    
    /**
     * Adds GenericData item to hash table
     * <p>
     * Note: Uses hash index value from generateHash
     * <p>
     * Note: Shows probed index with data at the point of insertion
     * <p>
     * Note: Probe attempts are limited to the current size (capacity) of the
     * table
     * @param newItem GenericData item
     * @return Boolean success of operation
     */
    public boolean addItem(GenericData newItem)
    {
        int index = generateHash(newItem);
        if(index<0 || index>=tableSize)
        {
            return false;
        }
        tableArray[index] = newItem;
        return true;
    }
    
    /**
     * Clears hash table by setting all bins to null
     */
    public void clearHashTable()
    {
        int iteration;
        for(iteration=0;iteration<tableSize;iteration++)
        {
            tableArray[iteration] = null;
        }
    }
    
    /**
     * Returns item found
     * @param searchItem GenericData value to be found; uses findItemIndex
     * @return GenericData item found
     */
    public GenericData findItem(GenericData searchItem)
    {
        int result = findItemIndex(searchItem);
        return (GenericData) tableArray[result];
    }
    
    /**
     * Searches for item index in hash table
     * <p>
     * Uses linear or quadratic probing as configured
     * @param searchItem GenericData value to be found
     * @return integer index location of search item
     */
    private int findItemIndex(GenericData searchItem)
    {
        int iteration;
        int count=1;
        switch(probeFlag)
        {
            case LINEAR_PROBING:
                for(iteration=0;iteration<tableSize;iteration++)
                {
                    if(tableArray[iteration] == searchItem)
                    {
                        return iteration;
                    }
                }
                
            case QUADRATIC_PROBING:
                for(iteration=0;iteration<tableSize;iteration=iteration+(count*count))
                {
                    if(tableArray[iteration] == searchItem)
                    {
                        return iteration;
                    }
                    count++;
                }
                break;
                
            default:
                return ITEM_NOT_FOUND;
        }
        return iteration;
    }
    
    /**
     * Method converts GenericData hash value to index for use in hash table
     * @param item GenericData value to be converted to hash value
     * <p>
     * Note: gets data from object via hashCode, then calculates index
     * <p>
     * Note: Uses hashCode from object
     * @return integer hash value
     */
    public int generateHash(GenericData item)
    {
        return item.hashCode();
    }
    
    /**
     * Removes item from hash table
     * @param toBeRemoved GenericData value used for requesting data uses
     * findItemIndex
     * @return GenericData item removed, or null if not found
     */
    public GenericData removeItem(GenericData toBeRemoved)
    {
        if(tableArray[0]==null)
        {
            return toBeRemoved;
        }
        Object temp = null;                 //Cannot get to find correct index
        tableArray[0]=null;                //Cannot get to find correct index
        return (GenericData) temp;
    }
    
    /**
     * traverses through all array bins, finds min and max number of contiguous
     * elements, and number of empty nodes; also shows table loading
     * <p>
     * NOTE: Generates string of used and unused bins in addition to displaying
     * results on screen
     * @return String result of hash table analysis
     */
    public String showHashTableStatus()
    {
        int iteration;
        int min=0, max=0, empty=0;
        for(iteration=0;iteration<tableSize;iteration++)
        {
            
        }
        return "not completed";
    }
    
    /**
     * Local recursive method to calculate exponentiation with integers
     * @param base base of exponentiation
     * @param exponent exponent of exponentiation
     * @return result of exponentiation calculation
     */
    private int toPower(int base, int exponent)
    {
        return 0;
    }
}
