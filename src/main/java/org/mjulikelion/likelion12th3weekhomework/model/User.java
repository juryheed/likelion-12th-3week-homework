package org.mjulikelion.likelion12th3weekhomework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class User {
    //유저는 ID와 name이 존재한다.
    private final String userId;
    private final String userName;
}
