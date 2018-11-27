package com.stavroula.postexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.RiderRequest;
import com.stavroula.postexample.entity.RiderRequest.Status;
import com.stavroula.postexample.entity.User;
import com.stavroula.postexample.repository.RiderRequestRepository;
import com.stavroula.postexample.repository.UserRepository;
import com.stavroula.postexample.service.DbSeedService;

@SpringBootApplication
public class PostexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostexampleApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner setup(RiderRequestRepository riderRequestRepository, DbSeedService seedService,UserRepository userRepository) {
		return (args) -> {
			seedService.seedDb();
			
			
			riderRequestRepository.save(new RiderRequest("Attikis29", "Attikis50", Status.approved, null,null,null));
			riderRequestRepository.save(new RiderRequest("Porou3", "Ikaris21", Status.pending,null,null, null));
			riderRequestRepository.save(new RiderRequest("Dimosthenous5 ", "Voda2", Status.pending,null,null, null));
			riderRequestRepository.save(new RiderRequest("Alexandras78", "Alimou19", Status.approved,null,null,null));
			
			
			
			 // Create a User instance
	        User user = new User("john" , "1234" , "john@yahoo.com");


	        // Create a UserProfile instance
	        Driver driver = new Driver(null, null, null, null);


	        // Set child reference(userProfile) in parent entity(user)
	        user.setDriver(driver);

	        // Set parent reference(user) in child entity(userProfile)
	        driver.setUser(user);

	        // Save Parent Reference (which will save the child as well)
	        userRepository.save(user);
			
			
			
	
		};
}
}