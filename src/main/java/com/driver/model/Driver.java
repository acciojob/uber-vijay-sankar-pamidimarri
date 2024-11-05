package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name = "Driver")
public class Driver{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  @OneToOne
  @JoinColumn(name = "Cab_details")
  private Cab cab;
  public Driver(){

  }

   Driver(String name){
      this.name=name;
   }

    public Driver(String mobile, String password) {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }
}