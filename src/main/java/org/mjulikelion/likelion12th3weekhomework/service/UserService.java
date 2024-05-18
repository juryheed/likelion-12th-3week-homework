package org.mjulikelion.likelion12th3weekhomework.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.authentication.PasswordHashEncryption;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.LoginDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.PasswordUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.UserCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.UserUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.ConflictException;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordHashEncryption passwordHashEncryption;

    //회원가입
    public void userAdd(UserCreateDto userCreateDto) {
        String plainPassword = userCreateDto.getPassword();
        String hashedPassword = passwordHashEncryption.encrypt(plainPassword);

        //유저 중복 검사
        User user = userRepository.findByEmail(userCreateDto.getEmail());
        if (null != user) {
            throw new ConflictException(ErrorCode.USER_DUPLICATION);
        }

        User newUser = User.builder()
                .userName(userCreateDto.getUserName())
                .email(userCreateDto.getEmail())
                .password(hashedPassword)   //암호화 된 Password가 들어감
                .build();
        userRepository.save(newUser);

    }

    //로그인
    public void login(LoginDto loginDto) {
        //이메일 존재 검사
        User userByEmail = userRepository.findByEmail(loginDto.getEmail());
        if (null == userByEmail) {
            throw new ConflictException(ErrorCode.EMAIL_NOT_FOUND);
        }
        //입력한 비밀번호와 DB의 암호화된 비밀번호가 일치하는지검사
        if (!passwordHashEncryption.matches(loginDto.getPassword(), userByEmail.getPassword())) {
            throw new ConflictException(ErrorCode.PASSWORD_NOT_EQUAL);
        }
    }


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

        //새로 사용할 비밀번호르 확인
        String hashedPassword = passwordHashEncryption.encrypt(passwordUpdateDto.getNewPassword());  //암호화된 비번

        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    public void userDelete(User user) {
        userRepository.delete(user);
    }

    public UUID findUser(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        return user.getId();
    }

}
