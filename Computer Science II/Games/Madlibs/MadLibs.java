import java.util.Scanner;//Imports code for user input class

public class MadLibs {

    public static void main(String[] args) {

        Scanner UI = new Scanner(System.in); //creates scanner object to input users strings

        //Lines 11-63 is repeated user input so the computer can create the story
        System.out.print("Please enter an adjective: ");
        String A1 = UI.nextLine();

	System.out.print("Please enter a verb: ");
        String V1 = UI.nextLine();

	System.out.print("Please enter an adverb: ");
        String AV1 = UI.nextLine();

	System.out.print("Please enter a noun (Plural): ");
        String N1 = UI.nextLine();

	System.out.print("Please enter a verb: ");
        String V2 = UI.nextLine();

	System.out.print("Please enter an adjective: ");
        String A2 = UI.nextLine();

	System.out.print("Please enter a noun: ");
        String N2 = UI.nextLine();

	System.out.print("Please enter a time of day: ");
        String TOD1 = UI.nextLine();

	System.out.print("Please enter a location: ");
        String L1 = UI.nextLine();

	System.out.print("Please enter a type of food (Plural): ");
        String F1 = UI.nextLine();

	System.out.print("Please enter a verb: ");
        String V3 = UI.nextLine();

	System.out.print("Please enter an adverb: ");
        String AV2 = UI.nextLine();

	System.out.print("Please enter a past tense verb: ");
        String PTV1 = UI.nextLine();

	System.out.print("Please enter a body part (Plural): ");
        String BP1 = UI.nextLine();

	System.out.print("Please enter a past tense verb: ");
        String PTV2 = UI.nextLine();

	System.out.print("Please enter an descriptive adjective: ");
        String AN1 = UI.nextLine();

	System.out.print("Please enter an amount of days: ");
        String D1 = UI.nextLine();

	System.out.print("Please enter a past tense verb: ");
        String PTV3 = UI.nextLine();
        
        //Lines 66-77 is the code that generates the story to be outputted to the user as a MadLib
	System.out.println("                              A(n) "+A1+" NAU Christmas");
	System.out.println("_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_");
	System.out.println("One day at NAU, the students "+V1+" "+AV1+" as christmas time was just arriving.");
	System.out.println("Most of the "+N1+" had left town to "+V2+" with their families.");     
	System.out.println("Some "+A2+" students stayed on campus for Rita Chang's "+N2+" extravaganza.");
	System.out.println("Events for the "+TOD1+" were held at "+L1+".");  
	System.out.println("The first event was Rita's hot "+F1+" to kick off the festive holiday"); 
	System.out.println("Following that, the campus then decided to "+V3+" Rita as a christmas suprise!");
	System.out.println("In a(n) "+AV2+" rage, Rita "+PTV1+" the attendees.");
	System.out.println("With fear in their "+BP1+", everyone "+PTV2+" away from Rita's path of "+AN1+".");
	System.out.println("Once the storm had settled, Christmas had been "+D1+" days ago.");
	System.out.println("And that my friends, is the story of how Rita Chang "+PTV3+" Christmas at NAU.");
    }
    
}
