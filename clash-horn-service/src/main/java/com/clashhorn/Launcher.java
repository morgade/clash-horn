package com.clashhorn;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author x4rb
 */
@SpringBootApplication
public class Launcher extends WebMvcConfigurerAdapter {

    @Value("${server.static-content}")
    private String staticContentLocation;

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
            "/**.html", "/**.js", "/**.css", "/**.map", "/**.jpg", "/**.png", 
            "/**.svg", "/**.ttf", "/**.eot", "/**.woff", "/**.woff2"
        )
        .addResourceLocations(staticContentLocation).setCachePeriod(0);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }
    
    
    /**
     * Avoid @RestController caching problems on older IE
     * @return 
     */
    @Bean
    public Filter noChacheFilterForIE() {
        return new GenericFilterBean() {
            @Override
            public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
                ((HttpServletResponse)sr1).addHeader("Pragma", "no-cache");
                ((HttpServletResponse)sr1).addHeader("Cache-control", "no-cache");
                fc.doFilter(sr, sr1);
            }
        };
  }
    
   @Bean
   public RestTemplate restTemplate() {
       return new RestTemplate();
   }
}
