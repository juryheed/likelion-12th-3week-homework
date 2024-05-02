package org.mjulikelion.likelion12th3weekhomework.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mjulikelion.likelion12th3weekhomework.model.Like;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class LikeListResponseData {
    private List<Like> like;
}
