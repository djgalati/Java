/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p10_package;

/**
 *
 * @author djg32
 */
public class StudentClass implements java.lang.Comparable<StudentClass>
{
    /**
     * Constant for identifying comma
     */
    private char COMMA = 44;
    
    /**
     * Gender data for class
     */
    char gender;
    
    /**
     * GPA data for class
     */
    double gpa;
    
    /**
     * Name data for class
     */
    String name;
    
    /**
     * Student ID data for class
     */
    int studentID;
    
    /**
     * Initialization constructor for data
     * <p>
     * Note: Class does not require a default constructor
     * @param inName name of student to be input into object
     * @param inStudentID ID number of student to be input into object
     * @param inGender gender of student to be input into object
     * @param inGPA gpa of student to be input into object
     */
    public StudentClass(String inName, int inStudentID,char inGender, 
            double inGPA)
    {
        gender = inGender;
        gpa = inGPA;
        name = inName;
        studentID = inStudentID;
    }
    
    /**
     * Copy constructor
     * <p>
     * Calls other constructor with copied data
     * @param copied StudentClass object to be copied
     */
    public StudentClass(StudentClass copied)
    {
        gender = copied.gender;
        gpa = copied.gpa;
        name = copied.name;
        studentID = copied.studentID;
    }
    
    /**
     * Compares student objects
     * <p>
     * Note: Compares name as class key; returns integer result such that if
     * this name is less than other name, a negative number is returned; if
     * this name is greater than other name, a positive number is returned; if
     * this name is equal to, and the same length as other name, zero is
     * returned
     * @param other StudentClass object to be compared with this object
     * @return integer difference between the names
     */
    @Override
    public int compareTo(StudentClass other)
    {
        int result;
        if(other.name.equals(this.name))
        {
            result = -1;
        }
        else if(other.name.equals(this.name))
        {
            result = 1;
        }
        else if(other.name == this.name)
        {
            result = 0;
        }
        else
        {
            result = 0;
        }
        return result;
    }
    
    /**
     * Creates hash value from local data
     * <p>
     * Algorithm: Accesses the integer values of the characters in the name
     * string up to but not including the first comma; then returns the sum
     * <p>
     * Uses .charAt
     * @return integer hash value representing data
     */
    @Override
    public int hashCode()
    {
        int total = 0;
        int iteration = 0;
        String code = this.toString();
        while(code.charAt(iteration)!=COMMA)
        {
            total = total + code.charAt(iteration);
            iteration++;
        }
        return total;
    }
    
    /**
     * Overrides Object toString with local
     * @return toString in class java.lang.Object
     */
    @Override
    public String toString()
    {
        return (name+"/"+studentID+"/"+gender+"/"+gpa+", Probed Index: ");
    }
}
