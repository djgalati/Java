package p2_package;

/**
 * Sorting Driver Class
 * @author djg323
 */
public class N2_SortDriverClass {
    /**
     * A empty constructor that has no action
     */
    public N2_SortDriverClass()
    {
    }
    
    /**
     * Bubble Sort algorithm that sorts a given array of chars via bubble sort
     * @param charArr An array to be sorted
     * @param size The amount of elements in the array
     * @return A sorted char array
     */
    public static char[] runBubbleSort(char[] charArr, int size)
    {
        int iteration;
        boolean swap;
        do
        {
            swap=false;
            for(iteration=0;iteration<size-1;iteration++)
            {
                if(charArr[iteration]>charArr[iteration+1])
                {
                    swapValues(charArr, iteration, iteration+1);
                    swap=true;
                }
            }
        }
        while(swap);
        return charArr;
    }
    
    /**
     * Insertion Sort algorithm that sorts a given array of chars via 
     * insertion sort
     *@param charArr An array to be sorted
     * @param size The amount of elements in the array
     * @return A sorted char array
     */
    public static char[] runInsertionSort(char[] charArr, int size)
    {
        int iteration;
        int innerLoop;
        char temp;
        for(iteration=1;iteration<size;iteration++)
        {
            temp=charArr[iteration];
            innerLoop=iteration-1;
            
            while(innerLoop>=0 && charArr[innerLoop]>temp)
            {
                charArr[innerLoop+1]=charArr[innerLoop];
                innerLoop--;
                
            }
            charArr[innerLoop+1]=temp;
        }
        return charArr;
    }
    
    /**
     * Selection Sort algorithm that sorts the given array via selection sort
     * @param charArr An array to be sorted
     * @param size The amount of elements in the array
     * @return A sorted char array
     */
    public static char[] runSelectionSort(char[] charArr, int size)
    {
        int iteration;
        int innerIteration;
        int min;
        
        for(iteration=0;iteration<size;iteration++)
        {
            min=iteration;
            
            for(innerIteration=iteration+1;innerIteration<size;innerIteration++)
            {
            
                if(charArr[innerIteration]<charArr[min])
                {
                    swapValues(charArr,min,innerIteration);
                }
            }
        }
        return charArr;
    }
    
    /**
     * Shell Sort algorithm that sorts the given array via shell sort
     *@param charArr An array to be sorted
     * @param size The amount of elements in the array
     * @return A sorted char array
     */
    public static char[] runShellSort(char[] charArr, int size)
    {
        char temp;
        int iteration;
        int outerLoop;
        int innerLoop;
        for(iteration=size/2;iteration>0;iteration=iteration/2)
        {
            for(outerLoop=iteration;outerLoop<size;outerLoop++)
            {
                for(innerLoop=outerLoop-iteration;innerLoop>=0;
                innerLoop=innerLoop-iteration)
                {
                    temp = charArr[innerLoop];
                    if(charArr[innerLoop]>charArr[innerLoop+iteration])
                    {
                        swapValues(charArr, innerLoop, innerLoop+iteration);
                    }
                }
            }
        }
        return charArr;
    }
    
    /**
     * A helper method that swaps 2 given chars in an array
     * @param charArray The char array that is getting values swapped
     * @param indexOne The first index of two needed to swap chars
     * @param indexOther The other index needed to swap chars
     */
    private static void swapValues(char [] charArray, int indexOne, 
    int indexOther)
    {
        char temp;
        temp=charArray[indexOne];
        charArray[indexOne]=charArray[indexOther];
        charArray[indexOther]=temp;
        
    }
}
