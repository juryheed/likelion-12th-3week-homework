package org.mjulikelion.likelion12th3weekhomework.controller;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.ResponseDto;
import org.mjulikelion.likelion12th3weekhomework.dto.UserCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.UserDeleteDto;
import org.mjulikelion.likelion12th3weekhomework.dto.UserUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDto<Void>> addUser(@RequestBody UserCreateDto userCreateDto) {
        userService.userAdd(userCreateDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.CREATED,
                "Success"
        ), HttpStatus.CREATED);
    }

    //유저 정보 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> userUpdate(@PathVariable String userId, @RequestBody UserUpdateDto userUpdateDto) {
        userService.userUpdate(userId, userUpdateDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.CREATED,
                "Susccess"
        ), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> userDelete(@PathVariable UserDeleteDto userDeleteDto) {
        userService.userDelete(userDeleteDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.CREATED,
                "Susccess"
        ), HttpStatus.CREATED);


    }

}
