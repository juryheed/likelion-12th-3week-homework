package org.mjulikelion.likelion12th3weekhomework.error.exception;

import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;

//인증 실패, 로그인 실패
public class UnauthorizedException extends CustomException {
    public UnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}