//package com.wud.servicezuul.filter;
//
//import com.alibaba.fastjson.JSON;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author: qjc
// * @createTime: 2019/5/6 11:07
// * @Description:post过滤器可以在请求转发后获取请求信息和响应入库，或者日志记录
// */
//@Component
//@Slf4j
//public class ResponseFilter extends ZuulFilter {
//
//    @Override
//    public String filterType() {
//        return "post";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 2;
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
//        // 如果前面的拦截器不进行路由，那么后面的过滤器就没必要执行
//        if (!requestContext.sendZuulResponse()) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        InputStream stream = requestContext.getResponseDataStream();
//        if (stream == null) {
//            return null;
//        }
//
//        HttpServletRequest request = requestContext.getRequest();
//
//        String requestParams = getRequestParams(requestContext, request);
//        System.err.println(requestParams);
//
//        try {
//            String responseBoby = IOUtils.toString(stream);
//            RequestContext.getCurrentContext().setResponseBody(responseBoby);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    //获取请求参数，适用于POST请求/GET请求，以及参数拼接在URL后面的POST请求
//    private String getRequestParams(RequestContext requestContext, HttpServletRequest request) {
//        String requestParams = null;
//        String requestMethod = request.getMethod();
//        StringBuilder params = new StringBuilder();
//        Enumeration<String> names = request.getParameterNames();
//        if (requestMethod.equals("GET")) {
//            while (names.hasMoreElements()) {
//                String name = (String) names.nextElement();
//                params.append(name);
//                params.append("=");
//                params.append(request.getParameter(name));
//                params.append("&");
//            }
//            requestParams = params.delete(params.length() - 1, params.length()).toString();
//        } else {
//            Map<String, String> res = new HashMap<>();
//            Enumeration<?> temp = request.getParameterNames();
//            if (null != temp) {
//                while (temp.hasMoreElements()) {
//                    String en = (String) temp.nextElement();
//                    String value = request.getParameter(en);
//                    res.put(en, value);
//                }
//                requestParams = JSON.toJSONString(res);
//            }
//            if (StringUtils.isBlank(requestParams) || "{}".equals(requestParams)) {
//                BufferedReader br = null;
//                StringBuilder sb = new StringBuilder("");
//                try {
//                    br = request.getReader();
//                    String str;
//                    while ((str = br.readLine()) != null) {
//                        sb.append(str);
//                    }
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (null != br) {
//                        try {
//                            br.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//                requestParams = sb.toString();
//            }
//        }
//        return requestParams;
//    }
//}