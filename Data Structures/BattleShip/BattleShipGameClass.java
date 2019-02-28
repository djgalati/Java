package p1_package;
import java.util.Random;

/**
 *
 * @author djg323
 */
public class BattleShipGameClass {
    /**
     * Constant value to define is a boat is an aircraft carrier
     */
    public static final int AIRCRAFT_CARRIER = 65;
    
    /**
     * Constant value to define length of aircraft carrier
     */
    private static final int AIRCRAFT_CARRIER_LENGTH = 5;

    /**
     * Constant value to define is a boat is a battleship
     */
    public static final int BATTLESHIP = 66;
    
    /**
     * Constant value to define battleship length
     */
    private static final int BATTLESHIP_LENGTH = 4;

    /**
     * Constant value to define if the action is done by the computer
     */
    public static final int COMPUTER = 103;

    /**
     * Constant value to define is a boat is a cruiser
     */
    public static final int CRUISER = 67;
    
    /**
     * Constant value to define length of cruiser
     */
    private static final int CRUISER_LENGTH = 3;

    /**
     * Constant value to define is a boat is a destroyer
     */
    public static final int DESTROYER = 68;
    
    /**
     * Constant value to define length of destroyer
     */
    private static final int DESTROYER_LENGTH = 2;

    /**
     * Constant value to define is a boat is a frigate
     */
    public static final int FRIGATE = 70;
    
    /**
     * Constant value to define length of frigate
     */
    private static final int FRIGATE_LENGTH = 1;

    /**
     * Constant value to define Horizontal
     */
    public static final int HORIZONTAL = 72;
    
    /**
     * Constant value to define if the action is done by the human
     */
    public static final int HUMAN = 102;
    
    /**
     * Constant value to define a blank space
     */
    private final char SPACE = 32;

    /**
     * Constant value to define Vertical
     */
    public static final int VERTICAL = 86;
    
    private final ShipClass[][] computerArray;
    private final ShipClass[][] humanArray;
    private final int arrayWidth;
    private final int arrayHeight;
    Random random = new Random();

    /**
     * Create an instance of a game of Battle Ship
     * @param height The game boards height
     * @param width The game boards width
     */
    public BattleShipGameClass(int height, int width)
    {
        int loopCount;
        int innerLoopCount;
        computerArray=new ShipClass[height][width];
        humanArray=new ShipClass[height][width];
        arrayHeight=height;
        arrayWidth=width;
        for(loopCount=0; loopCount<arrayHeight; loopCount++)
        {
            for(innerLoopCount=0;innerLoopCount<arrayWidth;innerLoopCount++)
            {
                computerArray[loopCount][innerLoopCount]=new ShipClass();
                humanArray[loopCount][innerLoopCount]= new ShipClass();
                
            }
        }
    }

    /**
     * Creates an instance of a game of Battle ship based on another instance 
     * passed to this method
     * @param copied the instance of the Battle ship game the new game will be 
     * duplicated from
     */
    public BattleShipGameClass(BattleShipGameClass copied)
    {
        int loopCount;
        int innerLoopCount;
        arrayHeight=copied.arrayHeight;
        arrayWidth=copied.arrayWidth;
        computerArray=new ShipClass[arrayHeight][arrayWidth];
        humanArray=new ShipClass[arrayHeight][arrayWidth];
        for(loopCount=0; loopCount<arrayHeight; loopCount++)
        {
            for(innerLoopCount=0;innerLoopCount<arrayWidth;innerLoopCount++)
            {
                computerArray[loopCount][innerLoopCount]=new ShipClass
                (copied.computerArray[loopCount][innerLoopCount]);
                humanArray[loopCount][innerLoopCount]=new ShipClass
                (copied.humanArray[loopCount][innerLoopCount]);
            }
        }
                
    }

