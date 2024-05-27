package org.mjulikelion.likelion12th3weekhomework.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.authentication.PasswordHashEncryption;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.LoginDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.UserCreateDto;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.ConflictException;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
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
                .password(hashedPassword)
                .build();
        userRepository.save(newUser);
    }

    //로그인
    public void login(LoginDto loginDto) {
        User userByEmail = userRepository.findByEmail(loginDto.getEmail());
        if (null == userByEmail) {
            throw new ConflictException(ErrorCode.USER_NOT_FOUND);
        }
        //입력한 비밀번호와 DB의 암호화된 비밀번호가 일치하는지검사
        if (!passwordHashEncryption.matches(loginDto.getPassword(), userByEmail.getPassword())) {
            throw new ConflictException(ErrorCode.PASSWORD_NOT_EQUAL);
        }
    }
}

