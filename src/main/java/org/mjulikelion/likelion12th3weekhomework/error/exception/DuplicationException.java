package org.mjulikelion.likelion12th3weekhomework.error.exception;

import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;

public class DuplicationException extends CustomException {
    public DuplicationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
