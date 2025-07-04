package com.proyecto.Edutech_v1.proyecto.Edutech_v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.proyecto.Edutech_v1.proyecto.Edutech_v1")
// La anotación @SpringBootApplication es una combinación de @Configuration, @EnableAutoConfiguration y @ComponentScan
public class ProyectoEdutechV1Application {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoEdutechV1Application.class, args);
    }

}
