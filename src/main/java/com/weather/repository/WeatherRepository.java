package com.weather.repository;

import org.springframework.stereotype.Repository;

import com.weather.model.Weather;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long>{
	
	@Query(value = "SELECT * FROM weather ORDER BY date_time DESC LIMIT :limit", nativeQuery = true)
	List<Weather> findTopNByOrderByDateTimeDesc(int limit);
}
