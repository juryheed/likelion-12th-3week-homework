package org.mjulikelion.likelion12th3weekhomework.repository;

import org.mjulikelion.likelion12th3weekhomework.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

}
