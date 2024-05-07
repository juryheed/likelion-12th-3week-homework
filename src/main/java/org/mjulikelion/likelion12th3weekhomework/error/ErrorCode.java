package org.mjulikelion.likelion12th3weekhomework.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND("4040", "유저를 찾을 수 없습니다."),//
    MEMO_NOT_FOUND("4041", "메모를 찾을 수 없습니다."),//
    CONTENT_NOT_FOUND("4042", "메모내용을 찾을 수 없습니다"),//
    TITLE_NOT_FOUND("4043", "메모 제목을 찾을 수 없습니다"),//
    CANT_ACCESS("4044", "접근이 불가합니다"),
    USER_DUPLICATION("4045", "이미 존재하는 유저입니다"),
    Password_NOT_EQUAL("4046", "이메일과 패스워드가 일치하지 않습니다"),
    ORGANIZATION_NOT_FOUND("4047", "해당 organization이 존재하지 않습니다"),

    NOT_NULL("9001", "필수값이 누락되었습니다."),
    NOT_BLANK("9002", "필수값이 빈 값이거나 공백으로 되어있습니다."),
    REGEX("9003", "형식에 맞지 않습니다."),
    LENGTH("9004", "길이가 유효하지 않습니다.");


    private final String code;
    private final String message;

    //Dto의 어노테이션을 통해 발생한 에러코드를 반환
    public static ErrorCode resolveValidationErrorCode(String code) {
        return switch (code) {
            case "NotNull" -> NOT_NULL;
            case "NotBlank" -> NOT_BLANK;
            case "Pattern" -> REGEX;
            case "Length" -> LENGTH;
            default -> throw new IllegalArgumentException("Unexpected value: " + code);
        };
    }
}
