package org.mjulikelion.likelion12th3weekhomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class Likelion12th3weekHomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(Likelion12th3weekHomeworkApplication.class, args);
    }
}
