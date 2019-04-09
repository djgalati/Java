package p9_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ArrayHeapClassMain
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
     * constant definition of minus sign (dash)
     */
    private static final char MINUS_SIGN = '-';
   
    /**
     * constant definition of semicolon
     */
    private static final char SEMI_COLON = ';';
   
    /**
     * constant definition for maximum input string
     */
    private static final int MAX_INPUT_CHARS = 80;
   
    /**
     * constant definition of end of file marker
     */
    private static final int EOF_MARKER = -1;


    public static void main(String[] args)
       {
        ArrayHeapClass ahc;
        StudentClass removedItem;
        boolean inputDisplayFlag = false;
        boolean removalDisplayFlag = true;
       
        System.out.println( "Adding Items to heap");
        ahc = uploadData( "testSet.txt", inputDisplayFlag );

        ahc.setDisplayFlag( removalDisplayFlag );
        
        System.out.println( "\nRemoving Items from heap");

        while( !ahc.isEmpty() )
           {
            removedItem = ahc.removeItem();
           
            System.out.println( removedItem.toString() );
           }
           
       }
   
    /**
     * Uploads data from a formatted text data file
     * 
     * @param fileName String file name of file
     * 
     * @return BST_Class data structure with data loaded
     */
    public static ArrayHeapClass uploadData( String fileName, boolean display )
       {
        ArrayHeapClass heapData = new ArrayHeapClass();
        StudentClass newItem;
        String inputString;
        int inputID;
        double inputGPA;
        int inputGender;
       
        heapData.setDisplayFlag( display );

        try
           {
            // Open FileReader 
            fileIn = new FileReader( fileName );
         
            // get leader line ahead of array height
            inputString = getALine( MAX_INPUT_CHARS, SEMI_COLON );
          
            while( compareString( inputString, "EndOfFile") != 0 )
               {
                inputID = getAnInt( MAX_INPUT_CHARS );
               
                inputGender = getACharInt( "MmFf" );
                
                inputGPA = getADouble( MAX_INPUT_CHARS );
                
                newItem = new StudentClass( inputString, inputID,
                                               (char)inputGender, inputGPA );
               
                // For input testing:
                // System.out.println( "Adding: " + newItem.toString() );
                
                heapData.addItem( newItem );
                
                inputString = getALine( MAX_INPUT_CHARS, SEMI_COLON );
               }
           }
       
        catch( FileNotFoundException fnfe )
           {
            System.out.println( 
                           "DATA ACCESS ERROR: Failure to open input file" );
           
            return null;
           }
       
        return heapData;       
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
          
            while( index < maxLength && inCharInt != (int)( delimiterChar ) )
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

    /**
     * Gets an integer from an input file
     * 
     * @param maxLength maximum length of characters
     * input to capture the integer
     * 
     * @return integer captured from file
     */
    private static int getAnInt( int maxLength )
       {
        int inCharInt;
        int index = 0;
        String strBuffer = "";
        int intValue;
        boolean negativeFlag = false;

        try
           {
            inCharInt = fileIn.read();

            // clear space up to number
            while( index < maxLength 
                       && !charInString( (char)inCharInt, "0123456789+-" ) )
               {
                inCharInt = fileIn.read();
              
                index++;
               }
          
            if( inCharInt == MINUS_SIGN )
               {
                negativeFlag = true;
              
                inCharInt = fileIn.read();
               }

            while( charInString( (char)inCharInt, "0123456789" ) )
               {   
                strBuffer += (char)( inCharInt );

                index++;
              
                inCharInt = fileIn.read();
               }            
           }
      
        catch( IOException ioe )
           {
            System.out.println( "INPUT ERROR: Failure to capture character" );
         
            strBuffer = "";
           } 
         
        intValue = Integer.parseInt( strBuffer );
      
        if( negativeFlag )
           {
            intValue *= -1;
           }
      
        return intValue;
       }

    /**
     * Gets a double from the input string
     * 
     * @param maxLength maximum length of characters
     * input to capture the double
     * 
     * @return integer captured from file
     */
    private static double getADouble( int maxLength )
       {
        int inCharInt;
        int index = 0;
        String strBuffer = "";
        boolean negativeFlag = false;
        double doubleValue;

        try
           {
            inCharInt = fileIn.read();

            // clear space up to number
            while( index < maxLength && !charInString( (char)inCharInt, ".0123456789+-" ) )
               {
                inCharInt = fileIn.read();
              
                index++;
               }

            if( inCharInt == MINUS_SIGN )
               {
                negativeFlag = true;
              
                inCharInt = fileIn.read();
               }

            while( charInString( (char)inCharInt, ".0123456789" ) )
               {   
                strBuffer  += (char)( inCharInt );
 
                index++;
               
                inCharInt = fileIn.read();
               }            
           }
      
        catch( IOException ioe )
           {
            System.out.println( "INPUT ERROR: Failure to capture character" );
         
            strBuffer = "";
           }
         
        doubleValue = Double.parseDouble( strBuffer );
      
        if( negativeFlag )
           {
            doubleValue *= -1;
           }
      
        return doubleValue;
       }

    /** 
     * Local method for getting next desired character 
     * from the file stream
     * 
     * @param rangeString set of desired characters
     *  
     * @return integer character for use in input process
     */
    private static int getACharInt( String rangeString )
       { 
        int charInt = 0;
       
        try
           {
            charInt = fileIn.read();
           
            while( charInt != EOF_MARKER 
                        && !charInString( (char)charInt, rangeString ) )
               {
                charInt = fileIn.read();
               }
           }
       
        catch( IOException ioe )
           {
            System.out.println( "INPUT ERROR: Failure to capture character" );
           }
       
        return charInt;
       }

    /**
     * Utility method for comparing Strings
     * 
     * @param oneStr one of the String quantities
     * 
     * @param otherStr the other String quantity
     * 
     * @return integer comparison value such that:
     * if oneStr LT otherStr, return LT 0;
     * if oneStr GT otherStr, return GT 0;
     * if oneStr == otherStr, return 0;
     * if both strings are the same up to the length of one of them,
     * returns the difference in lengths
     */
    private static int compareString( String oneStr, String otherStr )
       {
        int difference, index = 0;
       
        while( index < oneStr.length() && index < otherStr.length() )
           {
            difference = oneStr.charAt( index ) - otherStr.charAt( index );
           
            if( difference != 0 )
               {
                return difference;
               }
           
            index++;
           }
       
        return oneStr.length() - otherStr.length();
       }
   
    /**
     * tests and reports if a character is found in a given string
     * 
     * @param testChar character to be tested against the string
     * 
     * @param testString string within which the character is tested
     * 
     * @return Boolean result of test
     */
    private static boolean charInString( char testChar, String testString )
       {
        int index;
       
        for( index = 0; index < testString.length(); index++ )
           {
            if( testChar == testString.charAt( index ) )
               {
                return true;
               }
           }
      
        return false;
       }
  


   }
