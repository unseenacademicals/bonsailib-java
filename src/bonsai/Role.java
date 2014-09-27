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
public class Role {
    private String created_at;
    private String updated_at;
    private String id;
    private String name;
    private String[] badges;
    private String employer_id;

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

    @JsonIgnore public String getEmployer_id() {
        return employer_id;
    }

    @JsonProperty public void setEmployer_id(String employer_id) {
        this.employer_id = employer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getBadges() {
        return badges;
    }

    public void setBadges(String[] badges) {
        this.badges = badges;
    }
}
