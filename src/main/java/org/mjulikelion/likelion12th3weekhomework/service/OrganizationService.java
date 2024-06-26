package org.mjulikelion.likelion12th3weekhomework.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.request.organizaion.OrganizationCreateDto;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.DuplicationException;
import org.mjulikelion.likelion12th3weekhomework.error.exception.NotFoundException;
import org.mjulikelion.likelion12th3weekhomework.model.Organization;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.model.UserOrganization;
import org.mjulikelion.likelion12th3weekhomework.repository.OrganizationRepository;
import org.mjulikelion.likelion12th3weekhomework.repository.UserOrganizationRepository;
import org.mjulikelion.likelion12th3weekhomework.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.UUID;

@Transactional
@Service
@AllArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final UserOrganizationRepository userOrganizationRepository;

    //조직 생성
    public void make(UUID userId, OrganizationCreateDto organizationCreateDto) {

        if (organizationRepository.findByName(organizationCreateDto.getName())) {
            throw new DuplicationException(ErrorCode.ORGANIZATION_DUPLICATION);
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        Organization newOrganization = Organization.builder()
                .name(organizationCreateDto.getName())
                .userOrganization(new LinkedList<>())
                .build();

        UserOrganization newUserOrganization = UserOrganization.builder()
                .user(user)
                .organization(newOrganization)
                .build();

        newOrganization.getUserOrganization().add(newUserOrganization);

        organizationRepository.save(newOrganization);
    }

    //조직 가입
    public void join(UUID organizationId, UUID userId) {
        if (organizationRepository.existsById(userId)) {
            throw new DuplicationException(ErrorCode.ORGANIZATION_DUPLICATION);
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        Organization organization = organizationRepository.findById(organizationId).orElseThrow(() -> new NotFoundException(ErrorCode.ORGANIZATION_NOT_FOUND));
        UserOrganization newUserOrganization = UserOrganization.builder()
                .user(user)
                .organization(organization)
                .build();
        userOrganizationRepository.save(newUserOrganization);
    }


    //조직 탈퇴
    public void exit(UUID id, UUID userId) {
        //userid로 user를 추출
        //id로 organization을 찾는다
        //둘이 겹치는 것을 userorganization에서 삭제한다.
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.ORGANIZATION_NOT_FOUND));

        userOrganizationRepository.deleteByUserAndOrganization(user, organization);

    }


}
