package fr.esgi.avis.config;

import fr.esgi.avis.controller.Avis.AvisDtoMapper;
import fr.esgi.avis.controller.Jeu.JeuDtoMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration des beans
 */
@Configuration
public class BeanConfig {

    /**
     * Bean pour AvisDtoMapper
     * @return une instance de AvisDtoMapper
     */
    @Bean
    public AvisDtoMapper avisDtoMapper() {
        return new AvisDtoMapper();
    }

    /**
     * Bean pour JeuDtoMapper
     * @return une instance de JeuDtoMapper
     */
    @Bean
    public JeuDtoMapper jeuDtoMapper() {
        return new JeuDtoMapper();
    }
}
