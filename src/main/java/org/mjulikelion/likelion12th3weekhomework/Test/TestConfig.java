package org.mjulikelion.likelion12th3weekhomework.Test;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//WebMvcConfigurer를 구현하는 Config 클래스

@Configuration
public class TestConfig implements WebMvcConfigurer {
    private final TestArgumentResolver testArgumentResolver;

    public TestConfig(TestArgumentResolver testArgumentResolver) {
        this.testArgumentResolver = testArgumentResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TestInterceptor())  //Interceptor레포지토리에 TestInterceptor()를 추가
                .addPathPatterns("/memos/interceptor_test"); //어떤 URI에 Interceptor를 추가할지 등록
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(testArgumentResolver);
    }
}
