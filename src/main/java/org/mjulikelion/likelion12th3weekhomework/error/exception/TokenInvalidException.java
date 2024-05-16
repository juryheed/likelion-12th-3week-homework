package org.mjulikelion.likelion12th3weekhomework.error.exception;

import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;

public class TokenInvalidException extends CustomException {
    public TokenInvalidException(ErrorCode errorCode) {
        super(errorCode);
    }
}
