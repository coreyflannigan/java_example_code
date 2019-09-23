/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment6monsters;

/**
 *
 * This is the Monster super class for the Assignment6 Monsters program. this is
 * the top parent from which all the other game classes are extended to. keeps
 * track of all the required variables for the child objects, as well as
 * dynamic validation post object construction and throughout play.
 * 
 * I, Sean Flannigan, student number 000270501, certify that all code submitted
 * is my own work; that I have not copied it from any other source. I also 
 * certify that I have not allowed my work to be copied by others.
 * 
 * @author Sean Flannigan
 */
public class Monster {
    /**
     * all necessary variables required by the child objects for play, as well
     * as a set of check flags that track the living or dead status of an object
     * and lock out the rest of the program from arbitrarily setting values to
     * the variables after construction and bypassing the controlled incrementing/
     * decrementing methods.
     */
    private String clan, monsterType;
    private int ferocity, defense, magic;
    private int treasure;
    private double health;
    private boolean lifeFlag = true, clanSet = false,
            treasureSet = false, feroFlag = false,
            defoFlag = false, magicFlag = false,
            healthFlag = false, typeFlag = false;
    
    // empty constructor for extension
    public Monster(){
        
    }
    
    /**
     * String clan variable getter
     * @return the clan String variable representing 1 of 3 clans
     */
    public String getClan() {
        return clan;
    }

    /**
     * String clan variable setter. locks everyone out after object construction
     * unless the object is a manticore.
     * @param clan a String passed from the FoonLogic class
     */
    public void setClan(String clan) {
        if (clanSet == false || monsterType.equalsIgnoreCase("Manticore")){
            this.clan = clan;
        }
        clanSet = true;
    }

    /**
     * String monster type getter
     * @return the monsterType a String representing what kind of monster the object is
     */
    public String getMonsterType() {
        return monsterType;
    }

    /**
     * String monster type setter, locks everyone out after object construction
     * @param monsterType String passed from the object on creation to denote what it is
     */
    public void setMonsterType(String monsterType) {
        if (typeFlag == false){
            this.monsterType = monsterType;
        }
        typeFlag = true;
    }

    /**
     * integer ferocity variable getter
     * @return the ferocity integer value representing the objects ferocity
     */
    public int getFerocity() {
        return ferocity;
    }
    
    /**
     * integer ferocity variable setter, locks everyone out after object construction
     * @param ferocity integer value representing the objects ferocity
     */
    public void setFerocity(int ferocity){
        if (feroFlag == false){
            this.ferocity = ferocity;
        }
        feroFlag = true;
    }

    /**
     * integer defense variable getter
     * @return the defense integer value representing the objects defense
     */
    public int getDefense() {
        return defense;
    }
    
    /**
     * integer defense setter, locks everyone out after object construction
     * @param defense integer value representing the objects denfense
     */
    public void setDefense(int defense){
        if (defoFlag == false){
            this.defense = defense;
        }
        defoFlag = true;
    }

    /**
     * integer magic getting
     * @return the magic integer value representing the objects magic
     */
    public int getMagic() {
        return magic;
    }
    
    /**
     * integer magic setter, locks everyone out after object construction
     * @param magic integer value representing the objects magic
     */
    public void setMagic(int magic){
        if (magicFlag == false){
            this.magic = magic;
        }
        magicFlag = true;
    }

    /**
     * integer treasure getter
     * @return the treasure integer value representing how much treasure an object has
     */
    public int getTreasure() {
        return treasure;
    }
    
    /**
     * integer treasure setter, locks everyone out after object construction
     * @param treasure integer value representing how much treasure an object has
     */
    public void setTreasure(int treasure){
        if (treasureSet == false){
            this.treasure = treasure;
        }
        treasureSet = true;
    }
    
    /**
     * method to raise ferocity value by 1, as long as the object is alive and
     * has less than 20 ferocity presently
     */
    public void raiseFerocity(){
        if (lifeFlag == true){
            if (ferocity < 20){
                ferocity += 1;
            }
        }
    }
    
