package org.mjulikelion.likelion12th3weekhomework.error.exception;

import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;

public class OrganizationNotFoundException extends CustomException {
    public OrganizationNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}