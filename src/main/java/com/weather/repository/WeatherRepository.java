package com.weather.repository;

import org.springframework.stereotype.Repository;

import com.weather.model.Weather;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long>{

}
