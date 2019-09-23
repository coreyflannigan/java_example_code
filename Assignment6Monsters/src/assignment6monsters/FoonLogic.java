/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment6monsters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * This is the logic class of the Assignment6 Monsters program. Waits for calls
 * from the interface to action 'gameplay' through a few methods. Also where
 * the monster objects are created and stored for later usage.
 * 
 * I, Sean Flannigan, student number 000270501, certify that all code submitted
 * is my own work; that I have not copied it from any other source. I also 
 * certify that I have not allowed my work to be copied by others.
 * 
 * @author Sean Flannigan
 */
public class FoonLogic {
    /**
     * Variety of arrays and arrayLists initialized here for the organization
     * of all the monster class objects. These variables allow the program to scale
     * to as many live objects as needed throughout 'gameplay' and still have
     * all the methods function correctly.
     */
    private ArrayList<Monster> monsterArrayList = new ArrayList<>();
    private ArrayList<OrcInfantry> infantryArrayList = new ArrayList<>();
    private ArrayList<OrcWarlord> warlordArrayList = new ArrayList<>();
    private ArrayList<Goblin> goblinArrayList = new ArrayList<>();
    private String[] clanList = {"Clan Knife", "Clan Spear", "Clan Shield"};
    private Monster[] monsterArray;
    private OrcInfantry[] infantryArray;
    private OrcWarlord[] warlordArray;
    private Goblin[] goblinArray;
    
    // empty constructor for interface connection
    public FoonLogic(){
        
    }
    
    /**
     * this method creates goblins in pairs to ensure there's always someone
     * to assign to each as a nemesis. arrayLists are used to dynamically scale
     * the size of the Goblin object array, and the Goblin array is used to both
     * store all the Goblin objects and randomly assign a nemesis to each
     */
    public void makeSomeGoblins(){
        Goblin g1 = new Goblin(clanList[(int) (Math.random() * 3)]);
        Goblin g2 = new Goblin(clanList[(int) (Math.random() * 3)]);
        goblinArrayList.add(g1);
        goblinArrayList.add(g2);
        monsterArrayList.addAll(goblinArrayList);
        goblinArray = new Goblin[goblinArrayList.size()];
        monsterArray = new Monster[monsterArrayList.size()];
        goblinArrayList.toArray(goblinArray);
        monsterArrayList.toArray(monsterArray);
        while (g1.isNemesisSet() == false){
            int tempIndex = (int) (Math.random() * goblinArray.length);
            if (!(goblinArray[tempIndex].equals(g1))){
                g1.setNemesis(goblinArray[tempIndex]);
            }
        }
        while (g2.isNemesisSet() == false){
            int tempIndex = (int) (Math.random() * goblinArray.length);
            if (!(goblinArray[tempIndex].equals(g2))){
                g2.setNemesis(goblinArray[tempIndex]);
            }
        }
    }
    
    /**
     * method to create a Manticore object. manticores are created solo as they
     * don't rely on any pairings with other class objects. for that reason as
     * well, the manticores are simply kept within the general monster array.
     */
    public void makeAManticore(){
        Manticore m1 = new Manticore(clanList[(int) (Math.random() * 3)]);
        monsterArrayList.add(m1);
        monsterArray = new Monster[monsterArrayList.size()];
        monsterArrayList.toArray(monsterArray);
    }
    
    /**
     * method that creates a pile of orcs on command. creates 5 infantry and 1
     * warlord every time the method is called, which ensures the proper numbers
     * for the Orc specific reliance on other Orc objects. all Orcs created at
     * this point are grouped up under the one Warlord object. Orcs are also
     * stored in their class specific arrays as well as the general monster
     * array, using arrayLists to dynamically grow the size of the arrays.
     */
    public void makeOrcs(){
        OrcInfantry if1 = new OrcInfantry(clanList[(int) (Math.random() * 3)]);
        OrcInfantry if2 = new OrcInfantry(clanList[(int) (Math.random() * 3)]);
        OrcInfantry if3 = new OrcInfantry(clanList[(int) (Math.random() * 3)]);
        OrcInfantry if4 = new OrcInfantry(clanList[(int) (Math.random() * 3)]);
        OrcInfantry if5 = new OrcInfantry(clanList[(int) (Math.random() * 3)]);
        OrcWarlord ow1 = new OrcWarlord(clanList[(int) (Math.random() * 3)]);
        infantryArrayList.add(if1);
        infantryArrayList.add(if2);
        infantryArrayList.add(if3);
        infantryArrayList.add(if4);
        infantryArrayList.add(if5);
        warlordArrayList.add(ow1);
        monsterArrayList.addAll(infantryArrayList);
        monsterArrayList.addAll(warlordArrayList);
        OrcInfantry[] tempArray = {if1, if2, if3, if4, if5};
        ow1.setSquad(tempArray);
        if1.setLeader(ow1);
        if2.setLeader(ow1);
        if3.setLeader(ow1);
        if4.setLeader(ow1);
        if5.setLeader(ow1);
        monsterArray = new Monster[monsterArrayList.size()];
        infantryArray = new OrcInfantry[infantryArrayList.size()];
        warlordArray = new OrcWarlord[warlordArrayList.size()];
        monsterArrayList.toArray(monsterArray);
        infantryArrayList.toArray(infantryArray);
        warlordArrayList.toArray(warlordArray);
    }
    
