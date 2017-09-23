package com.clashhorn;

import com.clashhorn.infrastructure.jsonrpc.ClashHornErrorResolver;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import com.mongodb.MongoClientURI;
import java.io.IOException;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.ConversionServiceFactory;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author morgade
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
        LoggerFactory.getLogger(Launcher.class).info("Mapping static resources to '{0}'", staticContentLocation);
        registry.addResourceHandler("/**.html", "/**.js", "/**.css", "/**.map", "/**.svg",
                "/**.ttf", "/**.eot", "/**.woff", "/**.woff2", "/**.png")
                .addResourceLocations(staticContentLocation).setCachePeriod(0);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }


    @Bean
    public static AutoJsonRpcServiceImplExporter autoJsonRpcServiceImplExporter(@Autowired ClashHornErrorResolver errorResolver) {
        AutoJsonRpcServiceImplExporter exp = new AutoJsonRpcServiceImplExporter();
        exp.setErrorResolver(errorResolver);
        return exp;
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

    /**
     * RestTemplate bean
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
	return new SimpleMongoDbFactory(new MongoClientURI("mongodb://localhost:27017/test"));
    }
  
    @Bean
    public MongoTemplate mongoTemplate(@Autowired MongoDbFactory mongoDbFactory) throws Exception {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        
        MongoTemplate m = new MongoTemplate(mongoDbFactory, converter);
        //m.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        return m;
    }
    
    
    /**
     *
     * @param converters
     * @param genericConverters
     * @return
     */
    @Bean("dtoConversionService")
    public ConversionService conversionService(@Autowired Set<Converter> converters, @Autowired Set<GenericConverter> genericConverters) {
        GenericConversionService conversionService = new DefaultConversionService();
        ConversionServiceFactory.registerConverters(converters, conversionService);
        ConversionServiceFactory.registerConverters(genericConverters, conversionService);
        return conversionService;
    }
}
