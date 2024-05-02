package org.mjulikelion.likelion12th3weekhomework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {
    private final String statusCode;
    private final String message;
    private final T data;

    //응답 데이터를 담고 있다
    //statisCode의 상태값과 메세지(Success)를 전달한다.
    //데이터가 없는 경우
    public static <T> ResponseDto<T> res(final HttpStatusCode statusCode, final String message) {
        return new ResponseDto<>(String.valueOf(statusCode.value()), message, null);
    }

    ////statisCode의 상태값과 메세지(Success) + 데이터 내용까지 전달한다.
    //데이터가 있는 경우
    public static <T> ResponseDto<T> res(final HttpStatusCode statusCode, final String message, final T data) {
        return new ResponseDto<>(String.valueOf(statusCode.value()), message, data);
    }
}
