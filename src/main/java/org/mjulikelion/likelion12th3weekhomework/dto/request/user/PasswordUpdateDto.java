package org.mjulikelion.likelion12th3weekhomework.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PasswordUpdateDto {
    @NotBlank(message = "원래 사용중인 패스워드가 누락되었습니다")
    @Size(min = 8, max = 40, message = "비밀번호는 최소8글자 최대 30글자 입니다")
    private String nowPassword;

    @NotBlank(message = "새로 설정 할 패스워드가 누락되었습니다")
    @Size(min = 8, max = 40, message = "비밀번호는 최소8글자 최대 30글자 입니다")
    private String newPassword;
}
