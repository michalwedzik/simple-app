package com.homework.app.core.service;

import java.util.List;

import com.homework.app.core.model.Customer;

public interface ICustomerService {

    public Customer save(Customer customer);


    public List<Customer> list();


    public void update(Customer customer);


    public void delete(Customer customer);


    public List<Customer> getCustomersByCityId(Long id);

}
