package org.mjulikelion.likelion12th3weekhomework.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.UserCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.UserUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.repository.UserRepository;
import org.springframework.stereotype.Service;

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

    public void userUpdate(User user, UserUpdateDto userUpdateDto) {
        user.setUserName(userUpdateDto.getUserName());
        userRepository.save(user);
    }

    public void userDelete(User user) {
        userRepository.delete(user);
    }

}