    /**
     * Accepts a char to verify user input is a valid choice
     * @param testLetter a char generated for the computer or input from the user
     * @return True if the char accepted is valid for game play
     */
    private boolean checkShipLetter(char testLetter)
    {
        testLetter = toUpper(testLetter);
        switch(testLetter)
        {
            case AIRCRAFT_CARRIER:
                  return true;
            case BATTLESHIP:
                  return true;
            case CRUISER:
                  return true;
            case DESTROYER:
                  return true;
            case FRIGATE:
                  return true;
            default:
                   return false;
        }
    }
    
    /**
     * Verifies missile positioning and fires missiles for the computer
     * @return True if the missile was successfully launched
     */
    public boolean computerFiresMissile()
    {
        int randomX,randomY;
        do
        {
            randomX = getRandBetween(0,arrayWidth);
            randomY = getRandBetween(0,arrayHeight);
        }
        while(isMissInArray(HUMAN, randomX, randomY));
        return(fireMissile(COMPUTER, randomX, randomY));
    }

    /**
     * Outputs computer and player game boards so the player has a visual of what
     * the game boards status looked like
     * @param displayState Passed from the main class, this will either hide all
     * elements on the game board or show them base on this parameter
     */
    public void displayFields(int displayState)
    {
        int loopCount;
        int innerLoopCount;
        printCentered("Player Board", arrayWidth);
        printCentered("",8); 
        printCentered("Computer Board", arrayWidth);
        System.out.println();
        for(loopCount=0;loopCount<arrayHeight;loopCount++)
        {
            for(innerLoopCount=0;innerLoopCount<arrayWidth;innerLoopCount++)
            {
                System.out.print(humanArray[loopCount][innerLoopCount]
                .getLocationLetter(displayState));
                
            }
            printCentered("",8); 
            for(innerLoopCount=0;innerLoopCount<arrayWidth;innerLoopCount++)
            {
                System.out.print(computerArray[loopCount][innerLoopCount]
                .getLocationLetter(displayState));
                
            }
            System.out.println();
        }
    }

