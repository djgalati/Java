/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    StateDataClass()
    {
        population=0;
        state=null;
    }
    
    StateDataClass(StateDataClass copiedSC)
    {
        population=copiedSC.population;
        state=copiedSC.state;
    }
    
    StateDataClass(String stateName, int inPopulation)
    {
        population=inPopulation;
        state=stateName;
    }
    
    
    @Override
    public int compareTo(StateDataClass other)
    {
        return 0;
    }
    
    
    char toLowerCase(char testChar)
    {
        return 'a';
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
        return "";
    }
}
