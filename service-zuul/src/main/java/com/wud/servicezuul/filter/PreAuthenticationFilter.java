package com.wud.servicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wud.cloud.Dto.User;
import com.wud.cloud.service.ServiceHi;
import com.wud.servicezuul.service.SSOService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.tomcat.util.http.parser.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class PreAuthenticationFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());



    public static final String TOKEN_ENDPOINT="/auth/aouth/token";
    public static final String TOKEN_KEY_ENDPOINT="/auth/aouth/token_key";
    public static final String CHECK_TOKEN_ENDPOINT="/auth/aouth/check_token";
    public static final String SSO="/auth/sso/list";

    private  BearerTokenExtractor tokenExtractor= new BearerTokenExtractor();
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestURI = request.getRequestURI();
        return !(requestURI.equals(TOKEN_ENDPOINT) && requestURI.equals(TOKEN_KEY_ENDPOINT) && requestURI.equals(CHECK_TOKEN_ENDPOINT)&& requestURI.equals(SSO));
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("uri{}", request.getRequestURI());
        }
        Authentication authorization=this.tokenExtractor.extract(request);
        if(authorization == null || authorization.getPrincipal()==null){
            //不会继续往下执行 不会调用服务接口了 网关直接响应给客户了
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody("Full authentication is required to access this resource");
            requestContext.setResponseStatusCode(401);
            return null;
        }
        String accessToken =(String)authorization.getPrincipal();
        this.logger.info("token{}",accessToken);
        // todo 解析token，调用权限支撑平台，获取权限信息，组织到用户信息中传递给下游微服务
        return null;
    }


}
