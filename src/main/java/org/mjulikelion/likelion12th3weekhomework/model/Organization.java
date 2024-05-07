package org.mjulikelion.likelion12th3weekhomework.model;

import jakarta.persistence.*;
import lombok.*;
import org.mjulikelion.likelion12th3weekhomework.Entity.BaseEntity;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "organization")
public class Organization extends BaseEntity {
    //Organization에는 Id와 userId,name이 존재한다

    @Setter
    @Column(length = 50, unique = true)
    private String name;

    //Organization과 user의 관계 OneToMany
    // organization 필드를 기준으로 유저와 One To Many 관계를 맺는다.
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserOrganization> userOrganization;

}
