import java.util.*;
import java.lang.*;

public class Nim {
    public static void main(String[] args) {
	Scanner UI = new Scanner(System.in);
	int choice=0;
            int pile=pileSize();
	int player= randomInt();
	int computer= randomInt();
	while(pile>0){
	System.out.println("Current number of marbles left in pile: "+ pile);
		switch(player){
			case 0: //player will have first move
				System.out.println("How many marbles do you want to remove: ");
				choice = UI.nextInt();
				while(!legalChoice(choice, pile)){
					System.out.println("Not a valid choice. How many marbles do you want to remove: ");
					choice = UI.nextInt();
				}
				player=1;
				break;
                        case 1: //computer will have first move
				if(computer==1){ //Computer plays smart
					choice=smartMove(pile);
					System.out.println("Computer removes "+choice+" marble(s)");
				}
				else{ //computer plays dumb
					choice=dumbMove(pile);
					System.out.println("Computer removes "+choice+" marble(s)");
				}

				player=0;
				break;
		}
		pile-=choice;
	}
	if(player==0) System.out.println("The Computer took the last marble. User wins!");
	else if(player==1) System.out.println("The User took the last marble. Computer wins!");
    }

    //Custom methods written below
    public static int randomInt(){ //Generate a random integer between 0 and 1 to decide whether the computer or the human takes the first turn
	Random num = new Random();
	return num.nextInt(2);
    }

    public static int pileSize(){ //Generate a random integer between 10 and 100 to denote the initial size of the pile
	Random num = new Random();
	return (10 + num.nextInt(91));
    }


    public static int smartMove(int pileS){ //the computer takes off enough marbles to make the size of the pile a power of two minus 1
	if(powerOfTwo(pileS)){
		if(pileS>63) return (pileS-63);
		else if(pileS>31) return (pileS-31);
		else if(pileS>15) return (pileS-15);
		else if(pileS>7) return (pileS-7);
		else if(pileS>3) return (pileS-3);
		else return 1;
	}
        return 1;
    }

    public static int dumbMove(int pileS){ // the computer simply takes a random legal value (between 1 and n / 2) from the pile whenever it has a turn
	Random num = new Random();
        if(pileS>1)return (1 + num.nextInt((pileS/2)));
        else return 1;
    }

    public static boolean powerOfTwo(int pileS){ //Used to see if computer can make a smart move
	if(pileS != 3 ||pileS != 7 ||pileS != 15 ||pileS != 31 ||pileS != 63) return true;
	else return false;
    }

    public static boolean legalChoice(int num, int pileS){ //Check if any players turn is a legal choice
	if (num>=1 && num<=(pileS/2)) return true;
        else if(pileS==1) return true;
	else return false;
    }
}

