package com.driver.model;

import javax.persistence.*;

@Table(name = "Cab")
@Entity
public class Cab{
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private int ratesOfValue;
   @Enumerated(EnumType.STRING)
   private cabAvailability cabavailability;
   @OneToOne(mappedBy = "cab")
   Driver driver;
   Cab(){

   }

    public cabAvailability getCabavailability() {
        return cabavailability;
    }

    public int getRatesOfValue() {
        return ratesOfValue;
    }

    public void setCabavailability(cabAvailability cabavailability) {
        this.cabavailability = cabavailability;
    }

    public void setRatesOfValue(int ratesOfValue) {
        this.ratesOfValue = ratesOfValue;
    }

}