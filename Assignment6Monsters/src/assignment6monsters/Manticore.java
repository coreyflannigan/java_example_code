/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment6monsters;

/**
 *
 * This is the Manticore class object for the Assignment6 Monsters program.
 * Manticore extends the monster class and is only unique in that it can change
 * its clan affiliation (validated within the monster class) and overrides the
 * attack score method when attacking to get itself a higher value.
 * 
 * I, Sean Flannigan, student number 000270501, certify that all code submitted
 * is my own work; that I have not copied it from any other source. I also 
 * certify that I have not allowed my work to be copied by others.
 * 
 * @author Sean Flannigan
 */
public class Manticore extends Monster {
    
    /**
     * basic constructor with random values assigned to most of the object's
     * attributes
     * @param clan String passed from FoonLogic class to set a clan 
     */
    public Manticore(String clan){
        super.setClan(clan);
        super.setFerocity((int) (Math.random() * 6 + 1));
        super.setDefense((int) (Math.random() * 6 + 1));
        super.setMagic((int) (Math.random() * 6 + 1));
        super.setHealth((int) (Math.random() * 18 + 1));
        super.setTreasure((int) (Math.random() * 4));
        super.setMonsterType("Manticore");
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
    public Manticore(String clan, int ferocity, int defense, 
            int magic, int health, int treature) {
        super.setClan(clan);
        super.setFerocity(ferocity);
        super.setDefense(defense);
        super.setMagic(magic);
        super.setHealth(health);
        super.setTreasure(treature);
        super.setMonsterType("Manticore");
    }
    
    /**
     * Overriden getAttackScore method, used when a Manticore object is attacking
     * rather than the getAttackScore method within the Monster class. allows
     * the Manticore to attack with 1.5x the average of their attributes.
     * Math.floor used to truncate to 2 digits
     * @return double attack score value or 0 if dead.
     */
    @Override
    public double getAttackScore(){
        if (super.lifeCheck() == true){
            return (Math.floor((1.5 * ((super.getFerocity() + super.getDefense() 
                    + super.getMagic()) / 3.0)) * 100) / 100);
        } else {
            return 0;
        }
    }
    
    /**
     * Overriden toString that simply calls on the super class's toString method
     * @return a String message of all this object's attributes.
     */
    @Override
    public String toString(){
        return super.toString();
    }
}
