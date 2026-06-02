package com.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PaymentService {

    public void pay(){
        System.out.println("Paying...");
    }

    @PostConstruct
    public void afterInit(){
        System.out.println("Before paying");
    }

    @PreDestroy
    public void beforeDestroy(){
        System.out.println("After payment is done");
    }
}
