package org.mjulikelion.likelion12th3weekhomework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class Memo {
    //메모는 ID, content(메모의 내용), 작성 유저 ID가 존재한다.
    private final int memoId;  //메모 id
    private final String content; //메모내용
    private final String title; //메모 제목

    //Json변환시 유저id는 반환되지 않는다
    //@JsonIgnore
    private final String userId;   //유저id

    //좋아요 리스트
    private final List<Like> likeList = new LinkedList<>();

}


