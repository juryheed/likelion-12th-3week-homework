package org.mjulikelion.likelion12th3weekhomework.error.exception;

import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;

//이미 리소스가 있음
public class ConflictException extends CustomException {
    public ConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}
