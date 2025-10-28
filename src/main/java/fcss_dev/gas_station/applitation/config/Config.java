package fcss_dev.gas_station.applitation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config {
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // todas as rotas
                        .allowedOrigins("http://localhost:3000") // frontend React
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // métodos permitidos
                        .allowedHeaders("*"); // cabeçalhos permitidos
            }
        };
    }
}
