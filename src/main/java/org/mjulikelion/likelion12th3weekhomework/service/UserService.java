package org.mjulikelion.likelion12th3weekhomework.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.UserCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.UserDeleteDto;
import org.mjulikelion.likelion12th3weekhomework.dto.UserUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.MemoNotFoundException;
import org.mjulikelion.likelion12th3weekhomework.error.exception.UserNotFoundException;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void userAdd(UserCreateDto userCreateDto) {
        User newUser = User.builder()
                .userName(userCreateDto.getUserName())
                .email(userCreateDto.getEmail())
                .passWord(userCreateDto.getPassword())
                .build();
        userRepository.save(newUser);
    }

    public void userUpdate(UUID userId, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new MemoNotFoundException(ErrorCode.MEMO_NOT_FOUND));

        User newUser = User.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .passWord(user.getPassWord())
                .build();
        user = newUser;
    }

    public void userDelete(UUID userId, UserDeleteDto userDeleteDto) {
        User user = userRepository.findById(userDeleteDto.getUserId()).orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));

        if (!(userId.equals(user.getId()))) {
            throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND);
        }
        userRepository.delete(user);
    }

    public void login(UUID userId, String email, String password) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));

        if (!((email.equals(user.getEmail())) & (password.equals(user.getPassWord())))) {
            throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND);
        }
    }


}
