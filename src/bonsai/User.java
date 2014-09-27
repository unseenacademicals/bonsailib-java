/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonsai;

import com.fasterxml.jackson.annotation.*;

/**
 *
 * @author mitchell
 */
public class User {
    private String created_at;
    private String updated_at;
    private String id;
    private String acclaim_id;
    public String name;
    private double latitude;
    private double longitude;

    @JsonIgnore public Location getLocationObject () {
        return new Location(latitude, longitude);
    }
    
    @JsonIgnore public String getCreated_at() {
        return created_at;
    }

    @JsonProperty public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @JsonIgnore public String getUpdated_at() {
        return updated_at;
    }

    @JsonProperty public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @JsonIgnore public String getId() {
        return id;
    }

    @JsonProperty public void setId(String id) {
        this.id = id;
    }

    public String getAcclaim_id() {
        return acclaim_id;
    }

    public void setAcclaim_id(String acclaim_id) {
        this.acclaim_id = acclaim_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    
}
