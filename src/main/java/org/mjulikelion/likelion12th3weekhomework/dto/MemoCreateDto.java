package org.mjulikelion.likelion12th3weekhomework.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemoCreateDto {

    @NotBlank(message = "제목이 누락되었습니다")
    private String title;

    @NotBlank(message = "내용이 누락되었습니다")
    private String content;
}
