package org.mjulikelion.likelion12th3weekhomework.error.exception;

import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;

//요청 정보가 잘못됨
public class DtoValidationException extends CustomException {
    public DtoValidationException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}