    /**
     * method to satisfy the requirement for Orc infantry to change leaders
     * whenever needed. orc infantry specific array is randomly shuffled, then
     * plugged back into a temporary array in groups of 5. once the temporary
     * group hits five it is pushed into the warlord's squad setting method then
     * reset for the next group of 5.
     */
    public void orcShuffle(){
        if (infantryArray.length > 0){
            Collections.shuffle(Arrays.asList(infantryArray));
            int warlordCount = 0;
            int infantryCount = 1;
            int tempSquadCount = 0;
            OrcInfantry[] tempSquad = new OrcInfantry[5];
            while (warlordCount < warlordArray.length){
                infantryArray[infantryCount-1].setLeader(warlordArray[warlordCount]);
                tempSquad[tempSquadCount] = infantryArray[infantryCount-1];
                if (infantryCount % 5 == 0){
                    tempSquadCount = -1;
                    warlordArray[warlordCount].setSquad(tempSquad);
                    warlordCount += 1;
                    tempSquad = new OrcInfantry[5];
                }
                infantryCount += 1;
                tempSquadCount += 1;
                
            }
        }
    }
    
    /**
     * this method controls the logic of one monster attacking another. attack
     * scores are measured against defense scores, then the difference is taken
     * as damage by the loser. winner gains a treasure piece, loser loses one.
     * 
     * @param attacker currently a randomly selected monster
     * @param defender currently a randomly selected monster
     */
    public void battle(Monster attacker, Monster defender){
        if (attacker.getAttackScore() >= defender.getDefenseScore()){
            defender.lowerHealth(attacker.getAttackScore() 
                    - defender.getDefenseScore());
            attacker.addTreasure();
            defender.loseTreasure();
            System.out.println("Attacker Won.");
        } else {
            attacker.lowerHealth(defender.getDefenseScore() 
                    - attacker.getAttackScore());
            System.out.println("Defender Won.");
            attacker.loseTreasure();
            defender.addTreasure();
        }
    }
    
    /**
     * These 4 methods were used to test and make sure all the arrays were
     * working properly and being passed along into the other classes correctly.
     * Ive left them here in case you want to uncomment them and run them to
     * check functionality of those specific features.
     */
    
//    public void test(){
//        for (Monster m: monsterArray){
//            System.out.println(m);
//            System.out.println("\n");
//        }
//    }
//    
//    public void checkNemesis(){
//        for (Goblin m: goblinArray){
//            System.out.println(m.getNemesis());
//            System.out.println("\n");
//        }
//    }
//    
//    public void checkLeader(){
//        for (OrcInfantry m: infantryArray){
//            System.out.println(m.getLeader());
//            System.out.println("\n");
//        }
//    }
//    
//    public void checkSquad(){
//        for (OrcWarlord m: warlordArray){
//            for (OrcInfantry i: m.getSquad()){
//                System.out.println(i.toString());
//            }
//        }
//    }
    
    
    /**
     * this method allows a call from the interface to set up a battle between
     * two random unique monsters. moreso a proof of functionality rather than
     * a necessary method to the program, included only due to the lack of any
     * real interface to prove the classes function and work together.
     */
    public void getCombatants(){
        Monster attacker = monsterArray[(int) (Math.random() * monsterArray.length)];
        Monster defender = monsterArray[(int) (Math.random() * monsterArray.length)];
        
        while (attacker.equals(defender)){
            defender = monsterArray[(int) (Math.random() * monsterArray.length)];
        }
        
        System.out.println("Attacker: \n" + attacker);
        System.out.println("\n");
        System.out.println("Defender: \n" + defender);
        
        System.out.println("\n-----\n");
        
        battle(attacker, defender);
        
        System.out.println("\n-----\n");
        
        System.out.println("Attacker post battle: \n" + attacker);
        System.out.println("\n");
        System.out.println("Defender post battle: \n" + defender);
    }
    
}
