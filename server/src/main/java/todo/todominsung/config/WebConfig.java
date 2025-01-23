package todo.todominsung.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 경로에 대해 CORS 설정
                .allowedOrigins("http://localhost:8080")    // 모든 origin 허용
                .allowedMethods("GET", "POST", "PATCH", "DELETE")  // 허용할 메소드
                .allowedHeaders("*");    // 허용할 헤더
    }
}
