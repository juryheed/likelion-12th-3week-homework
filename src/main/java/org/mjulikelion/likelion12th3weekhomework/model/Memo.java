package org.mjulikelion.likelion12th3weekhomework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(force = true)
@Entity(name = "memo")
public class Memo extends BaseEntity {
    //메모는 메모 아이디,메모내용,메모제목 이 존재 한다

    @Setter
    @Column(nullable = false)
    @Size(min = 1, max = 50)
    private final String title; //메모 제목

    @Setter
    @Column(nullable = false)
    @Size(max = 2000)
    private String content; //메모내용

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

    //메모와 좋아요의 관계,OnetoMany,유저:many 선택
    // memo 필드를 기준으로 One To Many 관계를 맺는다.
    // memo가 삭제되면 연관된 memo_like도 삭제된다.
    // memo가 null이 되면 memo_like도 삭제된다. 지연로딩을 사용한다.
    @JsonIgnore
    @OneToMany(mappedBy = "memo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MemoLike> memoLikes;

}


