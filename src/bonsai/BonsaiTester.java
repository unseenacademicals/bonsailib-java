/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonsai;

import java.util.HashSet;

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
            
            // getting data
            User[] users = client.getAllUsers();
            System.out.println(users[0].getAcclaim_id());
            User user = client.getUser(1);
            System.out.println(user.getAcclaim_id());
            Employer emp = client.getEmployer(1);
            User[] usersNearby = client.getNearbyUsers(emp.getLocationObject());
            System.out.println(usersNearby[0].getAcclaim_id());
            
            // creating records
            User newUser = new User();
            newUser.setAcclaim_id("dummy-id");
            newUser.setLatitude(40.0000);
            newUser.setLongitude(-90.0000);
            client.createUser(newUser);
            
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
