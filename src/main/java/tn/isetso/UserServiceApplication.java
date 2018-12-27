package tn.isetso;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import tn.isetso.dao.UserRepository;
import tn.isetso.entities.User;

@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start( UserRepository userrepository) {
		return args->{
			
			 Stream.of("A","B","C").forEach(cn-> {
				 userrepository.save(new User(null,cn,"password","email@gmail.com",null));
			 });
			 
			 userrepository.findAll().forEach(System.out::println);
			 
		};
		
		
	}
}

 