/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acclaim;

import com.fasterxml.jackson.databind.*;
import org.apache.commons.codec.binary.Base64;
import java.net.*;
import java.io.*;

/**
 *
 * @author mitchell
 */
public class Acclaim {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String authString;
    private final String BASE_URL = "https://sandbox.youracclaim.com/api/v1";
    
    public Acclaim(String user, String password) {
        String creds = user + ":" + password;
        String base64 = Base64.encodeBase64String(creds.getBytes());
        authString = "Basic " + base64;
    }
    
    public String doHTTGetRequest(String url) throws Exception {
        URL obj = new URL(BASE_URL + url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", authString);
        con.setRequestProperty("Content-Type", "application/json");
        
        int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
    
    public String doHTTPPostRequest(String url) throws Exception {
        String rawData = "id=10";
        String type = "application/x-www-form-urlencoded";
        String encodedData = URLEncoder.encode(rawData);
        URL u = new URL("http://www.example.com/page.php");
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", type);
        conn.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));
        OutputStream os = conn.getOutputStream();
        os.write(encodedData.getBytes());
        return null;
    }
}
