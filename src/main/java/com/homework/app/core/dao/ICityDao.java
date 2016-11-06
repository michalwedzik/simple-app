package com.homework.app.core.dao;

import java.util.List;

import com.homework.app.core.model.City;

public interface ICityDao {

    public List<City> list();


    public City save(City city);


    public City get(Long id);

}
