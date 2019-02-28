package p4_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MazeSolverMainClass
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
     * constant definition for COLON    
     */
    private static final char COLON = ':';
    
    /**
     * constant definition for minus sign
     */
    private static final char MINUS_SIGN = '-';
    
    /**
     * constant definition for maximum input string
     */
    private static final int MAX_INPUT_CHARS = 255;
  
    /**
     * constant definition of end of file marker
     */
    private static final int EOF_MARKER = -1;

    
    public static void main(String[] args)
       {
        boolean showBacktracking = true;
        boolean displayOperations = true;
        double displayTime = .5;
        MazeSolverClass msc = uploadData( "MazeData05.txt" );

        // show initial maze
        msc.displayMaze();
        
        // display starting position coordinates
        System.out.println( "Maze starts at row: "
                              + msc.getStartY() + ", and column: "
                              + msc.getStartX() );
        
        // sets flag to display tested but not working paths
        msc.setBackTrackMarker( showBacktracking );
        
        // sets flag to show maze after every array update
        msc.setDiagnosticDisplay( displayOperations, displayTime);
        
        // run solver
        msc.solveMaze();
        
        // show final result
        msc.displayMaze();
       }

    /**
     * Uploads String data from a formatted text data file
     * 
     * @param fileName String file name of file
     * 
     * @return String data array with data loaded
     */
    public static MazeSolverClass uploadData( String fileName )
       {
        char[][] mazeArray;
        int arrayHeight, arrayWidth, xStartIndex, yStartIndex;
        int rowIndex, colIndex, charAsInt;
      
        try
           {
            // Open FileReader 
            fileIn = new FileReader( fileName );
        
            // get leader line/number of rows
            getALine( MAX_INPUT_CHARS, COLON );
            arrayHeight = getAnInt( MAX_INPUT_CHARS );
            
            // get leader line/number of columns
            getALine( MAX_INPUT_CHARS, COLON );
            arrayWidth = getAnInt( MAX_INPUT_CHARS );
            
            // get leader line/x start location
            getALine( MAX_INPUT_CHARS, COLON );
            xStartIndex = getAnInt( MAX_INPUT_CHARS );
            
            // get leader line/y start location
            getALine( MAX_INPUT_CHARS, COLON );
            yStartIndex = getAnInt( MAX_INPUT_CHARS );
            
            mazeArray = new char[ arrayHeight ][ arrayWidth ];
            
            try
               {
                for( rowIndex = 0; rowIndex < arrayHeight; rowIndex++ )
                   {
                    for( colIndex = 0; colIndex < arrayWidth; colIndex++ )
                       {
                        charAsInt = fileIn.read();
                        
                        while( charAsInt < (int)SPACE )
                           {
                            charAsInt = fileIn.read();
                           }
                        
                        mazeArray[ rowIndex ][ colIndex ] = (char)( charAsInt );
                       }
                   }
                
                fileIn.close();
               }
            
            catch( IOException ioe )
               {
                System.out.println( 
                               "DATA ACCESS ERROR: Failure to upload array");
               }
            
           }
      
        catch( FileNotFoundException fnfe )
           {
            System.out.println(  
                           "DATA ACCESS ERROR: Failure to open input file" );
          
            return null;
           }
        
        return new MazeSolverClass( arrayHeight, arrayWidth, 
                                   yStartIndex, xStartIndex, mazeArray );        
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

    /**
     * gets an integer from the input string
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
           while( index < maxLength && !charInString( (char)inCharInt, 
                                                           "0123456789+-" ) )
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
