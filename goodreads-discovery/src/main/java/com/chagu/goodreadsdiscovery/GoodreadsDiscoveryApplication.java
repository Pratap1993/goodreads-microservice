package com.chagu.goodreadsdiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GoodreadsDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodreadsDiscoveryApplication.class, args);
    }

}
