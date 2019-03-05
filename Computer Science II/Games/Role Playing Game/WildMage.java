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
public class WildMage extends Contestant{
    Random ran = new Random();
    WildMage(){
        super.setHealth(10+ran.nextInt(51));
        super.setDPA(ran.nextInt(7));
        super.setDescription("A wild Mage");
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
        for(int index=0; index<players.size(); index++){
            ((Contestant) players.get(index)).defend(this.getDPA(), "mage", players.indexOf(this), players, this);
        }
    }
  
    @Override
    public void defend(int DPA, String type, int index, ArrayList players, Contestant attacker){
        System.out.println(this.getDescription() + " ("+ this.getHealth()+") takes "+ attacker.getDPA() + " damage from  "+ attacker.getDescription());
        this.setHealth(this.getHealth()-attacker.getDPA());

        if(this.getHealth()<=0){
            super.setDescription("A dead mage");
            int killer = players.indexOf(attacker);
            ((Contestant)players.get(killer)).defend(5, "mage", players.indexOf(this), players, this);
            this.setDPA(0);
            super.dead();
        }
    }
}
