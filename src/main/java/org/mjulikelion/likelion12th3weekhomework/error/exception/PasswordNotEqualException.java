package org.mjulikelion.likelion12th3weekhomework.error.exception;

import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;

public class PasswordNotEqualException extends CustomException {
    public PasswordNotEqualException(ErrorCode errorCode) {
        super(errorCode);

    }
}

