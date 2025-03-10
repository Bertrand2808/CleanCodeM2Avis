package fr.esgi.avis.configuration;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
public class TeleversementConfiguration {

    @Bean(name="uploadConfig")
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(2));
        factory.setMaxRequestSize(DataSize.ofMegabytes(3));
        return factory.createMultipartConfig();
    }

}