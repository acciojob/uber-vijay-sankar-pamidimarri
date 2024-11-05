package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Admins") // Specify the table name in the database
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Primary key

    private String name; // Name of the admin

    private String password; // Changed to singular 'password' for clarity

    // Static list to keep track of all admin instances (not recommended for persistence)
    // It's usually better to manage entities via the database rather than a static list
    private static List<Admin> newAdmins = new ArrayList<>(); // Initialized to avoid NullPointerException

    // Constructor for JPA (default constructor is required)
    public Admin() {
    }

    // Parameterized constructor for creating Admin objects
    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }

    // Getters and Setters
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public static List<Admin> getNewAdmins() {
        return newAdmins;
    }

    public static void setNewAdmins(List<Admin> newAdmins) {
        Admin.newAdmins = newAdmins;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
