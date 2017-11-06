/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apitz_000
 */
public class Organization {

    private int orgId;
    @NotEmpty(message = "You must supply a value for Organization Name.")
    @Length(max = 45, message = "Organization Name must be no more than 45 characters in length.")
    private String orgName;
    @NotEmpty(message = "You must supply a value for Description.")
    @Length(max = 45, message = "Description must be no more than 45 characters in length.")
    private String description;
    @NotEmpty(message = "You must supply a value for Description.")
    @Length(max = 45, message = "Description must be no more than 45 characters in length.")
    private String contact;
    @NotEmpty(message = "You must supply a value for Base Location.")
    @Length(max = 45, message = "Description must be no more than 45 characters in length.")
    private String baseLocation;
    private List<Hero> heroes;

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBaseLocation() {
        return baseLocation;
    }

    public void setBaseLocation(String baseLocation) {
        this.baseLocation = baseLocation;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.orgId;
        hash = 79 * hash + Objects.hashCode(this.orgName);
        hash = 79 * hash + Objects.hashCode(this.description);
        hash = 79 * hash + Objects.hashCode(this.contact);
        hash = 79 * hash + Objects.hashCode(this.baseLocation);
        hash = 79 * hash + Objects.hashCode(this.heroes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organization other = (Organization) obj;
        if (this.orgId != other.orgId) {
            return false;
        }
        if (!Objects.equals(this.orgName, other.orgName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        if (!Objects.equals(this.baseLocation, other.baseLocation)) {
            return false;
        }
        if (!Objects.equals(this.heroes, other.heroes)) {
            return false;
        }
        return true;
    }

}
