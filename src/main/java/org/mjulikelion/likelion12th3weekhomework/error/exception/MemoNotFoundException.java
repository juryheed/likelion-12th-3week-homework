package org.mjulikelion.likelion12th3weekhomework.error.exception;

import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;

public class MemoNotFoundException extends CustomException {
    public MemoNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}