    /**
     * method to lower ferocity value by 1, as long as the object is alive and
     * has more than 0 ferocity presently
     */
    public void lowerFerocity(){
        if (lifeFlag == true){
            if (ferocity > 0){
                ferocity -= 1;
            }
        }
    }
    
    /**
     * method to raise defense value by 1, as long as the object is alive and
     * has less than 20 defense presently
     */
    public void raiseDefense(){
        if (lifeFlag == true){
            if (defense < 20){
                defense += 1;
            }
        }
    }
    
    /**
     * method to lower defense value by 1, as long as the object is alive and
     * has more than 0 defense presently
     */
    public void lowerDefense(){
        if (lifeFlag == true){
            if (defense > 0){
                defense -= 1;
            }
        }
    }
    
    /**
     * method to raise magic value by 1, as long as the object is alive and
     * has less than 20 magic presently
     */
    public void raiseMagic(){
        if (lifeFlag == true){
            if (magic < 20){
                magic += 1;
            }
        }
    }
    
    /**
     * method to lower magic value by 1, as long as the object is alive and
     * has more than 0 magic presently
     */
    public void lowerMagic(){
        if (lifeFlag == true){
            if (magic > 0){
                magic -= 1;
            }
        }
    }
    
    /**
     * method to raise treasure amount by 1, following a victory in combat and
     * so long as the object is alive.
     */
    public void addTreasure(){
        if (lifeFlag == true){
            treasure += 1;
        }
    }
    
    /**
     * method to lower the treasure amount by 1, following a loss in combat,
     * currently doesn't matter if object is alive or dead
     */
    public void loseTreasure(){
        if (treasure > 0){
            treasure -= 1;
        }
    }
    
    /**
     * method to heal the object following a successful battle cry from an Orc
     * Warlord object
     * @param heal healing value passed from an Orc Warlord object
     */
    public void raiseHealth(int heal){
        if (lifeFlag == true){
            this.health += heal;
        }
    }
    
    /**
     * method to apply damage to an object following a loss in combat. uses
     * Math.floor in order to truncate the health value to 2 decimal places.
     * also checks and updates the life flag after damage is applied.
     * @param damage amount of damage inflicted on the object in combat
     */
    public void lowerHealth(double damage){
        this.health -= damage;
        health = Math.floor(health * 100) / 100;
        if (health <= 0) {
            lifeFlag = false;
        }
    }

    /**
     * integer health value setter, locks out everyone after object construction
     * @param health integer value passed from object's class constructor
     */
    public void setHealth(int health) {
        if (healthFlag == false){
            this.health = health;
        }
        healthFlag = true;
    }
    
    /**
     * method to return an object's attack score as a value of the average of all
     * 3 of the object's attributes. Math.floor used to truncate the value to 2
     * decimal places.
     * @return double value representing attack score or a 0 if dead
     */
    public double getAttackScore(){
        if (lifeFlag == true){
           return (Math.floor(((ferocity + defense + magic) / 3.0) * 100) / 100);
        } else {
            return 0;
        }
    }
    /**
     * method to return an objects defense score as a value of the average of all
     * 3 of the objects attributes. Math.floor used to truncate the value to 2
     * decimal places. required with the getAttackScore() method as Manticore
     * and OrcWarlord classes override the getAttackScore() method when attacking
     * but uses normal values when defending.
     * @return double value representing defense score or a 0 if dead
     */
    public double getDefenseScore(){
        if (lifeFlag == true) {
            return (Math.floor(((ferocity + defense + magic) / 3.0) * 100) / 100);
        } else {
            return 0;
        }
    }
    
    /**
     * boolean life flag getter
     * @return boolean lifeFlag to show if object is alive or dead
     */
    public boolean lifeCheck(){
        return lifeFlag;
    }
    
    /**
     * Overriden toString to display all the stored variables for the object
     * @return String created by concatenating all necessary variables
     */
    @Override
    public String toString(){
        return "Monster Type: " + monsterType + "\nClan Affiliation: " + clan 
                + "\nAlive: " + lifeFlag + "\nHealth: " + health 
                + "\nTreasure: " + treasure + "\nFerocity: " + ferocity 
                + "\nDefense: " + defense + "\nMagic: " + magic;
    }
    
}
