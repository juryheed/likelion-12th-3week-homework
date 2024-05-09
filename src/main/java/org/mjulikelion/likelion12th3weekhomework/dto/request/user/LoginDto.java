package org.mjulikelion.likelion12th3weekhomework.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
public class LoginDto {
    @NotBlank(message = "이메일이 누락되었습니다.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "이메일이 형식에 맞지 않습니다.")
    @Length(max = 100, message = "이메일은 100자를 넘을 수 없습니다.")
    private String email;

    @NotBlank(message = "패스워드가 없습니다")
    private String passWord;
}
