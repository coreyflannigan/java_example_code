/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment6monsters;

/**
 *
 * This is the Orc Infantry class object for Assignment6 Monsters program. Extends
 * the monster class, and additionally holds an OrcWarlord object variable to
 * denote which Orc is this object's leader.
 * 
 * I, Sean Flannigan, student number 000270501, certify that all code submitted
 * is my own work; that I have not copied it from any other source. I also 
 * certify that I have not allowed my work to be copied by others.
 * 
 * @author Sean Flannigan
 */
public class OrcInfantry extends Monster {
    // this object's leader is stored in this OrcWarlord object variable
    private OrcWarlord leader;
    
    // empty constructor for the extended class OrcWarlord
    public OrcInfantry(){
        
    }
    
    /**
     * basic constructor with random values assigned to most of the object's
     * attributes
     * @param clan String passed from FoonLogic class to set a clan 
     */
    public OrcInfantry(String clan){
        super.setClan(clan);
        super.setFerocity((int) (Math.random() * 6 + 1));
        super.setDefense((int) (Math.random() * 6 + 1));
        super.setMagic((int) (Math.random() * 6 + 1));
        super.setHealth((int) (Math.random() * 18 + 1));
        super.setTreasure((int) (Math.random() * 4));
        super.setMonsterType("Orc Infantry");
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
    public OrcInfantry(String clan, int ferocity, int defense, 
            int magic, int health, int treature) {
        super.setClan(clan);
        super.setFerocity(ferocity);
        super.setDefense(defense);
        super.setMagic(magic);
        super.setHealth(health);
        super.setTreasure(treature);
        super.setMonsterType("Orc Infantry");
    }
    
    /**
     * leader variable setter, takes in an OrcWarlord object from FoonLogic
     * @param leader this object's Orc Warlord leader
     */
    public void setLeader(OrcWarlord leader){
        this.leader = leader;
    }
    
    /**
     * getter for the leader variable
     * @return leader this object's Orc Warlord leader
     */
    public OrcWarlord getLeader(){
        return leader;
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
