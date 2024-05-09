package org.mjulikelion.likelion12th3weekhomework.dto.response.memo;

import lombok.Builder;
import lombok.Getter;
import org.mjulikelion.likelion12th3weekhomework.model.Memo;

@Getter
@Builder
public class MemoResponseData {
    private Memo memo;
}
