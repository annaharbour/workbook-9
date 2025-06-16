package com.pluralsight.NorthwindTradersApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class NorthwindTradersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NorthwindTradersApiApplication.class, args);
	}

}

@RestController
class HomeController {

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index() {
		return "Hello World!";
	}

}