package com.example.userservice;

import com.example.userservice.user.User;
import com.example.userservice.user.UserRepository;
import com.example.userservice.utils.PasswordUtils;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}


	//cIxdR82W0CnU061e
	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository){
		return args -> {
			int max = 10;
			Faker faker = new Faker(new Locale("en-US"));

			for(int i = 0; i < max; i++)
			{
				User user = new User();
				user.setName(faker.name().fullName());
				user.setEmail(faker.internet().emailAddress());
				user.setPassword(PasswordUtils.hashPassword("password"));
				userRepository.save(user);
			}
		};
	}
}
