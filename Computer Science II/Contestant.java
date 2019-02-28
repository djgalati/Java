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
public class Contestant {
    int health;
    int DPA;
    String description=null;
    int dead=0;
    Contestant(){
    }
    public int getHealth(){
        return health;
    }
    
    public void setHealth(int update){
        health = update;
    }
    
    public int getDPA(){
        return DPA;
    }
    
    public void setDPA(int num){
        DPA=num;
    }
    
    public void setDescription(String txt){
        description= txt;
    }

    public String getDescription(){
        return description;
    }
    public void attack(ArrayList players){
    }

    public void defend(int DPA, String type, int index, ArrayList players, Contestant attacker){
    }
    
    public void dead(){
        dead++;
    }
    
    public int getDead(){
        return dead;
    }
}
