package com.driver.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Customer")
public class Customer{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String mail;
  @OneToMany(mappedBy = "customer")
  private List<TripBooking> tripBooking;
  Customer(){

  }
  Customer(String name,String mail){
      this.name=name;
      this.mail=mail;
  }
    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }
}