package com.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("id")
    int id;
    @JsonProperty("name")
    String name;
    @JsonProperty("city")
    String city;
    @JsonProperty("contact")
    String contact;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public User() {
    }

    public User(int id, String name, String city, String contact) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.contact = contact;
    }

    public User(String name, String city, String contact) {
        this.name = name;
        this.city = city;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
