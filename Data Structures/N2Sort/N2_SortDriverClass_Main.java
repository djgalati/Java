package p2_package;

public class N2_SortDriverClass_Main
   {
    /** 
     * main method drives testing the three sorting algorithms
     * 
     * @param args String arguments not used
     */
    public static void main(String[] args)
       {
        int numChars = 20; // may not be greater than 26
        char[] testArr;
       
        testArr = generateUniqueChars( numChars );
        displayArray( testArr, numChars );       
        testArr = N2_SortDriverClass.runBubbleSort( testArr, numChars );
        System.out.print( "Bubble sort result: " );
        displayArray( testArr, numChars );
       
        testArr = generateUniqueChars( numChars );
        displayArray( testArr, numChars );       
        testArr = N2_SortDriverClass.runInsertionSort( testArr, numChars );
        System.out.print( "Insertion sort result: " );
        displayArray( testArr, numChars );
       
        testArr = generateUniqueChars( numChars );
        displayArray( testArr, numChars );       
        testArr = N2_SortDriverClass.runSelectionSort( testArr, numChars );
        System.out.print( "Selection sort result: " );
        displayArray( testArr, numChars );
       
        testArr = generateUniqueChars( numChars );
        displayArray( testArr, numChars );       
        testArr = N2_SortDriverClass.runShellSort( testArr, numChars );
        System.out.print( "Shell sort result: " );
        displayArray( testArr, numChars );              
       }

    /**
     * Creates an array of unique (i.e., non-repeating) random characters
     * <p>
     * Note: Uses generateRandBetween
     * 
     * @param numChars integer number of characters requested;
     * note this may not be greater than 26
     */
    public static char[] generateUniqueChars( int numChars )
       {
        int index;
        char newChar;
        char[] newCharArr = new char[ numChars ];
        
        for( index = 0; index < numChars; index++ )
           {
            do
               {
                newChar = generateRandBetween( 'A', 'Z' );
               }
            while( isInArray( newCharArr, index, newChar ) );
            
            newCharArr[ index ] = newChar;
           }
    
        return newCharArr;
       }
    
    /**
     * Generate random character value between the lowest and highest
     * specified limits inclusive
     * 
     * @param lowLimit specified character low limit of random value range
     * 
     * @param highLimit specified character high limit of random value range
     * 
     * @return random character value generated between the inclusive limits; 
     * dash is returned if limits are not correct
     */
    public static char generateRandBetween( char lowLimit, char highLimit )
       {
        int randVal, range;
       
        if( highLimit > lowLimit )
           {
            // create range of numbers
            range = highLimit - lowLimit + 1;
           
            // find random integer value from value between 0 and 1
            randVal = (int)Math.round( Math.random() * range );
           
            return (char)( randVal % range + lowLimit );
           } 
       
        return '-';
       } 

    /**
     * Checks for test character already stored in given array
     * 
     * @param testArray character array within which test character might
     * already be stored
     * 
     * @param arrSize integer number of valid items in array
     * 
     * @param testChar character to be tested for current storage in array
     * 
     * @return Boolean result of test, true if character is found in array,
     * false otherwise
     */
    public static boolean isInArray( char[] testArray, 
                                                 int arrSize, char testChar )
       {
        int index = 0;
        
        while( index < arrSize )
           {
            if( testArray[ index ] == testChar )
               {
                return true;
               }
            
            index++;
           }
        
        return false;
       }
    
    /**
     * Displays comma-delimited array of characters
     * 
     * @param dispArray character array with values to be displayed
     * 
     * @param size integer number of valid characters in array
     */
    public static void displayArray( char[] dispArray, int size )
       {
        int index;
        
        for( index = 0; index < size; index++ )
           {
            if( index > 0 )
               {
                System.out.print( ", " );
               }
            
            System.out.print( dispArray[ index ] );
           }
        
        System.out.println();
       }
   }
