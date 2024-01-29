package com.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class EmployeeServiceClient
{

    public static void main( String[] args )
    {
        SpringApplication.run(EmployeeServiceClient.class,args);
        System.out.println( "Hello World!" );
    }
}
