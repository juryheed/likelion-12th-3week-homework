package org.mjulikelion.likelion12th3weekhomework.model;

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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)//값이 비어있으면 안되고 지연로딩
    private Memo memo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)//값이 비어있으면 안되고 지연 로딩
    private User user;

}
