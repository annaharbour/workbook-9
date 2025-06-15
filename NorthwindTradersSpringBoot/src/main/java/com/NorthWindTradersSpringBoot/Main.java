package com.NorthWindTradersSpringBoot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
//make sure the username and password were provided for the db
//        if (args.length != 2) {
//            System.out.println("Usage: java -jar app.jar <username> <password>");
//            System.exit(1);
//        }

//         Set system properties with the username and password so Spring can read them later.
//        System.setProperty("dbUsername", args[0]);
//        System.setProperty("dbPassword", args[1]);

        SpringApplication.run(Main.class, args);
    }

}
