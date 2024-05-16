package org.mjulikelion.likelion12th3weekhomework.error.exception;

import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;

//리스스를 찾을수 없음
public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
