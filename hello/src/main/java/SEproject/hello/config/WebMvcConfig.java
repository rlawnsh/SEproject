package SEproject.hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static SEproject.hello.common.model.BaseUrl.testBook_Base_URL;
import static SEproject.hello.common.model.BaseUrl.test_BASE_URL;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/mbtiTestImg/**")
                .addResourceLocations("file:///" + test_BASE_URL);
        registry.addResourceHandler("/testBook/**")
                .addResourceLocations("file:///" + testBook_Base_URL);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
}
