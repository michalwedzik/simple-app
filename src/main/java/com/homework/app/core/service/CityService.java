package com.homework.app.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homework.app.core.dao.ICityDao;
import com.homework.app.core.model.City;

@Service("cityService")
@Transactional
public class CityService implements ICityService {

    @Autowired
    private ICityDao cityDao;

    @Autowired
    private ICustomerService customerService;


    @Override
    public City save(City city) {
        return cityDao.save(city);
    }


    @Override
    public City get(Long id) {
        return cityDao.get(id);
    }


    @Override
    public List<City> list() {
        return cityDao.list();
    }


    @Override
    public List<City> listCitiesWithCustomers() {
        List<City> cities = list();

        cities.forEach(city -> city.setCustomers(customerService.getCustomersByCityId(city.getId())));

        return cities;
    }
}
