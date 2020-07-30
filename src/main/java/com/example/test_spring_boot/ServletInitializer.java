package com.example.test_spring_boot;

import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Class what is used to bind beans (Servlet,Filter and ServletContextInitializer) and to set the application configuration.
 */
public class ServletInitializer extends SpringBootServletInitializer
{
    /**
     * This method sets application configuration.
     *
     * @param application object of class what is used to register application class as the configuration class.
     * @return application configuration.
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(TestSpringBootApplication.class);
    }
}