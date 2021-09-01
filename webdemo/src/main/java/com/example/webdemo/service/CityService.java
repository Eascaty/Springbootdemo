package com.example.webdemo.service;

import com.example.webdemo.bean.City;

public interface CityService {
    public City getById(Long id);
    public void saveCity(City city);
}
