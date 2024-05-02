package org.mjulikelion.likelion12th3weekhomework.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateDto {

    @NotNull(message = "유저Id가 누락되었습니다")
    private String userId;

    @NotNull(message = "유저의 이름이 누락되었습니다.")
    private String userName;
}
