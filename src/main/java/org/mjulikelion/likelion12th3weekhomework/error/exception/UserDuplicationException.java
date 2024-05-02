package org.mjulikelion.likelion12th3weekhomework.error.exception;

import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;

public class UserDuplicationException extends CustomException {
    public UserDuplicationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
