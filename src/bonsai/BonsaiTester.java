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
            System.out.println("Fetched kyle");
            
            Employer greepod = client.getEmployer(1);
            System.out.println("Got employer");
            
            greepod.setName("Greepod");
            client.updateEmployer(greepod);
            System.out.println("Updated employer");
            
            String[] badges = new String[1];
            badges[0] = ("dummy-badge-id");
            
            Role newRole = new Role();
            newRole.setEmployer_id(greepod.getId());
            newRole.setName("Dummy Role Name");
            newRole.setBadges(badges);
            client.createRole(newRole);
            
            System.out.println("Created new role");
            
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
