/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;
import java.util.*;
/**
 *
 * @author djg323
 */
public class Warrior extends Contestant{
    Random ran = new Random();
    Warrior(){
        super.setHealth(20+ran.nextInt(21));
        super.setDPA(10+ran.nextInt(7));
        super.setDescription("A disciplined Warrior");
    } 
    
    @Override
    public int getHealth(){
        return super.getHealth();
    }
    
    @Override
    public int getDPA(){
        return super.getDPA();
    }
    
    @Override
    public void attack(ArrayList players){
        int index = ran.nextInt(players.size());
        this.setDPA(ran.nextInt(7)+10);
        ((Contestant) players.get(index)).defend(this.getDPA(), "war", players.indexOf(this), players, this);

    }
  
    @Override
    public void defend(int DPA, String type, int index, ArrayList players, Contestant attacker){
        if(type.equals("bzerk") || type.equals("war")){
            if(ran.nextInt(4)>0){
                System.out.println(this.getDescription() + " ("+ this.getHealth()+") takes "+ attacker.getDPA() + " damage from  "+ attacker.getDescription());
                this.setHealth(this.getHealth()-this.getDPA());
            }
            else{System.out.println(this.getDescription() + " ("+ this.getHealth()+") takes 0 damage from  "+ attacker.getDescription());}
        }
        if(type.equals("mage")){
            System.out.println(this.getDescription() + " ("+ this.getHealth()+") takes "+ attacker.getDPA() + " damage from  "+ attacker.getDescription());
            this.setHealth(this.getHealth()-attacker.getDPA());
        }

        if(this.getHealth()<=0){
            super.setDescription("A slain Warrior");
            this.setDPA(0);
            super.dead();
        }
    }
}

