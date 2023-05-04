package com.company;

import com.company.entities.Admin;
import com.company.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EhealthcareApplication {
        


    public static void main(String[] args) {
		SpringApplication.run(EhealthcareApplication.class, args);
	}
    @Autowired
    public AdminService adminService;

    @Bean
	public CommandLineRunner run() {
		CommandLineRunner clr = new CommandLineRunner(){

			@Override
			public void run(String... args) throws Exception {
				Admin admin= new Admin(1, "tamerlan_aghayev", "salam", "ADMIN", "Tamerlan", "Aghayev");
				adminService.insertAdmin(admin);
			}
		};

		return clr;
	}
}
