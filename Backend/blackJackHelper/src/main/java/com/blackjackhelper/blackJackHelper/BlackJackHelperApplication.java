package com.blackjackhelper.blackJackHelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "com.blackjackhelper.blackJackHelper"})
public class BlackJackHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackJackHelperApplication.class, args);
	}

}
