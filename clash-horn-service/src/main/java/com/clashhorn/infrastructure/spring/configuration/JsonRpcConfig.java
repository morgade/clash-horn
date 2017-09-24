/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.spring.configuration;

import static com.clashhorn.Launcher.PROFILE_SLOW_JSON_RPC;
import com.clashhorn.infrastructure.jsonrpc.ClashHornErrorResolver;
import com.fasterxml.jackson.databind.JsonNode;
import com.googlecode.jsonrpc4j.InvocationListener;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import java.lang.reflect.Method;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 *
 * @author morgade
 */
@Configuration
public class JsonRpcConfig {
    
    @Bean
    public static AutoJsonRpcServiceImplExporter autoJsonRpcServiceImplExporter(@Autowired ClashHornErrorResolver errorResolver, 
                                                                        @Autowired Environment environment) throws Exception {
        AutoJsonRpcServiceImplExporter exp = new AutoJsonRpcServiceImplExporter();
        if (environment.acceptsProfiles(PROFILE_SLOW_JSON_RPC)) {
            exp.setInvocationListener(new InvocationListener() {
                @Override public void willInvoke(Method method, List<JsonNode> list) {
                    try { Thread.sleep(500); } catch (InterruptedException ex) { }
                }
                @Override public void didInvoke(Method method, List<JsonNode> list, Object o, Throwable thrwbl, long l) { }
            });
        } 

        exp.setErrorResolver(errorResolver);
        return exp;
    }
    
}
