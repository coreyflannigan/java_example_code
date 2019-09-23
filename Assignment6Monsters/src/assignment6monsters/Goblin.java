/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment6monsters;

/**
 *
 * This is the Goblin class of the Assignment6 Monster program. Extends the Monster
 * class and carries additional code to allow every created Goblin object to have
 * a nemesis Goblin object.
 * 
 * I, Sean Flannigan, student number 000270501, certify that all code submitted
 * is my own work; that I have not copied it from any other source. I also 
 * certify that I have not allowed my work to be copied by others.
 * 
 * @author Sean Flannigan
 */
public class Goblin extends Monster {
    // nemesis Goblin held within the class here, with a flag to prevent it changing
    private Goblin nemesis;
    private boolean nemesisSetFlag = false;
    
    /**
     * basic constructor with random values assigned to most of the object's
     * attributes
     * @param clan String passed from FoonLogic class to set a clan 
     */
    public Goblin(String clan){
        super.setClan(clan);
        super.setFerocity((int) (Math.random() * 6 + 1));
        super.setDefense((int) (Math.random() * 6 + 1));
        super.setMagic((int) (Math.random() * 6 + 1));
        super.setHealth((int) (Math.random() * 18 + 1));
        super.setTreasure((int) (Math.random() * 4));
        super.setMonsterType("Goblin");
    }
    
    /**
     * constructor to allow specific values to be passed in on creation rather
     * than have them randomly chosen.
     * @param clan String passed from FoonLogic class to set a clan
     * @param ferocity int value for the ferocity attribute
     * @param defense int value for the defense attribute
     * @param magic int value for the magic attribute
     * @param health int value for the health of the object
     * @param treature  int value for how much treasure the object has
     */
    public Goblin(String clan, int ferocity, int defense, 
            int magic, int health, int treature) {
        super.setClan(clan);
        super.setFerocity(ferocity);
        super.setDefense(defense);
        super.setMagic(magic);
        super.setHealth(health);
        super.setTreasure(treature);
        super.setMonsterType("Goblin");
    }
    
    /**
     * method to take in a different Goblin object's reference and set it as
     * this object's nemesis. locks out further setting by use of the check flag
     * @param nemesis Goblin object passed from FoonLogic class
     */
    public void setNemesis(Goblin nemesis){
        if (nemesisSetFlag == false){
            this.nemesis = nemesis;
        }
        nemesisSetFlag = true;
    }
    
    /**
     * boolean getter to check if a nemesis object has been set
     * @return boolean flag showing if a nemesis has been set or not
     */
    public boolean isNemesisSet(){
        return nemesisSetFlag;
    }
    
    /**
     * getter method to return the information of this object's Goblin nemesis
     * @return nemesis variable holding a Goblin object reference
     */
    public Goblin getNemesis(){
        return nemesis;
    }
    
    /**
     * Overriden toString that passes the call back to this class's super to
     * output all of this object's attributes.
     * @return String message of all of this object's attributes.
     */
    @Override
    public String toString(){
        return super.toString();
    }
}
