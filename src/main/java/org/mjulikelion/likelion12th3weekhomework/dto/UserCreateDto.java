package org.mjulikelion.likelion12th3weekhomework.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateDto {

    @NotBlank(message = "유저의 이름이 누락되었습니다")
    private String userName;

    @NotBlank(message = "이메일이 누락되었습니다")
    private String email;

    @NotBlank(message = "password가 누락되었습니다")
    private String password;
}
