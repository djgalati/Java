package p1_package;

public class ShipClass
{
    /**
     * Constant value to show exposed ship state
     */
    public static final int EXPOSED_STATE = 1001;

    /**
     * Constant value to show hidden ship state
     */
    public static final int HIDDEN_STATE = 1002;

    /**
     * Constant value to show background state
     */
    public static final int BACKGROUND_STATE = 1003;

    /**
     * Constant value to show empty background without ship
     */
    public static final char BACKGROUND_LETTER = '+';

    /**
     * Constant value to show empty background without ship
     */
    public static final char MISSED_LETTER = 'X';

    /**
     * Member value representing ship
     */
    char shipLetter;

    /**
     * Member value containing ship or background state
     */
    int locationState;

    /**
     * Constructor initializes object background letter
     * and hidden state
     */
    public ShipClass()
    {
        shipLetter = BACKGROUND_LETTER;

        locationState = HIDDEN_STATE;
    }

    /**
     * Copy constructor initializes a new object
     * with data from a copied object
     *
     * @param copied ShipClass object to be copied
     */
    public ShipClass( ShipClass copied )
    {
        shipLetter = copied.shipLetter;

        locationState = copied.locationState;
    }

    /**
     * Gets location letter from object
     *
     * @param hidden integer indication for return value;
     * if passed HIDDEN_STATE, returns hidden character;
     * if passed EXPOSED_STATE, passes character only if exposed;
     * otherwise returns background character
     *
     * @return character as specified
     */
    public char getLocationLetter( int hidden )
    {
        if( hidden == HIDDEN_STATE )
        {
            return shipLetter;
        }

        else if( locationState == EXPOSED_STATE )

        {
            return shipLetter;
        }

        return BACKGROUND_LETTER;
    }

    /**
     * Overloaded method updates both object state and character
     *
     * @param state integer state to set in object
     *
     * @param letter character to set in object
     */
    public void updateLocationState( int state, char letter )
    {
        locationState = state;

        shipLetter = letter;
    }

    /**
     * Overloaded method only updates state
     *
     * @param state integer state to set in object
     */
    public void updateLocationState( int state )
    {
        locationState = state;
    }

}
