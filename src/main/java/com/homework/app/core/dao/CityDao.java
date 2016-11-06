package com.homework.app.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.homework.app.core.model.City;

@Repository("cityDao")
public class CityDao implements ICityDao {

    private static Logger logger = Logger.getLogger(CityDao.class.getName());

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public City save(City city) {
        Long id = (Long) sessionFactory.getCurrentSession().save(city);
        city.setId(id);

        return city;
    }


    @Override
    public City get(Long id) {
        return (City) sessionFactory.getCurrentSession().get(City.class, id);
    }


    @Override
    public List<City> list() {
        @SuppressWarnings("unchecked")
        List<City> list = sessionFactory.getCurrentSession().createQuery("from City").list();
        if (list == null) {
            if (logger.isInfoEnabled()) {
                logger.info("not found any City in db");
            }
            list = new ArrayList<City>();
        }
        return list;
    }
}
