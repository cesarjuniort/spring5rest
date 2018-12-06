package com.cesarjuniort.springboot.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootBackendApirestApplication  implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendApirestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("=====================");
		System.out.println("cors.allowedorigins: " + (new com.cesarjuniort.springboot.apirest.auth.JwtConfig()).CORS_ALLOWED_ORIGINS);
		System.out.println("=====================");
		
		 
		
		String testPassword = "P@ssw0rd!";
		for(int i = 0; i < 4; i++) {
			String encPwd = pwdEncoder.encode(testPassword);
			System.out.println(encPwd);
		}
	}
}
