package org.mjulikelion.likelion12th3weekhomework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
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
    @Column(unique = true, nullable = false)
    @Size(min = 1, max = 30)
    private String name;

    //Organization과 user의 관계 OneToMany
    // organization 필드를 기준으로 유저와 One To Many 관계를 맺는다.
    // organization가 삭제되도 연관된user는 삭제되지 않는다.
    // 지연로딩을 사용한다.
    @JsonIgnore
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private List<UserOrganization> userOrganization;

}
