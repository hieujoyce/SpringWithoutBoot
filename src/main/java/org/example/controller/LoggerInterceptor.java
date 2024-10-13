package org.example.controller;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class LoggerInterceptor extends RequestBodyAdviceAdapter implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        String test = "null";

//        if ("POST".equalsIgnoreCase(request.getMethod())) {
//            ContentCachingRequestWrapper rq = new ContentCachingRequestWrapper(request);
//            test = rq.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//
//        }
        log.info("request.getContentLength(): " + request.getContentLength());
        if ("GET".equalsIgnoreCase(request.getMethod()) || ("POST".equalsIgnoreCase(request.getMethod()) && request.getContentLength() == 0)) {
            log.info("Hieu Joyce GET: " + test + "| POST body empty");
        }

        return true;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object handleEmptyBody(@Nullable Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.info("Body null");
        return body;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.info("HJ test afterBodyRead: " + body.toString());
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
}
