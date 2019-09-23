/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment6monsters;

/**
 *
 * This is the empty view portion of the Assignment6 Monsters program. Currently
 * holds a few commands to run the basics of the program as proof of
 * functionality.
 * 
 * I, Sean Flannigan, student number 000270501, certify that all code submitted
 * is my own work; that I have not copied it from any other source. I also 
 * certify that I have not allowed my work to be copied by others.
 * 
 * @author Sean Flannigan
 */
public class Assignment6Monsters {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // logic class object initialized
        FoonLogic f1 = new FoonLogic();
        
        // these three commands create the 4 different monsters
        f1.makeAManticore();
        f1.makeOrcs();
        f1.makeSomeGoblins();
        
        // this command runs the combat structure of the model as proof
        // of functionality
        f1.getCombatants();
        
    }
    
}
