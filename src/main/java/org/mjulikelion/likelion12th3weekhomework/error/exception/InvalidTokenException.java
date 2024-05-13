package org.mjulikelion.likelion12th3weekhomework.error.exception;

import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;

public class InvalidTokenException extends CustomException {
    public InvalidTokenException(ErrorCode errorCode, String text) {
        super(errorCode);
    }
}


