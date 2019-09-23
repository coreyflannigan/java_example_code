/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment6monsters;

/**
 *
 * This is the Orc Warlord class object for Assignment6 Monsters program. Extends
 * the Orc Infantry class, and additionally holds an array of 5 OrcInfantry 
 * objects, which this object will be considered the leader of. On top of that,
 * the Orc Warlord class has a leadership value between 1-5 and a leadership
 * bonus derived from how much treasure it has.
 * 
 * I, Sean Flannigan, student number 000270501, certify that all code submitted
 * is my own work; that I have not copied it from any other source. I also 
 * certify that I have not allowed my work to be copied by others.
 * 
 * @author Sean Flannigan
 */
public class OrcWarlord extends OrcInfantry {
    // OrcInfantry array holding the objects that this object is the leader of
    private OrcInfantry[] squad = new OrcInfantry[5];
    // leadership and bonus variables to be used for the battle cry method
    private int leadership, leadershipBonus = 0;
    
    /**
     * basic constructor with random values assigned to most of the object's
     * attributes
     * @param clan String passed from FoonLogic class to set a clan 
     */
    public OrcWarlord(String clan){
        super.setClan(clan);
        super.setFerocity((int) (Math.random() * 6 + 1));
        super.setDefense((int) (Math.random() * 6 + 1));
        super.setMagic((int) (Math.random() * 6 + 1));
        super.setHealth((int) (Math.random() * 18 + 1));
        super.setTreasure((int) (Math.random() * 4));
        super.setMonsterType("Orc Warlord");
        this.leadership = ((int) (Math.random() * 5 + 1));
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
     * @param leadership int value for the leadership attribute
     */
    public OrcWarlord(String clan, int ferocity, int defense, 
            int magic, int health, int treature, int leadership) {
        super.setClan(clan);
        super.setFerocity(ferocity);
        super.setDefense(defense);
        super.setMagic(magic);
        super.setHealth(health);
        super.setTreasure(treature);
        super.setMonsterType("Orc Warlord");
        this.leadership = leadership;
    }
    
    /**
     * method to raise leadership by 1, so long as this object is alive and has
     * less than 5 leadership at present.
     */
    public void raiseLeadership(){
        if (super.lifeCheck() == true){
            if (leadership < 5){
                leadership += 1;
            }
        }
    }
    
    /**
     * method to lower leadership by 1, so long as this object is alive and has
     * more than 1 leadership at present.
     */
    public void lowerLeadership(){
        if (super.lifeCheck() == true){
            if (leadership > 1){
                leadership -= 1;
            }
        }
    }
    
    /**
     * method that checks how much treasure this object has and applies it to
     * a leadership bonus here. 10 treasure for +1 leadership bonus.
     */
    public void leaderBonusUpdate(){
        leadershipBonus = (super.getTreasure() / 10);
    }
    
    /**
     * battle cry method that can be used so long as this object is alive. heals
     * all the Orc Infantry that this object is the leader of for a value derived
     * from this object's leadership and leadership bonus attributes.
     */
    public void battleCry(){
        leaderBonusUpdate();
        if (super.lifeCheck() == true){
            int healthBoost = leadership + leadershipBonus;
            for (OrcInfantry o: squad){
                o.raiseHealth(healthBoost);
            }
        }
    }
    
    /**
     * setter to take in a squad of 5 Orc Infantry objects for this object
     * to be the leader of
     * @param squad array of 5 Orc Infantry objects
     */
    public void setSquad(OrcInfantry[] squad){
        this.squad = squad;
    }
    
    /**
     * getter for the OrcInfantry array squad, to return the objects that this
     * object is in charge of
     * @return squad variable array of 5 Orc Infantry objects.
     */
    public OrcInfantry[] getSquad(){
        return squad;
    }
    
    /**
     * Overriden getAttackScore method, used when an Orc Warlord object is attacking
     * rather than the getAttackScore method within the Monster class. allows
     * the Orc Warlord to attack with 1.5x the average of their attributes.
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
     * Overriden toString that passes the call back to this class's super to
     * output all of this object's attributes.
     * @return String message of all of this object's attributes.
     */
    @Override
    public String toString(){
        leaderBonusUpdate();
        return super.toString() + "\nLeadership: " + leadership 
                + "\nLeadership Treasure Bonus: " + leadershipBonus;
    }
}
