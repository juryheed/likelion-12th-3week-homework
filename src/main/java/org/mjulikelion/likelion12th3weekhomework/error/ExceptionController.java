package org.mjulikelion.likelion12th3weekhomework.error;

import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.likelion12th3weekhomework.error.exception.CustomException;
import org.mjulikelion.likelion12th3weekhomework.error.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {
    //UserNotFoundException 핸들러
    @ResponseStatus(HttpStatus.NOT_FOUND)//response HTTP 상태 코드를 404(NOT_FOUND)로 설정
    @ExceptionHandler(NotFoundException.class)//UserNotFoundException예외를 처리하는 핸들러
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(
            NotFoundException userNotFoundException) {

        this.writeLog(userNotFoundException);
        return new ResponseEntity<>(ErrorResponseDto.res(userNotFoundException), HttpStatus.NOT_FOUND);
    }


    // 원인을 알 수 없는 예외 처리
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception) {
        this.writeLog(exception);
        return new ResponseEntity<>(
                ErrorResponseDto.res(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), exception),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void writeLog(CustomException customException) {
        String exceptionName = customException.getClass().getSimpleName();
        ErrorCode errorCode = customException.getErrorCode();
        String detail = customException.getDetail();

        log.error("({}){}: {}", exceptionName, errorCode.getMessage(), detail);
    }

    private void writeLog(Exception exception) {
        log.error("({}){}", exception.getClass().getSimpleName(), exception.getMessage());
    }
}