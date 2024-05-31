package org.mjulikelion.likelion12th3weekhomework.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.request.organizaion.OrganizationCreateDto;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.ConflictException;
import org.mjulikelion.likelion12th3weekhomework.error.exception.NotFoundException;
import org.mjulikelion.likelion12th3weekhomework.model.Organization;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.model.UserOrganization;
import org.mjulikelion.likelion12th3weekhomework.repository.OrganizationRepository;
import org.mjulikelion.likelion12th3weekhomework.repository.UserOrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.UUID;

@Transactional
@Service
@AllArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final UserOrganizationRepository userOrganizationRepository;

    //조직 생성
    public void make(User user, OrganizationCreateDto organizationCreateDto) {
        //조직의 이름이 이미 존재하는지 검사
        Organization organization = (organizationRepository.findByName(organizationCreateDto.getName()));
        if (organization != null) {
            throw new ConflictException(ErrorCode.ORGANIZATION_DUPLICATION);
        }

        //조직 만들기
        Organization newOrganization = Organization.builder()
                .name(organizationCreateDto.getName())
                .userOrganization(new LinkedList<>())
                .build();
//
//        UserOrganization newUserOrganization = UserOrganization.builder()
//                .user(user)
//                .organization(newOrganization)
//                .build();

//        newOrganization.getUserOrganization().add(newUserOrganization);

        //조직 레포에 조직 추가
        organizationRepository.save(newOrganization);
        //방금 생성된 조직에 조직 샹성자 자동 추가
        //userOrganizationRepository.save(newUserOrganization);

        UserOrganization userOrganization = UserOrganization.builder()
                .user(user)
                .organization(newOrganization)
                .build();
        userOrganizationRepository.save(userOrganization);
    }

    //조직 가입
    public void join(User user, UUID organizationId) {
        Organization organization = organizationRepository.findById(organizationId).orElseThrow(() -> new NotFoundException(ErrorCode.ORGANIZATION_NOT_FOUND));
        // 해당 유저가 이미 조직에 가입되어 있는지 확인한다.
        boolean isUserAlreadyJoined = userOrganizationRepository.existsByUserAndOrganization(user, organization);

        if (isUserAlreadyJoined) { // 이미 가입된 상태라면
            throw new ConflictException(ErrorCode.ALREADY_ORGANIZATION);
        }

        UserOrganization newUserOrganization = UserOrganization.builder()
                .user(user)
                .organization(organization)
                .build();
        userOrganizationRepository.save(newUserOrganization);
    }


    //조직 탈퇴
    public void exit(User user, UUID id) {
        //userid로 user를 추출
        //id로 organization을 찾는다
        //둘이 겹치는 것을 userorganization에서 삭제한다.
        //User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.ORGANIZATION_NOT_FOUND));

        userOrganizationRepository.deleteByUserAndOrganization(user, organization);
    }
}
