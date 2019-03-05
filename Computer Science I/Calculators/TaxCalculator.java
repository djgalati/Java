import java.util.*;
public class TaxCalculator{
	
	public static void main(String args[]){
		Scanner UI = new Scanner(System.in);
		System.out.println("Enter your marital status. married or single: ");
		String married = UI.next().toLowerCase();
		System.out.println("What is your annual income?: $");
		int income = UI.nextInt();
		double taxRate = 0;

		if(married.equals("single")){
			if(income<8000){
				taxRate = income*.1;
				System.out.println("You owe $"+ taxRate + " in tax.");
			}
			else if(income<32000){
				taxRate = (income -8000)*.15 + 800;
				System.out.println("You owe $"+ taxRate + " in tax.");
			}
			else{
				taxRate = (income -32000)*.25 + 4400;
				System.out.println("You owe $"+ taxRate + " in tax.");
			}
		}
		else if(married.equals("married")){
			if(income<16000){
				taxRate = income*.1;
				System.out.println("You owe $"+ taxRate + " in tax.");
			}
			else if(income<64000){
				taxRate = (income-16000)*.15 + 1600;
				System.out.println("You owe $"+ taxRate + " in tax.");
			}
			else{
				taxRate = (income-32000)*.25 + 8800;
				System.out.println("You owe $"+ taxRate + " in tax.");
			}
		}
		else{ //nop
			System.out.println("This is not a correct marital status");
		}
	}
}
