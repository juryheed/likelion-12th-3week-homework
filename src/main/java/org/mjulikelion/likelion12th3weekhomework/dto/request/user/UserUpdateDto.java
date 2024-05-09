package org.mjulikelion.likelion12th3weekhomework.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserUpdateDto {

    @NotBlank(message = "유저의 이름이 누락되었습니다.")
    private String userName;

}
