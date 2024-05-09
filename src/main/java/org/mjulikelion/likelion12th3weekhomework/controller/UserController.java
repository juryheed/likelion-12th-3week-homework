package org.mjulikelion.likelion12th3weekhomework.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.LoginDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.UserCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.UserUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.ResponseDto;
import org.mjulikelion.likelion12th3weekhomework.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    //유저 추가
    @PostMapping
    public ResponseEntity<ResponseDto<Void>> addUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        userService.userAdd(userCreateDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.CREATED,
                "Success"
        ), HttpStatus.CREATED);
    }

    //유저 정보 수정
    @PatchMapping
    public ResponseEntity<ResponseDto<Void>> userUpdate(@RequestHeader("userId") UUID userId, @RequestBody @Valid UserUpdateDto userUpdateDto) {
        userService.userUpdate(userId, userUpdateDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "Susccess"
        ), HttpStatus.OK);
    }

    //유저 삭제
    @DeleteMapping
    public ResponseEntity<ResponseDto<Void>> userDelete(@RequestHeader("userId") UUID userId) {
        userService.userDelete(userId);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "Susccess"
        ), HttpStatus.OK);
    }


    //로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<Void>> login(@RequestHeader("userId") UUID userId, @RequestBody @Valid LoginDto loginDto) {
        userService.login(userId, loginDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.CREATED,
                "Susccess"
        ), HttpStatus.CREATED);
    }
}
