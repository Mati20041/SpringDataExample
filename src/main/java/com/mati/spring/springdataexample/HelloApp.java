package com.mati.spring.springdataexample;

import com.mati.spring.springdataexample.domain.City;
import com.mati.spring.springdataexample.domain.Human;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        HelloJDBCDao dao = (HelloJDBCDao) context.getBean("MyDAO");
        dao.createTable();

        Human first = new Human();
        first.setName("Mati");

        dao.insertHuman(first);
        for (Human human : dao.getHumans()) {
            System.out.println("Human named: " + human.getName());
        }
        dao.drop();

        ///////////////////////////////////////////////////////

        HelloSpringJPADAO dao2 = (HelloSpringJPADAO) context.getBean("MyDAO2");
        System.out.println("Cities in db: " + dao2.getNoOfCities());
        System.out.println("Humans in db: " + dao2.getAllHumans().size());

        City city = new City("NY");
        dao2.addCity(city);
        city = dao2.getFirstCity();

        Human human = new Human("Mati", city);
        Human human2 = new Human("Greg", city);
        dao2.saveHuman(human);
        dao2.saveHuman(human2);

        for (Human h : dao2.getAllHumans()) {
            System.out.printf("Human named: %s from city: %s\n", h.getName(), h.getCity().getName());
        }

        City wroclaw = dao2.getFirstCity();

        System.out.println("Peoples that live in " + wroclaw.getName());

        for (Human h : wroclaw.getHumans()) {
            System.out.println("-> " + h.getName());
        }

        System.out.println("Cities in db: " + dao2.getNoOfCities());
        System.out.println("Humans in db: " + dao2.getAllHumans().size());
        dao2.removeAllHumans();
        dao2.removeAllCities();

        System.out.println("Cities in db: " + dao2.getNoOfCities());
        System.out.println("Humans in db: " + dao2.getAllHumans().size());
    }

}
