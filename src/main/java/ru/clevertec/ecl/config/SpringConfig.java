package ru.clevertec.ecl.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Objects;
import java.util.Properties;

@EnableWebMvc
@Configuration
@ComponentScan("ru.clevertec.ecl")
@PropertySource("classpath:application.yml")
public class SpringConfig implements WebMvcConfigurer {

    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        PropertySourcesPlaceholderConfigurer configure = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        Properties yamlObject = Objects.requireNonNull(yaml.getObject(), "Yaml not found.");
        configure.setProperties(yamlObject);

        return configure;
    }
}