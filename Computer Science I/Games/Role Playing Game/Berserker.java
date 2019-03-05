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
public class Berserker extends Contestant{
    Random ran = new Random();
    Berserker(){
        super.setHealth(35);
        super.setDPA(20);
        super.setDescription("A raging Berserker");
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
        ((Contestant) players.get(index)).defend(this.getDPA(), "bzerk", players.indexOf(this), players, this);

    }
  
    @Override
    public void defend(int DPA, String type, int index, ArrayList players, Contestant attacker){
        if(type.equals("bzerk")){
            System.out.println(this.getDescription() + " ("+ this.getHealth()+") takes "+ attacker.getDPA() + " damage from  "+ attacker.getDescription());
            this.setHealth(this.getHealth()-attacker.getDPA());
        }
        if(type.equals("war")){
            System.out.println(this.getDescription() + " ("+ this.getHealth()+") takes "+ attacker.getDPA()*2 + " damage from  "+ attacker.getDescription());
            this.setHealth((this.getHealth()-attacker.getDPA()*2));
        }
        if(type.equals("mage")){
            System.out.println(this.getDescription() + " ("+ this.getHealth()+") takes "+ attacker.getDPA()/2 + " damage from  "+ attacker.getDescription());
            this.setHealth((this.getHealth()-attacker.getDPA()/2));
        }

        if(this.getHealth()<=0){
            super.setDescription("A slain Berserker");
            this.setDPA(0);
            super.dead();
        }
    }
}
