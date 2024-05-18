package org.mjulikelion.likelion12th3weekhomework.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginDto {
    @NotBlank(message = "이메일이 누락되었습니다.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "이메일이 형식에 맞지 않습니다.")
    @Size(min = 5, max = 100, message = "이메일은 최소 5글자 최대 100자 입니다.")
    private String email;

    @NotBlank(message = "패스워드가 누락되었습니다")
    @Size(min = 8, max = 40, message = "비밀번호는 최소8글자 최대 40글자 입니다")
    private String password;
}
