package com.example.webdemo.service.impl;


import com.example.webdemo.bean.City;
import com.example.webdemo.mapper.CityMapper;
import com.example.webdemo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
        @Autowired
        CityMapper cityMapper;

        public City getById(Long id){
           return  cityMapper.getById(id);
        }

    public void saveCity(City city) {
            cityMapper.insert(city);
    }
}
