package org.mjulikelion.likelion12th3weekhomework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity(name = "user")
public class User extends BaseEntity {
    //유저는 ID와 name,email,password가  존재한다.

    @Column(length = 100, unique = true, nullable = false)
    private final String email; //유저 이메일

    @Setter
    @Column(length = 300, nullable = false)
    private String password;  //비밀번호

    @Setter
    @Column(length = 30, nullable = false)
    private String userName;  //유저 Name

    //User와  Memo의 관계
    // user 필드를 기준으로 One To Many 관계를 맺는다.
    // user가 삭제되면 연관된 memo도 삭제된다.
    // user가 null이 되면 memo도 삭제된다.
    // 지연로딩을 사용한다.
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Memo> memos;

    //User와 MemoLike의 관계
    // user 필드를 기준으로 One To Many 관계를 맺는다.
    // user가 삭제되면 연관된 memo_like도 삭제된다.
    // user가 null이 되면 memo_like도 삭제된다.
    // 지연로딩을 사용한다.
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MemoLike> memolikes;

    //User와 userorganization의 관계
    // user 필드를 기준으로 One To Many 관계를 맺는다.
    // user가 삭제되면 연관된 userorganization도 삭제된다.
    // user가 null이면 userorganization가 삭제되지 않는다.
    // 지연로딩을 사용한다.
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserOrganization> userOrganization;
}
