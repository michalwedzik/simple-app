package com.homework.app.core.service;

import java.util.List;

import com.homework.app.core.model.City;

public interface ICityService {

    public City save(City city);


    public List<City> list();


    public City get(Long id);


    public List<City> listCitiesWithCustomers();

}
