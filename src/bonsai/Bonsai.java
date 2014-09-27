/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonsai;
import com.fasterxml.jackson.databind.*;
import java.io.*;
import java.net.*;
import java.util.HashMap;

/**
 *
 * @author mitchell
 */
public class Bonsai {
    private final String BASE_URL = "http://bonsaibadge.herokuapp.com/api";
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
    
    private Role getRole(Employer employer, int role_id) throws Exception {
        String response = doHTTP("/employers/" + employer.getId() + "/roles/" + role_id, "GET", null);
        return mapper.readValue(response, Role.class);
    }
    
    public User[] getNearbyUsers(Location location) throws Exception {
        String response = doHTTP("/users/near/" + location.latitude + "/" + location.longitude, "GET", null);
        return mapper.readValue(response, User[].class);
    }
    
    public User getUserByAcclaimID(String acclaim_id) throws Exception {
        String response = doHTTP("/users/by-acclaim/" + acclaim_id, "GET", null);
        return mapper.readValue(response, User.class);
    }
    
    public Employer[] getNearbyEmployers(Location location) throws Exception {
        String response = doHTTP("/employers/near/" + location.latitude + "/" + location.longitude, "GET", null);
        return mapper.readValue(response, Employer[].class);
    }
    
    public void createUser(User user) throws Exception {
        HashMap<String, User> object = new HashMap<String, User>();
        object.put("user", user);
        doHTTP("/users", "POST", serializeObject(object));
    }
    
    public void updateUser(User user) throws Exception {
        HashMap<String, User> object = new HashMap<String, User>();
        object.put("user", user);
        doHTTP("/users/"+user.getId(), "PUT", serializeObject(object));
    }
    
    public void createEmployer(Employer employer) throws Exception {
        HashMap<String, Employer> object = new HashMap<String, Employer>();
        object.put("employer", employer);
        doHTTP("/employers", "POST", serializeObject(object));
    }
    
    public void updateEmployer(Employer employer) throws Exception {
        HashMap<String, Employer> object = new HashMap<String, Employer>();
        object.put("employer", employer);
        doHTTP("/employers/"+employer.getId(), "PUT", serializeObject(object));
    }
    
    public void createRole(Role role) throws Exception {
        HashMap<String, Role> object = new HashMap<String, Role>();
        object.put("role", role);
        doHTTP("/employers/"+role.getEmployer_id()+"/roles", "POST", serializeObject(object));
    }
    
    public void updateRole(Role role) throws Exception {
        HashMap<String, Role> object = new HashMap<String, Role>();
        object.put("role", role);
        doHTTP("/employers/"+role.getEmployer_id()+"/roles/"+role.getId(), "PUT", serializeObject(object));
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
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Content-Length", String.valueOf(data.length()));
            OutputStream os = con.getOutputStream();
            os.write(data.getBytes());
            os.flush();
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
