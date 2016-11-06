package com.homework.app.core.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "city")
public class City {

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public City() {
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Transient
    private List<Customer> customers;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<Customer> getCustomers() {
        return customers;
    }


    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }


    @Override
    public String toString() {
        return "City [id=" + id + ", name=" + name + "]";
    }
}
