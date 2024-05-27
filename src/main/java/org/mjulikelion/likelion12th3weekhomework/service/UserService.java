package org.mjulikelion.likelion12th3weekhomework.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.authentication.PasswordHashEncryption;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.PasswordUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.UserUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.ConflictException;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.repository.UserOrganizationRepository;
import org.mjulikelion.likelion12th3weekhomework.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordHashEncryption passwordHashEncryption;
    private final UserOrganizationRepository userOrganizationRepository;

    //회원 이름 수정
    public void userUpdate(User user, UserUpdateDto userUpdateDto) {
        user.setUserName(userUpdateDto.getUserName());
        userRepository.save(user);
    }

    //비밀번호 수정
    public void passwordUpdate(User user, PasswordUpdateDto passwordUpdateDto) {
        //현재 비밀번호 확인
        String plainPassword = passwordUpdateDto.getNowPassword();
        if (!passwordHashEncryption.matches(plainPassword, user.getPassword())) {
            throw new ConflictException(ErrorCode.PASSWORD_NOT_EQUAL);
        }
        //새로 사용할 비밀번호 암호화
        String hashedPassword = passwordHashEncryption.encrypt(passwordUpdateDto.getNewPassword());  //암호화된 비번

        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    //회원 탈퇴
    public void userDelete(User user) {
        userRepository.delete(user);
    }
}
