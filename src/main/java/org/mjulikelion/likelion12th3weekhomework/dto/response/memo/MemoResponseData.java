package org.mjulikelion.likelion12th3weekhomework.dto.response.memo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemoResponseData {
    private String title;
    private String content;
}
