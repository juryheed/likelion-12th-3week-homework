package org.mjulikelion.likelion12th3weekhomework.Test;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

//Resolver 작성

@Component
public class TestArgumentResolver implements HandlerMethodArgumentResolver {
    @Override//어떤 어노테이션의 기능인지 작성
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Test.class);
    }

    @Override//해당 어노테이션의 실제 동작 정의
    public String resolveArgument(final MethodParameter parameter,
                                  final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest,
                                  final WebDataBinderFactory binderFactory) {
        return "TestArgumentResolver";//이 문자열을 반환하고 싶음
    }
}
