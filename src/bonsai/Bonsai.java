/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonsai;
import com.fasterxml.jackson.databind.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author mitchell
 */
public class Bonsai {
    private final String BASE_URL = "http://bonsaibadge.herokuapp.com/api";
    private final ObjectMapper mapper = new ObjectMapper();
    
    public User[] getAllUsers() throws Exception {
        String response = doHTTP("/users", "GET");
        return mapper.readValue(response, User[].class);
    }
    
    public User getUser(int user_id) throws Exception {
        String response = doHTTP("/users/" + user_id, "GET");
        return mapper.readValue(response, User.class);
    }
    
    public Employer[] getAllEmployers() throws Exception {
        String response = doHTTP("/employers", "GET");
        Employer[] employers = mapper.readValue(response, Employer[].class);
        for (Employer employer : employers) {
            employer.setRoles(getAllRoles(employer));
        }
        return employers;
    }
    
    public Employer getEmployer(int employer_id) throws Exception {
        String response = doHTTP("/employers/" + employer_id, "GET");
        Employer employer = mapper.readValue(response, Employer.class);
        employer.setRoles(getAllRoles(employer));
        return employer;
    }
    
    private Role[] getAllRoles(Employer employer) throws Exception {
        String response = doHTTP("/employers/" + employer.getId() + "/roles", "GET");
        return mapper.readValue(response, Role[].class);
    }
    
    public User[] getNearbyUsers(Location location) throws Exception {
        String response = doHTTP("/users/near/" + location.latitude + "/" + location.longitude, "GET");
        return mapper.readValue(response, User[].class);
    }
    
    public Employer[] getNearbyEmployers(Location location) throws Exception {
        String response = doHTTP("/employers/near/" + location.latitude + "/" + location.longitude, "GET");
        return mapper.readValue(response, Employer[].class);
    }
    
    private String doHTTP(String url, String method)
        throws Exception {
        URL obj = new URL(BASE_URL + url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(method);

        int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
