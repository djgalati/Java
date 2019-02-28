package rpg;

import java.util.ArrayList;
import java.util.Random;

public class Arena {
    public ArrayList<Contestant> players= new ArrayList();
    ArrayList<Contestant> list= new ArrayList();
    Random ran = new Random();
    String out=null;
    
    public Arena() {
    }

    public void add(Contestant contestant) {
        players.add(contestant);
    }

    public void playRound() {
        for(int index =0; index<players.size(); index++){
            if(players.get(index).getHealth()>0){
                players.get(index).attack(players);
            }
        } 
        if(players.size()<=1){
            getWinner();   
        }                                                                   
    }

    public String getDescription() {
        out+=("\n In the arena...");
        out+=("\n "+ players.get(0).getDescription() + " ("+ players.get(0).getHealth()+")");
        out+=("\n "+ players.get(1).getDescription() + " ("+ players.get(1).getHealth()+")");
        out+=("\n "+ players.get(2).getDescription() + " ("+ players.get(2).getHealth()+")");
        out+=("\n "+ players.get(3).getDescription() + " ("+ players.get(3).getHealth()+")");
        out+=("\n "+ players.get(4).getDescription() + " ("+ players.get(4).getHealth()+")");
        out+=("\n "+ players.get(5).getDescription() + " ("+ players.get(5).getHealth()+")");
        return out;
    }

    public Contestant getWinner() {
        int index=0;
        int maxHealth=0;
        if(players.get(0).getDead()>=5){
            for(int i=0; i<players.size(); i++){
                if(players.get(i).getHealth()>maxHealth){
                    index=i;
                    maxHealth=players.get(i).getHealth();
                }
            }
        return players.get(index);
        }
    return null;
    }
}
