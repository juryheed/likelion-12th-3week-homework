package org.mjulikelion.likelion12th3weekhomework.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDeleteDto {
    @NotNull(message = "유저의 Id가 누락되었습니다.")
    private int userId;
}
