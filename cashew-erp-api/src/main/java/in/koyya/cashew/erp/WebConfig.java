package in.koyya.cashew.erp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for Cross-Origin Resource Sharing (CORS).
 */
@Configuration
public class WebConfig {

    /**
     * Configures CORS mappings to allow requests from specific origins.
     *
     * @return WebMvcConfigurer instance with CORS settings
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Allow CORS requests to all endpoints from http://localhost:3000
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") 
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}
