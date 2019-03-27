package p7_package;

/**
 *Class manages data for a state and its population
 * <p>
 * Note: Class implements Comparable interface, and therefore the
 * compareTo method
 * @author djg32
 */
public class StateDataClass implements java.lang.Comparable<StateDataClass>{
    
    private int population;
    private String state;
    
    /**
     * Default constructor, initializes all state data to default values
     */
    StateDataClass()
    {
        population=0;
        state=null;
    }
    
    /**
     * Copy constructor, sets all data to copied object
     * @param copiedSC StateDataClass object to be copied
     */
    StateDataClass(StateDataClass copiedSC)
    {
        population=copiedSC.population;
        state=copiedSC.state;
    }
    
    /**
     * Initialization constructor, initializes state data to default values
     * @param stateName String value with state name
     * @param inPopulation integer population for state
     */
    StateDataClass(String stateName, int inPopulation)
    {
        population=inPopulation;
        state=stateName;
    }
    
    /**
     * Provides required method for comparing this object to another 
     * StateDataClass object
     * <p>
     * Note: uses .length to get length of state string
     * <p>
     * Note: uses toLowerCase to test all characters as lower case
     * <p>
     * Note: returns negative value (not necessarily -1) if this data is less
     * than other data; returns positive value (not necessarily +1) if this
     * data is greater than other data; 
     * @param other object of StateDataClass with which to compare
     * @return returns zero if this item and other item are alphabetically
     * equal and the same length
     */
    @Override
    public int compareTo(StateDataClass other)
    {
        other.state.toLowerCase();
        this.state.toLowerCase();
        int length1 = other.state.length();
        int length2 = this.state.length();
        if(length1>length2)
        {
            return 1;
        }
        else if(length1<length2)
        {
            return -1;
        }
        else return 0;
        
    }
    
    /**
     * Changes character to lower case only if character was originally an
     * upper case letter
     * @param testCharCharacter to be tested, if it is upper case it will be
     * converted to lower case; otherwise the testChar will be returned 
     * unchanged
     * @return returns the lower case version of a letter if it was an upper 
     * case letter; otherwise, the character is returned unchanged
     */
    char toLowerCase(char testChar)
    {
        if(testChar>97 && testChar<122) return testChar;
        testChar-='A';
        testChar=+'a';
        return testChar;
    }
    
    /**
     * Overrides Object.toString, provides raw data from object
     * <p>
     * Output: "State Name: sssss, Population: ppppp", where sssss is state and
     * ppppp is population
     * @return String of object
     */
    @Override
    public String toString()
    {
        return ("State Name: " + this.state + ", Population: " + population);
    }
}
