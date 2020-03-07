//package com.wud.servicezuul.filter;
//
//import com.alibaba.fastjson.JSONObject;
//import com.dkjk.gateway.context.ResponseBean;
//import com.dkjk.gateway.domain.DockCompanyService;
//import com.dkjk.gateway.domain.UserService;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * @author: qjc
// * @createTime: 2019/4/13 16:08
// * @Description: 接口安全验证过滤器
// */
//@Component
//@Slf4j
//public class ValidFilter extends ZuulFilter {
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        // 进行跨域请求的时候，并且请求头中有额外参数，比如token，客户端会先发送一个OPTIONS请求来探测后续需要发起的跨域POST请求是否安全可接受
//        // 所以这个请求就不需要拦截，下面是处理方式
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = requestContext.getRequest();
//        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
//            log.info("OPTIONS请求不做拦截操作");
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = requestContext.getRequest();
//        String userToken = request.getHeader("apikey");
//        if (StringUtils.isBlank(userToken)) {
//            log.warn("apikey为空");
//            sendError(requestContext, 99001, "请传输参数apikey");
//            return null;
//        }
//        return null;
//    }
//
//    /**
//     * 发送错误消息
//     *
//     * @param requestContext
//     * @param status
//     * @param msg
//     */
//    private void sendError(RequestContext requestContext, int status, String msg) {
//        //过滤该请求，不往下级服务转发，到此结束不进行路由
//        requestContext.setSendZuulResponse(false);
//        HttpServletResponse response = requestContext.getResponse();
//        response.setHeader("Content-type", "application/json;charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter pw = null;
//        try {
//            pw = response.getWriter();
//            pw.write(JSONObject.toJSONString(new ResponseBean(status, msg, null)));
//        } catch (IOException e) {
//            log.error(e.getMessage());
//        } finally {
//            pw.close();
//        }
//    }
//}