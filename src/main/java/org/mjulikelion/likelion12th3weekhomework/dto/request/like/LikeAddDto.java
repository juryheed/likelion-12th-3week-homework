package org.mjulikelion.likelion12th3weekhomework.dto.request.like;

import lombok.Getter;

import java.util.UUID;

@Getter
public class LikeAddDto {

    private UUID userId;
    private UUID memoId;
}
