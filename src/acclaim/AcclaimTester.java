/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acclaim;

/**
 *
 * @author mitchell
 */
public class AcclaimTester {
    private static final String ORG_AUTH_TOKEN = "59kyows7O22yDDvz_g40";
    private static final String ORG_ID = "41dd84f5-e826-4e17-a819-14b6af8bdb1f";
    
    public static void main(String[] args) {
        try {
            // replace this with the user's username and password
            Acclaim client = new Acclaim(ORG_AUTH_TOKEN,"");
            System.out.println(client.doHTTPGetRequest("/organizations/"+ORG_ID+"/badge_templates"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
