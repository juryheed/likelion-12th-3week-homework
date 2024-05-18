package org.mjulikelion.likelion12th3weekhomework.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.authentication.AuthenticatedUser;
import org.mjulikelion.likelion12th3weekhomework.authentication.JwtTokenProvider;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.PasswordUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.UserUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.ResponseDto;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    //유저 이름 수정
    @PatchMapping
    public ResponseEntity<ResponseDto<Void>> userUpdate(@AuthenticatedUser User user, @RequestBody @Valid UserUpdateDto userUpdateDto) {
        userService.userUpdate(user, userUpdateDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "Susccess"
        ), HttpStatus.OK);
    }

    //비밀번호 변경
    @PatchMapping("/password")
    public ResponseEntity<ResponseDto<Void>> passwordUpdate(@AuthenticatedUser User user, @RequestBody @Valid PasswordUpdateDto passwordUpdateDto) {
        userService.passwordUpdate(user, passwordUpdateDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "Susccess"
        ), HttpStatus.OK);
    }

    //회원 탈퇴
    @DeleteMapping
    public ResponseEntity<ResponseDto<Void>> userDelete(@AuthenticatedUser User user) {
        userService.userDelete(user);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "Susccess"
        ), HttpStatus.OK);
    }
}
