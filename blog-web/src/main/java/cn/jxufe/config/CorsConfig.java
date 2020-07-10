package cn.jxufe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hsw
 * @date 2020/4/8 22:06
 */
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowCredentials(true)
                    .allowedMethods("GET", "POST", "DELETE", "PUT")
                    .allowedHeaders("*")
                    .exposedHeaders("Accept", "Accept-Language", "Content-Language", "Content-Type")
                    .maxAge(3600);
            }
        };
    }
}
