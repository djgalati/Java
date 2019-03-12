package p6_package;

/**
 *
 * @author djg32
 */
public class BasicLinkedListClass {
    /**
     * Default failed access constant
     */
    static final int FAILED_ACCESS = -999999;
    
    /**
     * Member - linked list head reference
     */
    private NodeClass headRef;
    
    /**
     * Constant used for allowing setAtIndex to insert value at index
     */
    static final int INSERT_AFTER = 1003;
    
    /**
     * Constant used for allowing setAtIndex to insert value at index
     */
    static final int INSERT_BEFORE = 1002;
    
    /**
     * Constant used for allowing accessAtIndex to remove an item
     */
    static final int REMOVE = 1004;
    
    /**
     * Constant used for allowing setAtIndex to replace at index
     */
    static final int REPLACE = 1001;
    
    /**
     * Constant used for allowing accessAtIndex to retrieve an item
     */
    static final int RETRIEVE = 1005;
    
    /**
     * Internal node class for managing data in the linked list
     */
    private class NodeClass
    {
        /**
         * hold the data for the node
         */
        private int nodeData;
        
        /**
         * Refers to the next node in the list if available
         */
        private NodeClass nextRef;
        
        /**
         * Initialization constructor for NodeClass
         * @param copied NodeClass object to be copied into this node
         */
        NodeClass(NodeClass copied)
        {
            nodeData = copied.nodeData;
            nextRef = new NodeClass(copied.nextRef);
        }
        
        /**
         * Initialization constructor for NodeClass
         * @param newData integer value to be placed in node
         */
        NodeClass(int newData)
        {
            nodeData = newData;
            nextRef = null;
        }
    }
    
    /**
     * Default constructor, initializes linked list
     */
    protected BasicLinkedListClass()
    {
        headRef = new NodeClass(0);
    }
    
    /**
     * Copy constructor, initializes linked list, then copies all nodes 
     * to local(this) linked list
     */
    protected BasicLinkedListClass(BasicLinkedListClass copied)
    {
        headRef = new NodeClass(copied.headRef);
    }
    
    /**
     * Utility method used by getAtIndex and removeAtIndex to access and 
     * possibly remove element depending on control code
     * <p>
     * Note: Data is managed with virtual index found by getRefAtIndex
     * <p>
     * Note: Uses only one loop
     * @param controlCode integer value with either RETRIEVE or REMOVE to 
     * control operations
     * @param index integer virtual index of element to be retrieved or removed
     * @return integer value at element or FAILED_ACCESS if attempt to access 
     * data out of bounds
     */
    private int accessAtIndex(int controlCode,int index)
    {
        NodeClass temp = getRefAtIndex(headRef,index);
        if(controlCode == RETRIEVE)
        {
            if(temp==null)
            {
                return FAILED_ACCESS;
            }
        }
        else
        {
            while(temp.nextRef.nextRef!=null)
            {
                temp.nextRef = temp.nextRef.nextRef;
            }
            temp.nextRef.nextRef = null;
        }
        return temp.nodeData;
    }
    
    /**
     * Clears linked list of all valid values by setting linked list head 
     * reference to null
     */
    protected void clear()
    {
        headRef = null;
    }
    
    /**
     * Accesses item in linked list at specified virtual index if it is within
     *  linked list limits
     * <p>
     * Note: Linked list value specified by virtual index is returned to 
     * calling program
     * <p>
     * Note: Calls accessAtIndex with RETRIEVE to conduct action
     * @param accessIndex integer virtual index of requested element value
     * @return integer accessed value if successful, FAILED_ACCESS if not
     */
    protected int getAtIndex(int accessIndex)
    {
       return accessAtIndex(RETRIEVE, accessIndex);
    }
    
    /**
     * Gets virtual index of last item in linked list
     * <p>
     * Note: Uses getCurrentSizeHelper
     * <p>
     * Note: Handles empty list prior to calling helper
     * @return integer virtual index
     */
    protected int getCurrentSize()
    {
        NodeClass wkgRef = new NodeClass(headRef);
        if(isEmpty())
        {
            return 0;
        }
        
        return getCurrentSizeHelper(wkgRef);
    }
    
