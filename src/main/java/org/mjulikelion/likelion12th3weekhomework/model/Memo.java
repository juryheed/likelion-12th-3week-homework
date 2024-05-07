package org.mjulikelion.likelion12th3weekhomework.model;

import jakarta.persistence.*;
import lombok.*;
import org.mjulikelion.likelion12th3weekhomework.Entity.BaseEntity;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(force = true)
@Entity(name = "memo")
public class Memo extends BaseEntity {
    //메모는 메모 아이디,메모내용,메모제목 이 존재 한다

    @Setter
    @Column(length = 2000, nullable = false)// 길이는 100자 이하이고, 비어있을 수 없다.
    private final String content; //메모내용

    @Setter
    @Column(length = 50, nullable = false)// 길이는 100자 이하이고, 비어있을 수 없다.
    private final String title; //메모 제목

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

    //메모와 좋아요의 관계,OnetoMany,유저:many 선택
    // memo 필드를 기준으로 One To Many 관계를 맺는다.
    // memo가 삭제되면 연관된 memo_like도 삭제된다.
    // memo가 null이 되면 memo_like도 삭제된다. 지연로딩을 사용한다.
    @OneToMany(mappedBy = "memo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MemoLike> memoLikes;

}


