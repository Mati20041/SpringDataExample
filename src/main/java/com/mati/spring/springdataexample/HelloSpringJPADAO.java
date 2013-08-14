package com.mati.spring.springdataexample;


import com.mati.spring.springdataexample.domain.City;
import com.mati.spring.springdataexample.domain.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class HelloSpringJPADAO {

    @Autowired
    private HelloSpringJPAHumanRepository humanRepository;

    @Autowired
    private HelloSpringJPACityRepository cityRepository;

    public List<Human> getAllHumans(){
        List<Human> all = humanRepository.findAll();
        return all;
    }

    public void saveHuman(Human human){
        humanRepository.saveAndFlush(human);
    }

    public void removeAllHumans(){
        humanRepository.deleteAll();
    }

    public City getFirstCity(){
        City city = cityRepository.findAll().get(0);
        return city;
    }

    public int getNoOfCities(){
        return cityRepository.findAll().size();
    }

    public void addCity(City city){
        cityRepository.saveAndFlush(city);
    }

    public void removeAllCities(){
        cityRepository.deleteAll();
    }

    public void flush(){
        cityRepository.flush();
        humanRepository.flush();
    }
}
