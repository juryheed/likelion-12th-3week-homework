package org.mjulikelion.likelion12th3weekhomework.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemoCreateDto {
    @NotNull(message = "제목이 누락되었습니다")
    private String title;
    @NotNull(message = "내용이 누락되었습니다")
    private String content;
}
