package com.anu.bank.capg;

import com.anu.bank.capg.domain.entity.Customer;
import com.anu.bank.capg.domain.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = "com.anu.bank.capg")
public class CapGApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapGApplication.class, args);
	}

	@Bean
	CommandLineRunner loadInitialCustomers(CustomerRepository customerRepository) {
		return args -> {
			Customer customer1 = new Customer(1L, "John", "Doe");
			Customer customer2 = new Customer(2L, "Jane", "Smith");
			Customer customer3 = new Customer(3L, "Robert", "Brown");

			Arrays.asList(customer1, customer2, customer3).forEach(customerRepository::save);
		};}

}
