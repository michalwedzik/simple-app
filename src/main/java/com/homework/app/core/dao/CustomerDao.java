package com.homework.app.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.homework.app.core.model.Customer;

@Repository("customerDao")
public class CustomerDao implements ICustomerDao {

    private static Logger logger = Logger.getLogger(CustomerDao.class.getName());

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Customer save(Customer customer) {
        Long id = (Long) sessionFactory.getCurrentSession().save(customer);
        customer.setId(id);

        return customer;
    }


    @Override
    public List<Customer> list() {
        @SuppressWarnings("unchecked")
        List<Customer> list = sessionFactory.getCurrentSession().createQuery("from Customer").list();
        if (list == null) {
            if (logger.isInfoEnabled()) {
                logger.info("not found any Customer in db");
            }
            list = new ArrayList<Customer>();
        }
        return list;
    }


    @Override
    public void update(Customer customer) {
        sessionFactory.getCurrentSession().update(customer);

    }


    @Override
    public void delete(Customer customer) {
        sessionFactory.getCurrentSession().delete(customer);

    }


    @Override
    public List<Customer> getCustomersByCityId(Long cityId) {
        @SuppressWarnings("unchecked")
        List<Customer> list = sessionFactory.getCurrentSession().getNamedQuery("getCustomersByCityId").setLong("cityId", cityId).list();
        if (list == null) {
            if (logger.isInfoEnabled()) {
                logger.info("not found any Customer for cityId: " + cityId + " in db");
            }
            list = new ArrayList<Customer>();
        }
        return list;
    }
}
