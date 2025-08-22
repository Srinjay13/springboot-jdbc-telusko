package com.telusko.SpringJDBCDemo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.telusko.SpringJDBCDemo.model.Alien;
import com.telusko.SpringJDBCDemo.repo.AlienRepo;

@SpringBootApplication
public class SpringJdbcDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringJdbcDemoApplication.class, args);
		Alien a1=context.getBean(Alien.class);
		a1.setId(7);
		a1.setName("Nilanjan Saha");
		a1.setTech("Home work");
		
		AlienRepo repo = context.getBean(AlienRepo.class);
		repo.save(a1);
		
		List<Alien> aliens = repo.findAll();
		
		System.out.println(aliens);
	}

}
