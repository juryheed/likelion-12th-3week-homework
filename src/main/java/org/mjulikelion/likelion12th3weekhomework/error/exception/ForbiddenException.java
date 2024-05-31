package org.mjulikelion.likelion12th3weekhomework.error.exception;

import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;

//인가 실패, 권한없음
public class ForbiddenException extends CustomException {
    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
