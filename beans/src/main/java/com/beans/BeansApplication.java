package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeansApplication implements CommandLineRunner {
	@Autowired
	PaymentService paymentServiceObj;
	@Autowired
	PaymentService paymentServiceObj2;
	public static void main(String[] args)  {

		SpringApplication.run(BeansApplication.class, args);
//		PaymentService paymentServiceObj = new PaymentService(); //give this responsibilty to spring
//		paymentServiceObj.pay();
	}

	@Override
	public void run(String... args) throws Exception {
		paymentServiceObj.pay();  //to use something in static need to make it static or use
		System.out.println(paymentServiceObj.hashCode());
		System.out.println(paymentServiceObj2.hashCode());

	}
}
