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
public class Hero {
    
    
    private int heroId;
    @NotEmpty(message = "You must supply a value for Hero Name.")
    @Length(max = 25, message = "Hero Name must be no more than 25 characters in length.")
    private String heroName;
    @NotEmpty(message = "You must supply a value for Real Name.")
    @Length(max = 45, message = "Real Name must be no more than 45 characters in length.")
    private String realName;
    @NotEmpty(message = "You must supply a value for Description.")
    @Length(max = 45, message = "Description must be no more than 45 characters in length.")
    private String description;
    private List<SuperPower> superPowers;

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SuperPower> getSuperPowers() {
        return superPowers;
    }

    public void setSuperPowers(List<SuperPower> superPowers) {
        this.superPowers = superPowers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.heroId;
        hash = 59 * hash + Objects.hashCode(this.heroName);
        hash = 59 * hash + Objects.hashCode(this.realName);
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.superPowers);
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
        final Hero other = (Hero) obj;
        if (this.heroId != other.heroId) {
            return false;
        }
        if (!Objects.equals(this.heroName, other.heroName)) {
            return false;
        }
        if (!Objects.equals(this.realName, other.realName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.superPowers, other.superPowers)) {
            return false;
        }
        return true;
    }
    
    
    
}
