package org.mjulikelion.likelion12th3weekhomework.dto.request.memo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemoUpdateDto {

    @NotBlank(message = "내용이 누락되었습니다")
    private String content;

}
