package com.example.communityforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CommunityForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityForumApplication.class, args);
    }

}
