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
@Entity(name = "user_organization")
public class UserOrganization extends BaseEntity {
    //user하고 organization이 있음

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Organization organization;
}
