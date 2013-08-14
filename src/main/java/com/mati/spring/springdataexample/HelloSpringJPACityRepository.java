package com.mati.spring.springdataexample;

import com.mati.spring.springdataexample.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloSpringJPACityRepository extends JpaRepository<City, Long> {
}
