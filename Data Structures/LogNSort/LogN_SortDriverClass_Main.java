package p3_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Driver class for testing LogN_SortDriverClass tools
 * 
 * @author MichaelL
 *
 */
public class LogN_SortDriverClass_Main
   {
    /**
     * FileReader reference for use in upload
     */
    private static FileReader fileIn;
   
    /**
     * constant definition of space
     */
    private static final char SPACE = ' ';

    /**
     * constant definition for maximum input string
     */
    private static final int MAX_INPUT_CHARS = 80;
   
    /**
     * constant definition of end of file marker
     */
    private static final int EOF_MARKER = -1;

    /**
     * Main method driver for testing sorting operations
     * 
     * @param args String array arguments not used
     */
    public static void main(String[] args)
       {
        int numItems = 100;
        String[] msStrArray, qsStrArray; 
       
        msStrArray = uploadData( "NameData.txt", numItems );
        System.out.println( "List before Merge Sort:");
        displayNames( msStrArray, numItems );
        LogN_SortDriverClass.runMergeSort( msStrArray, numItems );
        System.out.println( "\nList after Merge Sort:");
        displayNames( msStrArray, numItems );
        
        qsStrArray = uploadData( "NameData.txt", numItems );
        System.out.println( "\nList before Quick Sort:");
        displayNames( qsStrArray, numItems );
        LogN_SortDriverClass.runQuickSort( qsStrArray, numItems );
        System.out.println( "\nList after Quick Sort:");
        displayNames( qsStrArray, numItems );
        
       }

    /**
     * Displays strings from array
     * 
     * @param nameList String array to be displayed
     * 
     * @param numNames integer number of names to be displayed
     */
    public static void displayNames( String[] nameList, int numNames )
       {
        int index;
        int itemsPerLine = 10;
        
        for( index = 0; index < numNames; index++ )
           {
            if( index > 0 )
               {
                if( index % itemsPerLine == 0 )
                   {
                    System.out.print( ",\n" );
                   }
                
                else
                   {
                    System.out.print( ", " );
                   }
               } 
            
            System.out.print( nameList[ index ] );
           }
        
        System.out.println();
       }
    
    /**
     * Uploads String data from a formatted text data file
     * <p>
     * Note: uses compareStrings method from LogN_SortDriverClass
     * 
     * @param fileName String file name of file
     * 
     * @param arrCapacity integer capacity specification 
     * for returned String array; limits number of strings to be uploaded
     * 
     * @return String data array with data loaded
     */
    public static String[] uploadData( String fileName, int arrCapacity )
       {
        String[] strArray = new String[ arrCapacity ];
        String inputString;
        int index = 0;
       
        try
           {
            // Open FileReader 
            fileIn = new FileReader( fileName );
         
            // get leader line ahead of array height
            inputString = getALine( MAX_INPUT_CHARS, SPACE );
          
            while( index < arrCapacity && LogN_SortDriverClass.compareStrings( 
                                              inputString, "EndOfFile") != 0 )
               {
                strArray[ index ] = inputString;
                
                index++;
               
                inputString = getALine( MAX_INPUT_CHARS, SPACE );
               }
           }
       
        catch( FileNotFoundException fnfe )
           {
            System.out.println( "DATA ACCESS ERROR: Failure to open input file" );
          
            return null;
           }
       
        return strArray;       
       }
   
    /**
     * Gets a string from a file up to a maximum length 
     * or to specified delimiter
     * 
     * @param maxLength maximum length of input line
     * 
     * @param delimiterChar delimiter character to stop input
     * 
     * @return String captured from file
     */
    private static String getALine( int maxLength, char delimiterChar )
       {
        int inCharInt;
        int index = 0;
        String strBuffer = "";

        try
           {
            inCharInt = fileIn.read();

            // consume leading spaces
            while( index < maxLength && inCharInt <= (int)( SPACE )  )
               {
                if( inCharInt == EOF_MARKER )
                   {
                    return "EndOfFile";
                   }
              
                index++; 
              
                inCharInt = fileIn.read();
               }
          
            while( index < maxLength 
                     && inCharInt > (int)SPACE 
                          && inCharInt != (int)( delimiterChar ) )
               {   
                if( inCharInt >= (int)( SPACE ) )
                   {
                    strBuffer += (char)( inCharInt );
 
                    index++;
                  }
              
                inCharInt = fileIn.read();             
               }
           }
      
        catch( IOException ioe )
           {
            System.out.println( "INPUT ERROR: Failure to capture character" );
         
            strBuffer = "";
           }
         
        return strBuffer;
       }
    
   }
