package p9_package;

/**
 *
 * @author djg32
 */
public class StudentClass 
{
    char gender;
    double gpa;
    String name;
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
    StudentClass(String inName, int inStudentID, char inGender, double inGPA)
    {
        gender=inGender;
        gpa=inGPA;
        name=inName;
        studentID=inStudentID;
    }
    
    /**
     * Copy constructor
     * <p>
     * Calls other constructor with copied data
     * @param copied StudentClass object to be copied
     */
    StudentClass(StudentClass copied)
    {
        gender=copied.gender;
        gpa=copied.gpa;
        name=copied.name;
        studentID=copied.studentID;
    }
    
    /**
     * Compares student objects
     * <p>
     * Note: Compares gpa as class key; returns integer result such that if 
     * this gpa is less than other gpa, a negative number is returned; if this
     * gpa is greater than other gpa, a positive number is returned; if this
     * gpa is equal to other gpa, zero is returned
     * @param other StudentClass object to be compared with this object
     * @return integer difference between gpa grades
     */
    int compareTo(StudentClass other)
    {
        int result;
        if(gpa>other.gpa)
        {
            result=1;
        }
        else if(gpa<other.gpa)
        {
            result=-1;
        }
        else
        {
            result=0;
        }
        return result;
    }
    
    /**
     * Overrides Object toString with local
     * @return toString in class java.lang.Object
     */
    @Override
    public String toString()
    {
        return (name + "/" + studentID + "/" + gender + "/" + gpa);
    }
}
