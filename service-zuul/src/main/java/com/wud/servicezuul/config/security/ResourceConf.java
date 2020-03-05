package com.wud.servicezuul.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Configuration
class ResourceConf extends ResourceServerConfigurerAdapter {
    @Bean
    RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1,new org.springframework.http.converter.StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    @Autowired
    RedisTemplate redisTemplate;

    @Bean
    public TokenStore tokenStore() {
        return new JedisTokenStore();
    }

    @Bean
    @Primary  //这里必须定义primary  让resource在使用的时候选择这个 不然会有多个默认的实现 会报错.
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)); // 30天
        return defaultTokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/all/**").permitAll()
                .antMatchers("/api/user/**").fullyAuthenticated()
                .antMatchers("/user/**").fullyAuthenticated()
                .antMatchers("/api/amc/**").fullyAuthenticated()
                .antMatchers("/api/all/**").permitAll()
                .antMatchers("/all/**").permitAll();

        //you can implement it like this, but I show method invocation security on write
        //http.addFilterAfter(new RecordFilter(),SecurityContextHolderAwareRequestFilter.class)
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("biz").tokenStore(tokenStore());
    }
}
