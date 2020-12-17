package com.task;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This is the class, from where the application is started
 *
 * @author Harut
 * @since 12.17.2020
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.task.service"})
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }

    /**
     * this method is used for mapping the models, mainly in mapper classes, for getting one model from another
     *
     * @return the ModelMapper object
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
