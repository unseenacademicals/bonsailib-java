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
    private final String BASE_URL = "http://localhost:5000/api";
    private final ObjectMapper mapper = new ObjectMapper();
    
    public User[] getAllUsers() throws Exception {
        String response = doHTTP("/users", "GET", null);
        return mapper.readValue(response, User[].class);
    }
    
    public User getUser(int user_id) throws Exception {
        String response = doHTTP("/users/" + user_id, "GET", null);
        return mapper.readValue(response, User.class);
    }
    
    public Employer[] getAllEmployers() throws Exception {
        String response = doHTTP("/employers", "GET", null);
        Employer[] employers = mapper.readValue(response, Employer[].class);
        for (Employer employer : employers) {
            employer.setRoles(getAllRoles(employer));
        }
        return employers;
    }
    
    public Employer getEmployer(int employer_id) throws Exception {
        String response = doHTTP("/employers/" + employer_id, "GET", null);
        Employer employer = mapper.readValue(response, Employer.class);
        employer.setRoles(getAllRoles(employer));
        return employer;
    }
    
    private Role[] getAllRoles(Employer employer) throws Exception {
        String response = doHTTP("/employers/" + employer.getId() + "/roles", "GET", null);
        return mapper.readValue(response, Role[].class);
    }
    
    public User[] getNearbyUsers(Location location) throws Exception {
        String response = doHTTP("/users/near/" + location.latitude + "/" + location.longitude, "GET", null);
        return mapper.readValue(response, User[].class);
    }
    
    public Employer[] getNearbyEmployers(Location location) throws Exception {
        String response = doHTTP("/employers/near/" + location.latitude + "/" + location.longitude, "GET", null);
        return mapper.readValue(response, Employer[].class);
    }
    
    public void createUser(User user) throws Exception {
        doHTTP("/users", "POST", serializeObject(user));
    }
    
    private String serializeObject(Object object) throws Exception {
        String jsonString = mapper.writeValueAsString(object);
        return jsonString;
    }
    
    private String doHTTP(String url, String method, String data)
            throws Exception {
        URL obj = new URL(BASE_URL + url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(method);
        
        if (data != null) {
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write("user=" + data);
            writer.flush();
            writer.close();
            os.close();
        }

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
