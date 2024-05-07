package org.mjulikelion.likelion12th3weekhomework.repository;

import org.mjulikelion.likelion12th3weekhomework.model.Organization;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.model.UserOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserOrganizationRepository extends JpaRepository<UserOrganization, UUID> {
    void deleteByUserAndOrganization(User user, Organization organization);

}