    /**
     * Uses recursion to find virtual linked list size
     * @param wkgRef NodeClass current reference in recursion, initially called
     * with head reference
     * @return integer linked list size
     */
    private int getCurrentSizeHelper(NodeClass wkgRef)
    {
        if(wkgRef.nextRef != null) 
        {
            return 1+getCurrentSizeHelper(wkgRef.nextRef);
        }
        return 1;
    }
    
    /**
     * Private recursive method, finds a node represented by a virtual index
     * <p>
     * Note: if requested index is less than zero or outside linked list 
     * boundary, returns null
     * @param wkgRef NodeClass reference that initially called with the 
     * head reference, and is used for recursive operations
     * @param requestedIndex integer value representing virtual index requested
     * by the user
     * @return NodeClass reference to the element at the virtual index
     */
    private NodeClass getRefAtIndex(NodeClass wkgRef, int requestedIndex)
    {   
        if(requestedIndex>0) 
        {
            return getRefAtIndex(wkgRef.nextRef, requestedIndex-1);
        }
        return wkgRef;
    }
    
    /**
     * Tests for empty linked list
     * @return Boolean result of test for empty
     */
    protected boolean isEmpty()
    {
        if(headRef==null) return true;
        return false;
    }
    
    /**
     * Removes item from linked list at specified virtual index if index within
     * linked list size bounds
     * <p>
     * Note: Linked list node specified by virtual index is removed from list
     * <p>
     * Note: Calls accessAtIndex with REMOVE to conduct action
     * @param removeIndex integer index of element value to be removed
     * @return removed integer value if successful, FAILED_ACCESS if not
     */
    protected int removeAtIndex(int removeIndex)
    {
        return accessAtIndex(REMOVE, removeIndex);
    }
    
    /**
     * Displays formatted list with virtual indices
     * @param showIndex Boolean value turns on display of index under value if
     * set to true, otherwise only shows pipe delimited values
     */
    protected void runDiagnosticDisplay(boolean showIndex)
    {
        if(isEmpty())
        {
            System.out.println("No Data");
        }
        int iteration;
        System.out.print(" List: |");
        for(iteration=0;iteration<getCurrentSize();iteration++)
        {
            System.out.print(getAtIndex(iteration) +"|" +" ");
        }
        System.out.println();
        if(showIndex)
        {
            System.out.print(" Index: |");
            for(iteration=0;iteration<getCurrentSize();iteration++)
            {
                System.out.print(" "+iteration+"   ");
                
            }
            System.out.println();
        }
    }
    
    /**
     * sets item in linked list at specified virtual index
     * <p>
     * Note: If constant REPLACE is used, new value overwrites value at current
     * virtual index
     * <p>
     * Note: If constant INSERT_BEFORE is used, new value is inserted prior to
     * the value at the current virtual index
     * <p>
     * Note: If constant INSERT_AFTER is used, new value is inserted after the
     * value at the current virtual index
     * <p>
     * Note: Method must check for correct virtual array boundaries; if index
     * requested is below zero or above list size - 1, method must take no
     * action and return false
     * <p>
     * Note: Method must check for correct replace flag; if it is not one of
     * the three specified flags, it must take no action and return false
     * @param setIndex integer index of element at which value is to be 
     * replaced, or value is to be inserted before or after
     * @param newValue integer value to be placed in linked list
     * @param replaceFlag integer flag to indicate insertion or replacement in
     * the linked list
     * @return Boolean success if inserted, or failure if incorrect index or
     * replace flag was used
     */
    protected boolean setAtIndex(int setIndex, int newValue, int replaceFlag)
    {
        if(isEmpty() || setIndex>getCurrentSize()-1) replaceFlag=0;
        
        NodeClass nodeAtIndex = getRefAtIndex(headRef, setIndex);
        NodeClass newNode = new NodeClass(newValue);
        
        switch(replaceFlag)
        {
            case REPLACE:
                nodeAtIndex.nodeData = newValue;
                break;
                
            case INSERT_BEFORE:
                NodeClass prevNode = getRefAtIndex(headRef, setIndex-1);
                newNode.nextRef = prevNode.nextRef;
                prevNode.nextRef = newNode;
                break;
                
            case INSERT_AFTER:
                NodeClass nextNode = getRefAtIndex(headRef, setIndex);
                newNode.nextRef = nextNode;
                nodeAtIndex.nextRef=newNode;
                break;
                
            default:
                return false;
        }
        return true;
    }
}
