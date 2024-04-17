package org.mjulikelion.likelion12th3weekhomework.memo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Memo {
    private final String content; //메모내용
    private final int memoId;  //메모 id
    private final String userId;   //유저id
    private final String userName; //유저이름
}



