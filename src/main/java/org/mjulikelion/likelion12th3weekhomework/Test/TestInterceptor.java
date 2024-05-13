package org.mjulikelion.likelion12th3weekhomework.Test;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//Interceptor작성

@Slf4j
public class TestInterceptor implements HandlerInterceptor {// HandlerInterceptor는 특정한 URI 호출을 '가로채는' 역할

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) {
        log.info("TestInterceptor preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        log.info("TestInterceptor postHandle");
        log.info("response status: {}", response.getStatus());//현재 상태 코드
        try {
            response.getOutputStream().print("TestInterceptor postHandle"); //포스트맨에 출력되는 문장
        } catch (Exception e) {
            log.error("Error occurred while writing response", e);
        }
    }
}


