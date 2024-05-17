package org.mjulikelion.likelion12th3weekhomework.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.LoginDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.UserCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.UserUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.ConflictException;
import org.mjulikelion.likelion12th3weekhomework.error.exception.NotFoundException;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //회원가입
    public void userAdd(UserCreateDto userCreateDto) {
        User user = userRepository.findByEmail(userCreateDto.getEmail());

        if (null != user) {
            throw new ConflictException(ErrorCode.USER_DUPLICATION);
        }

        User newUser = User.builder()
                .userName(userCreateDto.getUserName())
                .email(userCreateDto.getEmail())
                .passWord(userCreateDto.getPassword())
                .build();
        userRepository.save(newUser);
    }

    //로그인
    public void login(UUID id, LoginDto loginDto) {

        //id존재 검사
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        //이메일 존재 검사
        User userByEmail = userRepository.findByEmail(loginDto.getEmail());
        if (null == userByEmail) {
            throw new ConflictException(ErrorCode.EMAIL_NOT_FOUND);
        }
        //이메일과 비밀번호 일치 검사
        if (!(loginDto.getPassWord().equals(userByEmail.getPassWord()))) {
            throw new ConflictException(ErrorCode.PASSWORD_NOT_EQUAL);
        }
    }

    //회원 정보 수정
    public void userUpdate(User user, UserUpdateDto userUpdateDto) {
        user.setUserName(userUpdateDto.getUserName());
        userRepository.save(user);
    }

    public void userDelete(User user) {
        userRepository.delete(user);
    }

}
