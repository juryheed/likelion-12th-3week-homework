package org.mjulikelion.likelion12th3weekhomework.dto.response.memo;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MemoListResponseData {
    private List<MemoResponseData> memoList;
}
