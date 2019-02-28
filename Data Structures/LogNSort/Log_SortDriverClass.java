package p3_package;

/**
 *Driver class to sort data passed to class
 * @author djg32
 */
public class LogN_SortDriverClass 
{
    /**
     * Default constructor, takes no action for this static tool class
     */
    public LogN_SortDriverClass()
    {   
        //Nothing is done
    }
    
    /**
     * Compares two strings character by character set to lower case to see 
     * which is alphabetically first
     * @param strOne first String value to be compared
     * @param strTwo second String value to be compared
     * @return integer difference as specified
     */
    public static int compareStrings(String strOne, String strTwo)  //DONE?
    {
        int iteration;
        for(iteration=0;iteration<strOne.length();iteration++)
        {
            if(toLowerCase(strOne.charAt(iteration))>toLowerCase
            (strTwo.charAt(iteration)))
            {
                return 1;
            }
            else if(toLowerCase(strOne.charAt(iteration))<toLowerCase
            (strTwo.charAt(iteration)))
            {
                return -1;
            }
            else if(toLowerCase(strOne.charAt(iteration))==toLowerCase
            (strTwo.charAt(iteration)))
            {
                if(strOne.length()==strTwo.length())
                {
                    return 0;
                }
                return (strOne.length()-strTwo.length());
            }
        }
        return 0;
    }
    
    /**
     * Combines Strings from low and high indexes
     * @param localArray String array holding unsorted values
     * @param lowIndex lowest index of array segment to be managed
     * @param middleIndex middle index of array segment to be managed
     * @param highIndex high index of array segment to be managed
     */
    private static void runMerge(String[] localArray, int lowIndex, 
    int middleIndex, int highIndex)
    {
        String[] lowerTemp=new String[highIndex];
        String[] higherTemp=new String[highIndex];
        int lowCount=0;
        int highCount=0;
        int initialIndex=lowIndex;
        int lowEdge=middleIndex-lowIndex+1;
        int highEdge=highIndex-middleIndex;
        for(lowCount=0;lowCount<lowEdge;lowCount++)
        {
            lowerTemp[lowCount]=localArray[lowCount];
        }
        for(highCount=0;highCount<highEdge;highCount++)
        {
            higherTemp[highCount]=localArray[highCount];
        }
        while(lowEdge>lowCount && highEdge>highCount)
        {
            if(lowerTemp[lowCount].charAt(lowCount)<=
            higherTemp[highCount].charAt(highCount))
            {
                localArray[initialIndex]=lowerTemp[lowCount];
                lowCount++;
            }
            else
            {
                localArray[initialIndex]=lowerTemp[highCount];
                highCount++;
            }
            initialIndex++;
        }
        while(lowCount<lowEdge)
        {
            localArray[initialIndex]=lowerTemp[lowCount];
            lowCount++;
            initialIndex++;
        }
        while(highCount<highEdge)
        {
            localArray[initialIndex]=higherTemp[highCount];
            highCount++;
            initialIndex++;
        }
        
    }
    
    /**
     * Calls RunMergeSortHelper to begin merge sorting
     * @param localArray String array holding unsorted values
     * @param size integer value holding number of values in array
     */
    public static void runMergeSort(String[] localArray, int size)
    {
        runMergeSortHelper(localArray, 0, size);
    }
    
    /**
     * Breaks array down to smaller segments between lowIndex and highIndex 
     * (inclusive), then calls runMergeSort
     * @param localArray String array holding unsorted values
     * @param lowIndex lowest index of array segment to be managed; this varies 
     * as the segments are broken down recursively
     * @param highIndex highest index of array segment to be managed; this 
     * varies as the segments are broken down recursively
     */
    private static void runMergeSortHelper(String[] localArray, int lowIndex,
    int highIndex)
    {
        int middleIndex;
        if(lowIndex<highIndex)
        {
            middleIndex=(lowIndex+highIndex)/2;
            runMergeSortHelper(localArray, lowIndex, middleIndex);
            runMergeSortHelper(localArray, middleIndex+1, highIndex);
            runMerge(localArray, lowIndex, middleIndex, highIndex);
        }
    }
    
    /**
     * Partitions the array to the correct positions
     * @param localArray String array holding unsorted values
     * @param lowIndex low index of array segment to be partitioned
     * @param highIndex high index of array segment to be partitioned
     * @return integer index of partition pivot
     */
    private static int runPartition(String[] localArray,int lowIndex,
    int highIndex)
    {
        int holder, point=0;
        String textOne;
        String textTwo;
        
        holder=lowIndex;
        textOne = localArray[holder];
        holder++;
        textTwo = localArray[holder];
        while(lowIndex<highIndex)
        {
            if(compareStrings(textOne, textTwo)>0)
            {
                point++;
                swapValues(localArray, point, lowIndex);
            }
            lowIndex++;
            
        }
        swapValues(localArray, point, holder);
        return point;
    }
    
    /**
     * Calls RunQuickSortHelper to begin the quick sort
     * @param localArray String array holding unsorted values
     * @param size integer value holding the number of values in the array
     */
    public static void runQuickSort(String[] localArray,int size)
    {
        runQuickSortHelper(localArray, 0, size-1);
    }
    
    /**
     * Called by runQuickSort, calls runPartition to split the array passed
     * @param localArray String array holding unsorted values
     * @param lowIndex low index of the segment of the array to be processed
     * @param highIndex high index of the segment of the array to be processed
     */
    private static void runQuickSortHelper(String[] localArray,int lowIndex,
    int highIndex)
    {
        int pointer;
        if(lowIndex<highIndex)
        {
            pointer=runPartition(localArray, lowIndex, highIndex);
            runQuickSortHelper(localArray, lowIndex, pointer-1);
            runQuickSortHelper(localArray, pointer, highIndex);
        }
    }
    
    /**
     * Takes an array of strings and will swap the strings at the indicated
     * indexes
     * @param localArray array of Strings used for swapping
     * @param indexOne integer index for one of the two items to be swapped
     * @param indexOther integer index for the other of the two items to be 
     * swapped
     */
    private static void swapValues(String[] localArray,int indexOne,
    int indexOther)
    {
        String temp=localArray[indexOne];
        localArray[indexOne]=localArray[indexOther];
        localArray[indexOther]=temp;
    }
    
    /**
     * Updates chars passed to method to lower case
     * @param testChar character value to be tested and possibly modified
     * @return character value modified as specified
     */
    private static char toLowerCase(char testChar)
    {
        if(testChar>='A' && testChar<='Z')
        {
            testChar=(char)(testChar+(32));
        }
        return testChar;
    }
}
