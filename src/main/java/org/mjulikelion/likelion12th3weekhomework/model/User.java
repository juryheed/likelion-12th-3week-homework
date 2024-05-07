package org.mjulikelion.likelion12th3weekhomework.model;

import jakarta.persistence.*;
import lombok.*;
import org.mjulikelion.likelion12th3weekhomework.Entity.BaseEntity;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity(name = "user")
public class User extends BaseEntity {
    //유저는 ID와 name,email,password가  존재한다.

    @Setter
    @Column(unique = true)
    private final String email; //유저 이메일

    @Setter
    @Column(unique = true)
    private final String passWord;  //비밀번호

    @Setter
    @Column(length = 100)
    private String userName;  //유저 Name


    //User와  Memo의 관계
    // user 필드를 기준으로 One To Many 관계를 맺는다.
    // user가 삭제되면 연관된 memo도 삭제된다.
    // user가 null이 되면 memo도 삭제된다.
    // 지연로딩을 사용한다.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Memo> memos;

    //User와 MemoLike의 관계
    // user 필드를 기준으로 One To Many 관계를 맺는다.
    // user가 삭제되면 연관된 memo_like도 삭제된다.
    // user가 null이 되면 memo_like도 삭제된다.
    // 지연로딩을 사용한다.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MemoLike> memolikes;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserOrganization> userOrganization;
}
