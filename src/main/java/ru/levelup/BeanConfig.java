package ru.levelup;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

@Configuration
@ComponentScan(basePackages = {"ru.levelup"})
public class BeanConfig {

    @Bean
    public BufferedReader console() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public java.net.URI wsUri() {
        try {
            return new java.net.URI("ws://localhost:8080/");
        } catch (URISyntaxException ignored) {
        }
        return null;
    }
}