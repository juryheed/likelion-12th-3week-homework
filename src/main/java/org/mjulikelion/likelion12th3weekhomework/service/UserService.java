package org.mjulikelion.likelion12th3weekhomework.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.LoginDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.UserCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.UserUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.NotFoundException;
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

    public void userUpdate(UUID id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        User newUser = User.builder()
                .userName(userUpdateDto.getUserName())
                .email(user.getEmail())
                .passWord(user.getPassWord())
                .build();
        userRepository.save(newUser);
    }

    public void userDelete(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        userRepository.delete(user);
    }

    public void login(UUID userId, LoginDto loginDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        if (!((loginDto.getEmail().equals(user.getEmail())) & (loginDto.getPassWord().equals(user.getPassWord())))) {
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
        }
    }


}
