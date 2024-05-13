package org.mjulikelion.likelion12th3weekhomework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "memo_like")
public class MemoLike extends BaseEntity {
    //좋아요는 memo,user가 있다


    //메모와 좋아요의 관계,OnetoMany,유저:many 선택
    // memo가 삭제되면 연관된 memo_like도 삭제된다.
    // memo가 null이 되면 memo_like도 삭제된다.
    // 지연로딩을 사용한다.
    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)//값이 비어있으면 안되고 지연로딩
    private Memo memo;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)//값이 비어있으면 안되고 지연 로딩
    private User user;

}
