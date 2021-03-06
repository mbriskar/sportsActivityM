/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportsactivitymanager.Entity;

import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Juraj Pleško, 359530
 */
@Entity
public class Activity {
  
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String name;

    @NotNull
    private Boolean MeasureDistance;
    
    @NotNull
    @OneToOne
    private Calories calories;
    
    public Activity () {
    }
    
    public Activity (Long Id){
     this.id = id;   
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getMeasureDistance() {
        return MeasureDistance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeasureDistance(Boolean MeasureDistanca) {
        this.MeasureDistance= MeasureDistanca;
    }

    @Override
    public String toString() {
        return "Activity{" + "id=" + id + ", name=" + name + ", MeasureDistanca=" + MeasureDistance + ", calories=" + calories + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Activity other = (Activity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Calories getCalories() {
        return calories;
    }

    public void setCalories(Calories calories) {
        this.calories = calories;
    }

    
    
}
