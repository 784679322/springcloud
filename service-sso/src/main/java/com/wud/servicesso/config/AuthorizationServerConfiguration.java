package com.wud.servicesso.config;

import com.wud.servicesso.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Resource
    private DataSource dataSource;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    MyUserDetailsService userDetailsService;



    //这个是定义授权的请求的路径的Bean
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }
    /**
     *
     *      ClientDetailsServiceConfigurer 用来配置客户端详情服务
     *     为了测试客户端与凭证存储在内存(生产应该用数据库来存储,oauth有标准数据库模板)
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//
//                .withClient("client1-code") // client_id
//                .secret(bCryptPasswordEncoder.encode("123")) // client_secret
//                .authorizedGrantTypes("authorization_code") // 该client允许的授权类型
//                .scopes("app") // 允许的授权范围
//                .redirectUris("https://www.baidu.com")
//                .resourceIds("goods", "mechant")    //资源服务器id,需要与资源服务器对应
//
//                .and()
//                .withClient("client2-credentials")
//                .secret(bCryptPasswordEncoder.encode("123"))
//                .authorizedGrantTypes("client_credentials")
//                .scopes("app")
//                .resourceIds("goods", "mechant");
        clients.withClientDetails(clientDetails());
    }

    /**
     * @param security 用来配置令牌端点(Token Endpoint)的安全约束
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //开启支持通过表单方式提交client_id和client_secret,否则请求时以basic auth方式,头信息传递Authorization发送请求
        security.allowFormAuthenticationForClients();
    }

    /**
     * @param endpoints 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //authenticationManager配合password模式使用
        endpoints.authenticationManager(authenticationManager);
        //这里使用内存存储token,也可以使用redis和数据库
        endpoints.tokenStore(new InMemoryTokenStore());
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST);
        endpoints.tokenEnhancer(new TokenEnhancer() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
                //在返回token的时候可以加上一些自定义数据
                DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) oAuth2AccessToken;
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("nickname", "测试姓名");
                token.setAdditionalInformation(map);
                return token;
            }
        });
    }



    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        //tokenServices.setClientDetailsService(clientDetails());
        // token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60*60*12);
        // refresh_token默认30天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }

}