    /**
     * Accepts a player who is firing the missile along with x,y coordinates for
     * the selected target
     * @param player The player who is firing the missile
     * @param xPos The x position of the location
     * @param yPos The y position of the location
     * @return 
     */
    public boolean fireMissile(int player, int xPos, int yPos)
    { 
        if(player==HUMAN)
        {
            switch(computerArray[yPos][xPos].shipLetter)    
            {
                case AIRCRAFT_CARRIER:
                    computerArray[yPos][xPos].updateLocationState
                    (ShipClass.EXPOSED_STATE);
                    break;
                case BATTLESHIP:
                    computerArray[yPos][xPos].updateLocationState
                    (ShipClass.EXPOSED_STATE);
                    break;
                case CRUISER:
                    computerArray[yPos][xPos].updateLocationState
                    (ShipClass.EXPOSED_STATE);
                    break;
                case DESTROYER:
                    computerArray[yPos][xPos].updateLocationState
                    (ShipClass.EXPOSED_STATE);
                    break;
                case FRIGATE:
                    computerArray[yPos][xPos].updateLocationState
                    (ShipClass.EXPOSED_STATE);
                    break;
                default:
                    computerArray[yPos][xPos].updateLocationState
                    (ShipClass.EXPOSED_STATE, ShipClass.MISSED_LETTER);
                    return false;
            }
            return true;
        }
        else if(player==COMPUTER)
        {
            switch(humanArray[yPos][xPos].shipLetter)
            {
                case AIRCRAFT_CARRIER:
                    humanArray[yPos][xPos].updateLocationState
                    (ShipClass.EXPOSED_STATE);
                    break;
                case BATTLESHIP:
                    humanArray[yPos][xPos].updateLocationState
                    (ShipClass.EXPOSED_STATE);
                    break;
                case CRUISER:
                    humanArray[yPos][xPos].updateLocationState
                    (ShipClass.EXPOSED_STATE);
                    break;
                case DESTROYER:
                    humanArray[yPos][xPos].updateLocationState
                    (ShipClass.EXPOSED_STATE);
                    break;
                case FRIGATE:
                    humanArray[yPos][xPos].updateLocationState
                    (ShipClass.EXPOSED_STATE);
                    break;
                default:
                    humanArray[yPos][xPos].updateLocationState
                    (ShipClass.EXPOSED_STATE, ShipClass.MISSED_LETTER);
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Generates a random number between two integers passed
     * @param low The lower inclusive bound
     * @param high The upper inclusive bound
     * @return A randomly picked number between the upper and lower bounds
     */
    private int getRandBetween(int low,int high)
    {
        return (low+(random.nextInt(high-low)));
    }
    
    /**
     * Returns the score of the requested player
     * @param player HUMAN or COMPUTER
     * @return Score of player passed
     */
    public int getScore(int player)
    { 
        int loopCount;
        int innerLoopCount;
        int cpuScore=0;
        int playerScore=0;
        for(loopCount=0; loopCount<arrayHeight; loopCount++)
        {
            for(innerLoopCount=0;innerLoopCount<arrayWidth;innerLoopCount++)
            {
                if(player==HUMAN)
                {
                    if(computerArray[loopCount][innerLoopCount].getLocationLetter
                    (ShipClass.EXPOSED_STATE)!=ShipClass.MISSED_LETTER) 
                    {
                        if(computerArray[loopCount][innerLoopCount].getLocationLetter
                        (ShipClass.EXPOSED_STATE)!=ShipClass.BACKGROUND_LETTER)
                        {
                            playerScore++;
                        }
                    }
                }
                else if(player==COMPUTER)
                {
                    if(humanArray[loopCount][innerLoopCount].getLocationLetter
                    (ShipClass.EXPOSED_STATE)!=ShipClass.MISSED_LETTER)
                    {
                        if(humanArray[loopCount][innerLoopCount].getLocationLetter
                        (ShipClass.EXPOSED_STATE)!=ShipClass.BACKGROUND_LETTER)
                        {
                            cpuScore++;
                        }
                    }
                }
            }
        }
        switch (player) 
        {
            case HUMAN:
                return playerScore;
                
            case COMPUTER:
                return cpuScore;
                
            default:
                return cpuScore;
        }
    }
    
    /**
     * Returns the total length of a given ship
     * @param typeOfShip A char that is passed
     * @return the length of the ship passed
     */
    private int getShipLength(char typeOfShip)
    { 
        typeOfShip = toUpper(typeOfShip);
        switch(typeOfShip)
        {
            case AIRCRAFT_CARRIER:
                return AIRCRAFT_CARRIER_LENGTH;
            case BATTLESHIP:
                return BATTLESHIP_LENGTH;
            case CRUISER:
                return CRUISER_LENGTH;
            case DESTROYER:
                return DESTROYER_LENGTH;
            case FRIGATE:
                return FRIGATE_LENGTH;
            default:
                return 0;
        }
    }

    /**
     * Returns the name of a given ship
     * @param typeOfShip A char that is passed
     * @return The string name corresponding to the passed char
     */
    public String getShipName(char typeOfShip)
    { 
        typeOfShip = toUpper(typeOfShip);
        switch(typeOfShip)
        {
            case AIRCRAFT_CARRIER:
                return "AIRCRAFT_CARRIER";
            case BATTLESHIP:
                return "BATTLESHIP";
            case CRUISER:
                return "CRUISER";
            case DESTROYER:
                return "DESTROYER";
            case FRIGATE:
                return "FRIGATE";
        }
        return "null";
    }
    
    /**
     * Return is a miss in found in the array
     * @param player HUMAN or COMPUTER
     * @param xPos The x position to be located
     * @param yPos The y position to be located
     * @return True if the (x,y) location does not have a + (BACKGROUND_LETTER)
     */
    private boolean isMissInArray(int player,int xPos,int yPos)
    {
        switch(player)
        {
            case HUMAN:
                if(humanArray[yPos][xPos].getLocationLetter
                (ShipClass.EXPOSED_STATE)==ShipClass.BACKGROUND_LETTER)
                {
                    return false;
                }
            case COMPUTER:
                if(computerArray[yPos][xPos].getLocationLetter
                (ShipClass.EXPOSED_STATE)==ShipClass.BACKGROUND_LETTER)
                {
                    return false;
                }
        }
        return true;
    }

    /**
     * Verifies Variables passed allows for a ship to be placed, then updates 
     * the ships location on the game board
     * @param shipType A char representing the type of ship
     * @param player HUMAN or COMPUTER who is placing the ship
     * @param xPos The x position of the bow of the ship
     * @param yPos The y position of the bow of the ship
     * @param orientation HORIZONTAL or VERTICAL
     * @return True if ship was successfully placed
     */
    public boolean placeShip(char shipType, int player, int xPos, int yPos, char orientation)
    { 
        int iteration;
        orientation = toUpper(orientation);
        shipType = toUpper(shipType);
        if(!checkShipLetter(shipType))
        {
            return false;
        }
        if(player==HUMAN)
        {
            switch(orientation)
            {
                case VERTICAL:
                    if((yPos+getShipLength(shipType)-1)>=arrayHeight)
                    {
                        return false;
                    }
                    for(iteration=0;iteration<getShipLength(shipType);iteration++)
                    {
                        if(humanArray[yPos+iteration][xPos].shipLetter!=
                        ShipClass.BACKGROUND_LETTER)
                        {
                            return false;
                        }
                    }
                    for(iteration=0;iteration<getShipLength(shipType);iteration++)
                    {
                        humanArray[yPos+iteration][xPos].updateLocationState
                        (ShipClass.HIDDEN_STATE,shipType);
                    }
                    return true;
                case HORIZONTAL:
                    if((xPos+getShipLength(shipType)-1)>=arrayWidth)
                    {
                        return false;
                    }
                    for(iteration=0;iteration<getShipLength(shipType);iteration++)
                    {
                        if(humanArray[yPos][xPos+iteration].shipLetter!=
                        ShipClass.BACKGROUND_LETTER)
                        {
                            return false;
                        }
                    }
                    for(iteration=0;iteration<getShipLength(shipType);iteration++)
                    {
                        humanArray[yPos][xPos+iteration].updateLocationState
                        (ShipClass.HIDDEN_STATE,shipType);
                    }
                    return true;
                default:
                    return false;
            }
        }
        else if(player==COMPUTER)
        {
            switch(orientation)
            {
                case VERTICAL:
                    if((yPos+getShipLength(shipType)-1)>=arrayHeight)
                    {
                        return false;
                    }
                    for(iteration=0;iteration<getShipLength(shipType);iteration++)
                    {
                        if(computerArray[yPos+iteration][xPos].shipLetter!=
                        ShipClass.BACKGROUND_LETTER)
                        {
                            return false;
                        }
                    }
                    for(iteration=0;iteration<getShipLength(shipType);iteration++)
                    {
                        computerArray[yPos+iteration][xPos].updateLocationState
                        (ShipClass.HIDDEN_STATE,shipType);
                    }
                    return true;
                  
                case HORIZONTAL:
                    if((xPos+getShipLength(shipType)-1)>=arrayWidth)
                    {
                        return false;
                    }
                    for(iteration=0;iteration<getShipLength(shipType);iteration++)
                    {
                        if(computerArray[yPos][xPos+iteration].shipLetter!=
                        ShipClass.BACKGROUND_LETTER)
                        {
                            return false;
                        }
                    }
                    for(iteration=0;iteration<getShipLength(shipType);iteration++)
                    {
                        computerArray[yPos][xPos+iteration].updateLocationState
                        (ShipClass.HIDDEN_STATE,shipType);
                    }
                    return true;
                    
                default:
                    return false;
            }
        }
        return false;
    }

    /**
     * Prints the game board headers centered over the game
     * @param toPrint The text of the game boards owner <p> e.g. "Player Board"</p>
     * @param blockSize The total amount of space the text will have to be printed
     */
    private void printCentered(String toPrint,int blockSize)
    { 
        int length = ((blockSize-toPrint.length())/2);
        printChars(length, SPACE);
        System.out.print(toPrint);
        printChars(length, SPACE);
        
    }
    
    /**
     * Repeatedly prints a char that is passed
     * @param numChars The amount of times the char passed should be printed
     * @param outChar the char to be printed
     */
    private void printChars(int numChars,char outChar)
    {
        if(numChars>0)
        {
            System.out.print(outChar);
            printChars(numChars-1,outChar);
        }
         
    }
    
    /**
     * Sets an equal amount of computer ships as human ships set
     * @param numShips The amount of ships that need to be placed
     */
    void setComputerShips(int numShips)
    { 
        char charOrientation;
        char ship=FRIGATE;
        int iteration;
        for(iteration=0;iteration<numShips;iteration++)
        {
            int randomX = getRandBetween(0,computerArray.length);
            int randomY = getRandBetween(0,computerArray[0].length);
            int randomOrientation = getRandBetween(0,1);
            int randomShip;
            if(randomOrientation==0)
            {
                charOrientation=VERTICAL;
                randomShip=getRandBetween(0,4);
            }
            else
            {
                charOrientation=HORIZONTAL;
                randomShip=getRandBetween(0,4);
            }
            switch(randomShip)
            {
                case 0:
                    ship=FRIGATE;
                    break;

                case 1:
                    ship=DESTROYER;
                    break;

                case 2:
                    ship=CRUISER;
                    break;

                case 3:
                    ship=BATTLESHIP;
                    break;

                case 4:
                    ship=AIRCRAFT_CARRIER;
                    break;
            }
            if((getShipLength(ship)+randomX)>=arrayWidth)
            {
                if(charOrientation==HORIZONTAL)
                {
                    do
                    {
                        randomShip = getRandBetween(0,4);
                        switch(randomShip)
                        {
                            case 0:
                                ship=FRIGATE;
                                break;

                            case 1:
                                ship=DESTROYER;
                                break;

                            case 2:
                                ship=CRUISER;
                                break;

                            case 3:
                                ship=BATTLESHIP;
                                break;

                            case 4:
                                ship=AIRCRAFT_CARRIER;
                                break;
                        }
                    }
                    while(getShipLength(ship)>=arrayWidth);
                }
            }
            else if((getShipLength(ship)+randomY)>=arrayHeight)
            {
                if(charOrientation==VERTICAL)
                {
                    do
                    {
                        randomShip = getRandBetween(0,4);
                        switch(randomShip)
                        {
                            case 0:
                                ship=FRIGATE;
                                break;

                            case 1:
                                ship=DESTROYER;
                                break;

                            case 2:
                                ship=CRUISER;
                                break;

                            case 3:
                                ship=BATTLESHIP;
                                break;

                            case 4:
                                ship=AIRCRAFT_CARRIER;
                                break;
                        }
                    }
                    while(getShipLength(ship)>=arrayWidth);
                }
            }
            if(!placeShip(ship,COMPUTER,randomX,randomY,charOrientation))
            {
                numShips++;
            }
        }
    }
    
    /**
     * Converts any lower case chars to upper case chars <p> e.g. 'a' to 'A'</p>
     * @param testLetter The char passed to be converted to upper case
     * @return A upper case char
     */
    private char toUpper(char testLetter)
    { 
        if(testLetter>='a' && testLetter<='z')
        {
            testLetter=(char)(testLetter-(32));
        }
        return testLetter;
    }

}


