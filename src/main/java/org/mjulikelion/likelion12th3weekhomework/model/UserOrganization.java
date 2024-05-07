package org.mjulikelion.likelion12th3weekhomework.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.Entity.BaseEntity;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_organization")
public class UserOrganization extends BaseEntity {
    //user하고 organization이 있음

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Organization organization;
}
