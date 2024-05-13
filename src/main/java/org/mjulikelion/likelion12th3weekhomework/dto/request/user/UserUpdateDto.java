package org.mjulikelion.likelion12th3weekhomework.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserUpdateDto {
    @NotBlank(message = "유저의 이름이 누락되었습니다")
    @Size(min = 1, max = 30, message = "이름은 최소 1글자, 최대 30글자 입니다")
    private String userName;
}
