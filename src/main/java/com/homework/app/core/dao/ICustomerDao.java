package com.homework.app.core.dao;

import java.util.List;

import com.homework.app.core.model.Customer;

public interface ICustomerDao {

    public List<Customer> list();


    public Customer save(Customer customer);


    public void update(Customer customer);


    public void delete(Customer customer);


    public List<Customer> getCustomersByCityId(Long cityId);
}
