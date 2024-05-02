package org.mjulikelion.likelion12th3weekhomework.error.exception;

import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;

public class CantAccessExeption extends CustomException {
    public CantAccessExeption(ErrorCode errorCode) {
        super(errorCode);
    }
}