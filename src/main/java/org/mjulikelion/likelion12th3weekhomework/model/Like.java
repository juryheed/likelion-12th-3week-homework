package org.mjulikelion.likelion12th3weekhomework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class Like {
    private String name;//좋아요 누른사람 이름
    private LocalDateTime time;//좋아요 누른 시간
    private int memoId;//좋아요가 눌린 메모 아이디

    //private int likeNum = 0;  //누적 좋아요 수


}
