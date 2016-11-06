package com.homework.app.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@NamedNativeQueries({ @NamedNativeQuery(name = "getCustomersByCityId", query = "SELECT * FROM customer WHERE city_id= :cityId", resultClass = Customer.class) })
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;


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


    public City getCity() {
        return city;
    }


    public void setCity(City city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", city=" + city + "]";
    }
}
