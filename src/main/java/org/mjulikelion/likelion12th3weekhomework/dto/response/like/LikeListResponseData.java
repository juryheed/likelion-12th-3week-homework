package org.mjulikelion.likelion12th3weekhomework.dto.response.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class LikeListResponseData {
    private List<LikeResponseData> likeList;
}
