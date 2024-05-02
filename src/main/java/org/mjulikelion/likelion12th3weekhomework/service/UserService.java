package org.mjulikelion.likelion12th3weekhomework.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.UserCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.UserDeleteDto;
import org.mjulikelion.likelion12th3weekhomework.dto.UserUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void userAdd(UserCreateDto userCreateDto) {
        User newUser = User.builder().userId(userCreateDto.getUserId()).userName(userCreateDto.getUserName()).build();
        userRepository.userAdd(newUser);
    }

    public void userUpdate(String userId, UserUpdateDto userUpdateDto) {
        User userUpdate = User.builder().userId(userUpdateDto.getUserId()).userName(userUpdateDto.getUserName()).build();
        userRepository.userUpdate(userId, userUpdate);
    }

    public void userDelete(UserDeleteDto userDeleteDto) {
        User userDelete = User.builder().userId(String.valueOf(userDeleteDto.getUserId())).build();
        userRepository.userDelete(userDelete);
    }

}
