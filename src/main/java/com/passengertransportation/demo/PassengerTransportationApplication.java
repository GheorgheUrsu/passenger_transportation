package com.passengertransportation.demo;

import com.passengertransportation.demo.config.AddConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PassengerTransportationApplication implements CommandLineRunner {

	@Autowired
	AddConfigBean addConfigBean;

	@Override
	public void run(String... args) throws Exception {
		System.out.printf(addConfigBean.getDb_url());
	}

	public static void main(String[] args) {
		SpringApplication.run(PassengerTransportationApplication.class, args);

	}

}
