package org.mjulikelion.likelion12th3weekhomework.error.exception;

import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;

public class UnathorizedException extends CustomException {
    public UnathorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}