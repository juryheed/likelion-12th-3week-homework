package org.mjulikelion.likelion12th3weekhomework.dto.request.memo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemoCreateDto {

    @NotBlank(message = "제목이 누락되었습니다")
    @Size(min = 1, max = 50, message = "제목은 최대 50자 입니다")
    private String title;

    @NotBlank(message = "내용이 누락되었습니다")
    @Size(max = 2000, message = "내용은 최대 2000자 입니다")
    private String content;
}
