package p9_package;

/**
 *
 * @author djg32
 */
public class ArrayHeapClass
{
    /**
     * Management data for array
     */
    private int arrayCapacity;
    
    /**
     * Management data for array
     */
    private int arraySize;
    
    /**
     * Initial array capacity
     */
    final int DEFAULT_ARRAY_CAPACITY = 10;
    
    /**
     * Display flag can be set to observe bubble up and trickle down operations
     */
    private boolean displayFlag;
    
    /**
     * Array for heap
     */
    private StudentClass[] heapArray;
    
    /**
     * Default constructor sets up array management conditions and default 
     * display flag setting
     */
    ArrayHeapClass()
    {
        arrayCapacity=DEFAULT_ARRAY_CAPACITY;
        displayFlag=false;
        heapArray= new StudentClass[DEFAULT_ARRAY_CAPACITY];
    }
    
    /**
     * Copy constructor copies array and array management conditions and 
     * default display flag setting
     * @param copied ArrayHeapClass object to be copied
     */
    ArrayHeapClass(ArrayHeapClass copied)
    {
        arrayCapacity=copied.arrayCapacity;
        displayFlag=copied.displayFlag;
        heapArray= new StudentClass[arrayCapacity];
    }
    
    /**
     * Accepts StudentClass item and adds it to heap
     * <p>
     * Note: uses bubbleUpArrayHeap to resolve unbalanced heap after data
     * addition
     * <p>
     * Note: Always checks for resize before adding data
     * @param newItem StudentClass item to be added
     */
    void addItem(StudentClass newItem)
    {
        System.out.println("Adding new Student: "+ newItem.toString());
        checkForResize();
        arraySize++;
        heapArray[arraySize]=newItem;
        bubbleUpArrayHeap(arraySize);
    }
    
    /**
     * Recursive operation to reset data in the correct order for the max heap
     * after new data addition
     * @param currentIndex index of current item being assessed, and moved up
     * as needed
     */
    private void bubbleUpArrayHeap(int currentIndex)
    {
        int left = currentIndex*2+1;
        int right = currentIndex*2+2;
        int parent = (currentIndex-1)/2;
        int index=currentIndex;
        if(left<arraySize)
        {
            if(heapArray[left].compareTo(heapArray[currentIndex])>0)
            {
                System.out.println("Bubbling up");
                index=left;
            }
        }
        if(right<arraySize)
        {
            if(heapArray[right].compareTo(heapArray[index])>0)
            {
                System.out.println("Bubbling up");
                index=right;
            }
        }
        if(index!=currentIndex)
        {
            StudentClass temp = new StudentClass(heapArray[index]);
            heapArray[index]=heapArray[currentIndex];
            heapArray[currentIndex]=temp;
            System.out.println("Swapping Parent: "+heapArray[index].toString()
            + "with child: "+ heapArray[currentIndex].toString());
            bubbleUpArrayHeap(index);
        }
        System.out.println();
    }
    
    /**
     * Automatic resize operation used prior to any new data addition in the
     * heap
     * <p>
     * Note: Tests for full heap array, and resizes to twice the current 
     * capacity as required
     */
    private void checkForResize()
    {
        int iteration;
        if(arrayCapacity-1==arraySize)
        {
            StudentClass[] temp=new StudentClass[arrayCapacity];
            for(iteration=0;iteration<arrayCapacity;iteration++)
            {
                temp[iteration]=heapArray[iteration];
            }
            arrayCapacity=arrayCapacity+1;
            heapArray = new StudentClass[arrayCapacity];
            for(iteration=0;iteration<arrayCapacity-1;iteration++)
            {
                heapArray[iteration]=temp[iteration];
            }
        }
    }
    
    /**
     * Tests for empty heap
     * @return boolean result of test
     */
    boolean isEmpty()
    {
        return (arraySize==0);
    }
    
    /**
     * Removes StudentClass item from top of max heap
     * <p>
     * Note: Uses trickleDownArrayHeap to resolve unbalanced heap after data 
     * removal
     * @return StudentClass item removed
     */
    StudentClass removeItem()
    {
        StudentClass removed = heapArray[1];
        heapArray[1]=heapArray[arraySize--];
        trickleDownArrayHeap(arraySize);
        return removed;
    }
    
    /**
     * Utility method to set the display flag for displaying internal 
     * operations of the heap bubble and trickle operations
     * @param setState flag used to set the state to display, or not
     */
    void setDisplayFlag(boolean setState)
    {
        displayFlag=setState;
    }
    
    /**
     * Dumps array to screen as is, no filtering or management
     */
    void showArray()
    {
        int iteration;
        System.out.print("StudentClass heapArray: ");
        for(iteration=0;iteration<arraySize;iteration++)
        {
            System.out.print(heapArray[iteration]+", ");
        }
        System.out.print(heapArray[iteration]);
        System.out.println();
        
    }
    
    /**
     * Recursive operation to reset data in the correct order for the max heap
     * after data removal
     * @param currentIndex index of current item being assessed, and moved down
     * as required
     */
    private void trickleDownArrayHeap(int currentIndex)
    {
        int left = currentIndex*2+1;
        int right = currentIndex*2+2;
        int parent = (currentIndex-1)/2;
        int index=currentIndex;
        if(left<arraySize)
        {
            if(heapArray[left].compareTo(heapArray[currentIndex])>0)
            {
                index=left;
            }
        }
        if(right<arraySize)
        {
            if(heapArray[right].compareTo(heapArray[index])>0)
            {
                index=right;
            }
        }
        if(index!=currentIndex)
        {
            StudentClass temp = new StudentClass(heapArray[index]);
            heapArray[index]=heapArray[currentIndex];
            heapArray[currentIndex]=temp;
            trickleDownArrayHeap(index);
        }
    }
}
