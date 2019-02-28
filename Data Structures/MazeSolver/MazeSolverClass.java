package p4_package;

public class MazeSolverClass
   {
    /**
     * Direction constant
     */
    private final int NORTH = 1001;

    /**
     * Direction constant
     */
    private final int EAST = 1002;
     /**
     * Direction constant
     */
    private final int SOUTH = 1003;
     /**
     * Direction constant
     */
    private final int WEST = 1004;
    
    /**
     * Cell size constant
     */
    private final int CELL_HEIGHT = 4;

    /**
     * Cell size constant
     */
    private final int HALF_CELL_HEIGHT = CELL_HEIGHT / 2;
    
    /**
     * Cell size constant
     */
    private final int CELL_WIDTH = 8;
    
    /**
     * Cell size constant
     */
    private final int HALF_CELL_WIDTH = CELL_WIDTH / 2;
    
    /**
     * Character constant for maze component
     */
    private final char EXIT_CHARACTER = ' ';

    /**
     * Character constant for maze component
     */
    private final char EXTERNAL_WALL_CHARACTER = '#';
    
    /**
     * Character constant for maze component
     */
    private final char HORIZONTAL_WALL_CHARACTER = '-';
    
    /**
     * Character constant for maze component
     */
    private final char VERTICAL_WALL_CHARACTER = '|';
    
    /**
     * Character constant for maze component
     */
    private final char TRACK_MARKER = '*';
    
    /**
     * Character constant for maze component
     */
    private final char BACKTRACK_MARKER = '.';
    
    /**
     * Character constant for maze component
     */
    private final char NOTRACK_MARKER = ' ';

    /**
     * Member data - number of rows and columns
     */
    private int numRows, numCols;
    
    /**
     * Member data - maze
     */
    private char[][] maze;
    
    /**
     * Member data - start x and y locations
     */
    private int startX, startY;
    
    /**
     * Member data - back track marker
     * <p>
     * Set to NOTRACK_MARKER when backtracking is not displayed;
     * set to BACKTRACK_MARKER when backtracking is displayed
     */
    private char backTrackMarker;
    
    /**
     * Set to display progress of maze search
     * <p>
     * When set to true, diagnosticDump is called after
     * every array update (i.e., every movement)
     */
    private boolean diagnosticRunFlag;
    
    /**
     * Set to time delay for diagnosticDump so that
     * progress can be observed real time
     */
    private double diagnosticDisplayTime;
    
    /**
     * Internal class holding location data for the maze operations
     * 
     * @author MichaelL
     *
     */
    private class LocationData
       {
        /**
         * Data used in simple LocationData internal class
         */
        int direction, xPos, yPos;

        /**
         * Initialization constructor for LocationData class
         * 
         * @param inDir integer value holding current direction
         * 
         * @param inY integer value holding current y position
         * 
         * @param inX integer value holding current x position
         */
        public LocationData( int inDir, int inY, int inX )
           {
            direction = inDir;
            xPos = inX;
            yPos = inY;
           }

        /**
         * Copy constructor for LocationData internal class
         * 
         * @param copied LocationData object to be copied
         */
        public LocationData( LocationData copied )
           {
            direction = copied.direction;
            xPos = copied.xPos;
            yPos = copied.yPos;
           }
       }
    
    /**
     * Initialization constructor for MazeSolverClass
     * 
     * @param rowHeight integer height of maze
     * 
     * @param colWidth integer width of maze
     * 
     * @param inStartY integer starting y position at edge of maze
     * 
     * @param inStartX integer starting x position at edge of maze
     * 
     * @param inMaze 2D character array representing maze
     */
    public MazeSolverClass( int rowHeight, int colWidth,
                             int inStartY, int inStartX, char[][] inMaze )
       {
           startX=inStartX;
           startY=inStartY;
           numCols=colWidth;
           numRows=rowHeight;
           maze=inMaze;
       }

    /**
     * Method shows current state of array if diagnostic run flag
     * is set; method also pauses program run for time duration
     * previously specified in setDiagnosticDisplay
     * <p>
     * Note: Method uses displayMaze for operation
     * 
     */
    private void diagnosticDump()
       {
        if( diagnosticRunFlag )
           {
            try
               {
                displayMaze();
                Thread.sleep( (int)diagnosticDisplayTime );
               }
            catch( InterruptedException ie )
               {
                ie.printStackTrace();
               }
           }
       }

    /**
     * Displays current maze
     */
    public void displayMaze()
       {
        int rowIndex, colIndex;
    
        for( rowIndex = 0; rowIndex < numRows; rowIndex++ )
           {
            for( colIndex = 0; colIndex < numCols; colIndex++ )
               {
                System.out.print( maze[ rowIndex ][ colIndex ] );
               }
            
            System.out.println();
           }
        
        System.out.println();
       }
    
    /**
     * Uses x and y starting position to find starting direction
     * of maze mover
     * 
     * @return integer value representing the direction
     */
    public int getInitialDirection()
       {
        int direction = NORTH;  // default, against bottom wall
        
        if( startX == 0 )  // against left wall
           {
            direction = EAST;
           }
    
        else if( startX == numCols - 1 )  // against right wall
           {
            direction = WEST;
           }
        
        else if( startY == 0 )  // against top
           {
            direction = SOUTH;
           }
        
        return direction;           
       }
    
    /**
     * Data accessor for start x position
     * 
     * @return integer value representing x start position
     */
    public int getStartX()
       {
        return startX;
       }

    /**
     * Data accessor for start y position
     * 
     * @return integer value representing y start position
     */
    public int getStartY()
       {
        return startY;
       }

    /**
     * Method looks from center of given cell in the current direction
     * to see if the maze mover is in a perimeter cell pointing at
     * an opening in the outer wall
     * <p>
     * Note: Method does not use if/else or switch
     *
     * @param currentLoc LocationData object holding 
     * the maze mover's current location status
     * 
     * @return Boolean result of specified test
     */
    private boolean isExitAhead( LocationData currentLoc )
       {
        boolean lookNorth = currentLoc.direction == NORTH
              && currentLoc.yPos - CELL_HEIGHT < 0
              && maze[ currentLoc.yPos - HALF_CELL_HEIGHT ][ currentLoc.xPos ] 
                                                             == EXIT_CHARACTER;
        boolean lookEast = currentLoc.direction == EAST
              && currentLoc.xPos + CELL_WIDTH > numCols
              && maze[ currentLoc.yPos ][ currentLoc.xPos + HALF_CELL_WIDTH ] 
                                                             == EXIT_CHARACTER;
        boolean lookSouth = currentLoc.direction == SOUTH
              && currentLoc.yPos + CELL_HEIGHT > numRows
              && maze[ currentLoc.yPos + HALF_CELL_HEIGHT ][ currentLoc.xPos ] 
                                                             == EXIT_CHARACTER;
        boolean lookWest = currentLoc.direction == WEST
              && currentLoc.xPos - CELL_WIDTH < 0
              && maze[ currentLoc.yPos ][ currentLoc.xPos - HALF_CELL_WIDTH ] 
                                                             == EXIT_CHARACTER;
        
        return lookNorth || lookEast || lookSouth || lookWest;
       }
    
    /**
     * Method tests current specified location to see if it is one
     * of the three possible wall characters
     * <p>
     * Note: Method does not use if/else or switch
     * 
     * @param yLoc integer y position
     * 
     * @param xLoc integer x position
     * 
     * @return Boolean result of specified test
     */
    private boolean isWallCharacter( int yLoc, int xLoc )
       {
        char testChar = maze[ yLoc ][ xLoc ];
        
        return testChar == HORIZONTAL_WALL_CHARACTER
                         || testChar == VERTICAL_WALL_CHARACTER
                                      || testChar == EXTERNAL_WALL_CHARACTER;
       }
    
    /** 
     * Method looks from center of given cell in the current direction
     * to see if the maze mover is in a cell pointing at a wall
     * <p>
     * Note: Method does not use if/else or switch
     * 
     * @param currentLoc LocationData object holding 
     * the maze mover's current location status
     * 
     * @return Boolean result of specified test
     */
    private boolean isWallAhead( LocationData currentLoc )
       {
        boolean lookNorth = currentLoc.direction == NORTH 
             && isWallCharacter( currentLoc.yPos - HALF_CELL_HEIGHT, 
                                                           currentLoc.xPos ); 

        boolean lookEast = currentLoc.direction == EAST
             && isWallCharacter( currentLoc.yPos, 
                                         currentLoc.xPos + HALF_CELL_WIDTH ); 

        boolean lookSouth = currentLoc.direction == SOUTH
             && isWallCharacter( currentLoc.yPos + HALF_CELL_HEIGHT, 
                                                            currentLoc.xPos ); 

        boolean lookWest = currentLoc.direction == WEST
             && isWallCharacter( currentLoc.yPos, 
                                         currentLoc.xPos - HALF_CELL_WIDTH ); 
       
        return lookNorth || lookEast || lookSouth || lookWest;
       }
    
    /**
     * Moves maze mover from center of one cell to center of next cell
     * in current direction
     * <p>
     * Note: Calls moveHelper with the TRACK_MARKER constant
     * 
     * @param currentLoc LocationData object holding 
     * the maze mover's current location status
     *  
     * @return LocationData result of moving to next cell
     */
    private LocationData moveToNextCell( LocationData currentLoc )
       {
        return moveHelper( currentLoc, TRACK_MARKER );
       }
    
    /**
     * Conducts the actual cell center to cell center move operation
     * for other methods
     *  
     * @param currentLoc LocationData object holding the maze mover's
     * current location status
     * 
     * @param marker character value holding marker to use
     * for showing maze mover trail
     * 
     * @return LocationData result of moving to next cell
     */
    private LocationData moveHelper( LocationData currentLoc, char marker )
       {
       int yIndex, xIndex, refPos;
       LocationData workingLoc = new LocationData( currentLoc );
       
       switch( workingLoc.direction )
          {
            case NORTH:
               refPos = workingLoc.yPos - 1;
              
               for( yIndex = refPos; yIndex > refPos - CELL_HEIGHT; yIndex-- )
                  {
                   placeMarker( yIndex, workingLoc.xPos, marker );
                  }
               
               workingLoc.yPos -= CELL_HEIGHT;
               break;
              
            case EAST:
               refPos = workingLoc.xPos + 1;
               for( xIndex = refPos; xIndex < refPos + CELL_WIDTH; xIndex++ )
                  {
                   placeMarker( workingLoc.yPos, xIndex, marker );
                  }
              
               workingLoc.xPos += CELL_WIDTH;
               break;
              
            case SOUTH:
               refPos = workingLoc.yPos + 1;
              
               for( yIndex = refPos; yIndex < refPos + CELL_HEIGHT; yIndex++ )
                  {
                   placeMarker( yIndex, workingLoc.xPos, marker );
                  }
              
               workingLoc.yPos += CELL_HEIGHT;
               break;
              
            case WEST:
               refPos = workingLoc.xPos - 1;
              
               for( xIndex = refPos; xIndex > refPos - CELL_WIDTH; xIndex-- )
                  {
                   placeMarker( workingLoc.yPos, xIndex, marker );
                  }
              
               workingLoc.xPos -= CELL_WIDTH;
               break;
           }
       
        return workingLoc;   
       }
    
    /**
     * Moves maze mover from center of cell to edge of cell
     * at maze exit
     * 
     * @param currentLoc LocationData object holding maze mover's
     * current location status
     * 
     * @return LocationData result of move to edge of maze at exit
     */
    private LocationData moveToExit( LocationData currentLoc )
       {
        int yIndex, xIndex;
        LocationData workingLoc = new LocationData( currentLoc );
        
        switch( workingLoc.direction )
           {
            case NORTH:
               for( yIndex = workingLoc.yPos; yIndex >= 0; yIndex-- )
                  {
                   placeMarker( yIndex, workingLoc.xPos, TRACK_MARKER );
                  }
               
               workingLoc.yPos = 0;
               break;
               
            case EAST:
               for( xIndex = workingLoc.xPos; xIndex < numCols; xIndex++ )
                  {
                   placeMarker( workingLoc.yPos, xIndex, TRACK_MARKER );
                  }
               
               workingLoc.xPos = numCols - 1;
               break;
               
            case SOUTH:
               for( yIndex = workingLoc.yPos; yIndex < numRows; yIndex++ )
                  {
                   placeMarker( yIndex, workingLoc.xPos, TRACK_MARKER );
                  }
               
               workingLoc.yPos = numRows - 1;
               break;
               
            case WEST:
               for( xIndex = workingLoc.xPos; xIndex >= 0; xIndex-- )
                  {
                   placeMarker( workingLoc.yPos, xIndex, TRACK_MARKER );
                  }
               
               workingLoc.xPos = 0;
               break;
           }
        
        return workingLoc;   
       }
    
    /**
     * Utility method that places a marker at a given location
     * 
     * @param yPos integer value representing the y position
     * 
     * @param xPos integer value representing the x position
     * 
     * @param marker character marker to be placed at location
     * in array
     */
    private void placeMarker( int yPos, int xPos, char marker )
       {
        maze[ yPos ][ xPos ] = marker;   
       }
    
    /**
     * Method sets back track marker to BACKTRACK_MARKER 
     * only if back track set flag is true; 
     * otherwise, sets back track mark to NOTRACK_MARKER
     * 
     * @param setFlag Boolean value for setting back track marker
     */
    public void setBackTrackMarker( boolean setFlag )
       {
        backTrackMarker = NOTRACK_MARKER;
        
        if( setFlag )
           {
            backTrackMarker = BACKTRACK_MARKER;
           }
       }
   
    /**
     * Method allows user to set the diagnostic display to show
     * path of maze mover as well as setting the time in seconds
     * for how long each display is paused
     * 
     * @param runFlag Boolean value that supports setting diagnostic run flag
     * 
     * @param displayTime double value in seconds for how long to show
     * display when diagnostic display is called 
     */
    public void setDiagnosticDisplay( boolean runFlag, double displayTime )
       {
        int secToMs = 1000;
        
        if( runFlag )
           {
            diagnosticRunFlag = true;
            
            diagnosticDisplayTime = displayTime * secToMs;
           }
       }
    
    /**
     * Method moves maze mover to center of cell from edge
     * of maze where start location was given
     * 
     * @param currentLoc LocationData object holding the maze mover status
     * at starting edge of maze
     * 
     * @param marker character marker to be used for marking maze mover track
     * 
     * @return LocationData result of move to center of first cell
     */
    private LocationData setFirstPosition( LocationData currentLoc,
                                                                char marker )
       {
        int yIndex, xIndex, refPos;
        LocationData workingLoc = new LocationData( currentLoc );
        
        switch( workingLoc.direction )
           {
            case NORTH:
               refPos = workingLoc.yPos;
               
               for( yIndex = refPos; 
                     yIndex >= refPos - HALF_CELL_HEIGHT; yIndex-- )
                  {
                   placeMarker( yIndex, workingLoc.xPos, marker );
                  }
               
               workingLoc.yPos -= HALF_CELL_HEIGHT;
               break;
               
            case EAST:
               refPos = workingLoc.xPos;
               for( xIndex = refPos; 
                     xIndex <= refPos + HALF_CELL_WIDTH; xIndex++ )
                  {
                   placeMarker( workingLoc.yPos, xIndex, marker );
                  }
               
               workingLoc.xPos += HALF_CELL_WIDTH;
               break;
               
            case SOUTH:
               refPos = workingLoc.yPos;
               
               for( yIndex = refPos; 
                     yIndex <= refPos + HALF_CELL_HEIGHT; yIndex++ )
                  {
                   placeMarker( yIndex, workingLoc.xPos, marker );
                  }
               
               workingLoc.yPos += HALF_CELL_HEIGHT;
               break;
               
            case WEST:
               refPos = workingLoc.xPos;
               
               for( xIndex = refPos; 
                     xIndex >= refPos - HALF_CELL_WIDTH; xIndex-- )
                  {
                   placeMarker( workingLoc.yPos, xIndex, marker );
                  }
               
               workingLoc.xPos -= HALF_CELL_WIDTH;
               break;
           }
        
        return workingLoc;   
       }
    
    /**
     * Public method for solving maze
     * <p>
     * Note: Calls solveMazeHelper with first cell position
     * of maze mover
     * 
     * @return Boolean result of attempt to find path through maze
     */
    public boolean solveMaze()
       {
        LocationData firstMove= setFirstPosition(new LocationData
        (getInitialDirection(), getStartY(), getStartX()),TRACK_MARKER);
        return solveMazeHelper(firstMove);
       }
    
    /**
     * Helper method for solveMaze, recursively called with current
     * maze mover location status
     * 
     * @param currentLoc LocationData holding maze mover location status
     * 
     * @return Boolean result of attempt to find path through maze
     */
    public boolean solveMazeHelper( LocationData currentLoc )
    {
        displayMaze();
        boolean outcome;
        
        currentLoc=turnToLeft(currentLoc);
        if(!isWallAhead(currentLoc) && !isExitAhead(currentLoc))
        {
            currentLoc=moveToNextCell(currentLoc);
            return solveMazeHelper(currentLoc);
        }
        else if(isExitAhead(currentLoc))
        {
            currentLoc=moveToExit(currentLoc);
            return true;
        }
        else
        {
            currentLoc=turnToRight(currentLoc);
            outcome=isWallAhead(currentLoc);
            if(outcome)
            {
                currentLoc=turnToRight(currentLoc);
                outcome=isWallAhead(currentLoc);
                if(outcome)
                {
                    currentLoc=turnToRight(currentLoc);
                    currentLoc=undoMoveToCell(currentLoc);
                    return solveMazeHelper(currentLoc);
                }
                else
                {
                    if(isExitAhead(currentLoc))
                    {
                        currentLoc=moveToExit(currentLoc);
                        return true;
                    }
                    currentLoc=moveToNextCell(currentLoc);
                    return solveMazeHelper(currentLoc);
                }
            }
            else
            {
                if(isExitAhead(currentLoc))
                {
                    currentLoc=moveToExit(currentLoc);
                    return true;
                }
                currentLoc=moveToNextCell(currentLoc);
                return solveMazeHelper(currentLoc);
            }
        }
    }
    
    /**
     * Method turns maze mover to the left relative to the maze mover's
     * current direction
     * 
     * @param currentLoc LocationData object holding maze mover's
     * current location status
     * 
     * @return LocationData result of turn
     */
    private LocationData turnToLeft( LocationData currentLoc )
       {
        LocationData workingLoc = new LocationData( currentLoc );
       
        switch( workingLoc.direction )
           {
            case NORTH:
               workingLoc.direction = WEST;
               break;
               
            case EAST:
               workingLoc.direction = NORTH;
               break;
               
            case SOUTH:
               workingLoc.direction = EAST;
               break;
               
            case WEST:
               workingLoc.direction = SOUTH;
               break;
           }
        
        return workingLoc;
       }

    /**
     * Method turns maze mover to the right relative to the maze mover's
     * current direction
     * 
     * @param currentLoc LocationData object holding maze mover's
     * current location status
     * 
     * @return LocationData result of turn
     */
    private LocationData turnToRight( LocationData currentLoc )
       {
        LocationData workingLoc = new LocationData( currentLoc );
        
        switch( workingLoc.direction )
           {
            case NORTH:
               workingLoc.direction = EAST;
               break;
               
            case EAST:
               workingLoc.direction = SOUTH;
               break;
               
            case SOUTH:
               workingLoc.direction = WEST;
               break;
               
            case WEST:
               workingLoc.direction = NORTH;
               break;
           }
        
        return workingLoc;
       }

    /**
     * Method sets maze mover back by one cell
     * <p>
     * Note: Calls moverHelper to conduct this action; also passes
     * in currently set back track marker
     * 
     * @param currentLoc LocationData object holding maze mover's
     * current location status
     * 
     * @return LocationData result of move
     */
    private LocationData undoMoveToCell( LocationData currentLoc )
       {
        return moveHelper( currentLoc, backTrackMarker ); 
       }
    
   }
