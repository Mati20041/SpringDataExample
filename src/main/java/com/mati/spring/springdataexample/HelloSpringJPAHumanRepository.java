package com.mati.spring.springdataexample;

import com.mati.spring.springdataexample.domain.Human;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HelloSpringJPAHumanRepository extends JpaRepository<Human, Long> {
}
