package com.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication
{
    public static void main( String[] args )
    {
        try {
            SpringApplication.run(EurekaServerApplication.class, args);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println( "eureka started  World!" );
    }
}
