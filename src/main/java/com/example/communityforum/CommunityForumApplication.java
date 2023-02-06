package com.example.communityforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class CommunityForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityForumApplication.class, args);
    }

}
