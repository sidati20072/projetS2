package tn.isetso;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tn.isetso.dao.RoleRepository;
import tn.isetso.dao.TaskRepository;
import tn.isetso.dao.UserRepository;
import tn.isetso.entities.Role;
import tn.isetso.entities.Task;
import tn.isetso.entities.Users;
import tn.isetso.service.AccountService;

@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication /*implements CommandLineRunner*/{

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private AccountService accountService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

	
	@Bean
	CommandLineRunner start( UserRepository userrepository) {
		return args->{


			taskRepository.save(new Task(null,"T1"));
			taskRepository.save(new Task(null,"T2"));
			taskRepository.save(new Task(null,"T3"));
			taskRepository.save(new Task(null,"T4"));
			
			
			accountService.saveUser(new Users(null, "admin", "123", "mail@gmail.com", null));
			accountService.saveUser(new Users(null, "user", "123", "mail@gmail.com", null));

			accountService.saveRole(new Role(null,"USER"));
			accountService.saveRole(new Role(null,"ADMIN"));
			
			accountService.addRoleToUser("user", "USER");
			accountService.addRoleToUser("admin", "USER");
			accountService.addRoleToUser("admin", "ADMIN ");
			
			System.out.println("*********** DONE ************");

			 
		};
		
		
	}
	/*
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		taskRepository.save(new Task(null,"T1"));
		taskRepository.save(new Task(null,"T2"));
		taskRepository.save(new Task(null,"T3"));
		taskRepository.save(new Task(null,"T4"));
		
		
		accountService.saveUser(new Users(null, "admin", "123", "mail@gmail.com", null));
		accountService.saveUser(new Users(null, "user", "123", "mail@gmail.com", null));

		accountService.saveRole(new Role(null,"USER"));
		accountService.saveRole(new Role(null,"ADMIN"));
		
		accountService.addRoleToUser("user", "USER");
		accountService.addRoleToUser("admin", "USER");
		accountService.addRoleToUser("admin", "ADMIN ");
		
		System.out.println("*********** DONE ************");
	}
	*/
}

 