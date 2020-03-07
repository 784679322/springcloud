//package com.wud.servicezuul.filter;
//
//import com.alibaba.fastjson.JSONObject;
//import com.dkjk.gateway.context.ResponseBean;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ReflectionUtils;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//
///**
// * @author: qjc
// * @createTime: 2019/5/30 19:11
// * @Description: 处理请求发生错误时过滤器
// */
//@Component
//@Slf4j
//public class ErrorFilter extends ZuulFilter {
//    @Override
//    public String filterType() {
//        return "error";
//    }
//
//    @Override
//    public int filterOrder() {
//        //需要在默认的 SendErrorFilter 之前
//        return -1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        // 只有在抛出异常时才会进行拦截
//        return RequestContext.getCurrentContext().containsKey("throwable");
//    }
//
//    @Override
//    public Object run() {
//        try {
//            RequestContext requestContext = RequestContext.getCurrentContext();
//            Object e = requestContext.get("throwable");
//
//            if (e != null && e instanceof ZuulException) {
//                ZuulException zuulException = (ZuulException) e;
//                // 删除该异常信息,不然在下一个过滤器中还会被执行处理
//                requestContext.remove("throwable");
//                // 响应给客户端信息
//                HttpServletResponse response = requestContext.getResponse();
//                response.setHeader("Content-type", "application/json;charset=UTF-8");
//                response.setCharacterEncoding("UTF-8");
//                PrintWriter pw = null;
//                pw = response.getWriter();
//                pw.write(JSONObject.toJSONString(new ResponseBean(99999, "系统出现异常", null)));
//                pw.close();
//            }
//        } catch (Exception ex) {
//            log.error("Exception filtering in custom error filter", ex);
//            ReflectionUtils.rethrowRuntimeException(ex);
//        }
//        return null;
//    }
//}