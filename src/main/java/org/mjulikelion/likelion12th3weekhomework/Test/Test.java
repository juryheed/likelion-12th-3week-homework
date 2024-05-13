package org.mjulikelion.likelion12th3weekhomework.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @Test 어노테이션 생성

@Target(ElementType.PARAMETER)//이 어노테이션이 파라미터에서 사용될것임을 알려줌
@Retention(RetentionPolicy.RUNTIME)//런타임시에 사용할거임
public @interface Test {
}
