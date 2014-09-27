/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonsai;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author mitchell
 */
public class BonsaiTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Bonsai client = new Bonsai();

        
        // MASSIVE, UNORGANIZED TESTS FOR THE BONSAI CLASS
        // HERE BE DRAGONS
        
        try {
            
            User kyle = client.getUserByAcclaimID("675c8db1-24ba-4055-9b50-e7f2c9d4f062");
            kyle.name = "Kyle Oswald";
            client.updateUser(kyle);
            System.out.println("Fetched kyle");
        
            
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
