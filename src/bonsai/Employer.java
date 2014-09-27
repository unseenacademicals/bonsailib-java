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
public class Employer {
    private String created_at;
    private String updated_at;
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    @JsonIgnore private Role[] roles;

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

    public Role[] getRoles() {
        return roles;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
