package org.mjulikelion.likelion12th3weekhomework.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.mjulikelion.likelion12th3weekhomework.model.Memo;

import java.util.List;

@Getter
@Builder
public class MemoListResponseData {
    private List<Memo> memoList;
}
