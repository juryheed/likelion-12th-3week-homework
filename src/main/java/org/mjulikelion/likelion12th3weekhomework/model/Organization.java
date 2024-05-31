package org.mjulikelion.likelion12th3weekhomework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "organization")
public class Organization extends BaseEntity {
    //Organization에는 Id와 userId,name이 존재한다

    @Setter
    @Column(length = 30, unique = true, nullable = false)
    private String name;

    //Organization과 user의 관계 OneToMany
    // organization 필드를 기준으로 유저와 One To Many 관계를 맺는다.
    // organization가 삭제되도 연관된user는 삭제되지 않는다.
    // 지연로딩을 사용한다.
    @JsonIgnore
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserOrganization> userOrganization;

}
