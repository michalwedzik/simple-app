package com.homework.app.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homework.app.core.dao.ICustomerDao;
import com.homework.app.core.model.Customer;

@Service("customerService")
@Transactional
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerDao customerDao;


    @Override
    public Customer save(Customer customer) {
        return customerDao.save(customer);
    }


    @Override
    public List<Customer> list() {
        return customerDao.list();
    }


    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }


    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);

    }


    @Override
    public List<Customer> getCustomersByCityId(Long cityId) {
        return customerDao.getCustomersByCityId(cityId);
    }
}
