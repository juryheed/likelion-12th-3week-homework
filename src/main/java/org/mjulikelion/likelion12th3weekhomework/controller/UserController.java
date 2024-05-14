package org.mjulikelion.likelion12th3weekhomework.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.authentication.AuthenticatedUser;
import org.mjulikelion.likelion12th3weekhomework.authentication.JwtTokenProvider;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.UserCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.UserUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.ResponseDto;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

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
    public ResponseEntity<ResponseDto<Void>> userUpdate(@AuthenticatedUser User user, @RequestBody @Valid UserUpdateDto userUpdateDto) {
        userService.userUpdate(user, userUpdateDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "Susccess"
        ), HttpStatus.OK);
    }

    //유저 삭제
    @DeleteMapping
    public ResponseEntity<ResponseDto<Void>> userDelete(@AuthenticatedUser User user) {
        userService.userDelete(user);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "Susccess"
        ), HttpStatus.OK);
    }


    //로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<Void>> login(@RequestHeader("userId") UUID userId
            , HttpServletResponse response) {

        String payload = userId.toString();
        String accessToken = jwtTokenProvider.createToken(payload);

        ResponseCookie cookie = ResponseCookie.from("AccessToken", accessToken)
                .maxAge(Duration.ofMillis(1800000))
                .path("/")
                .build();
        response.addHeader("set-cookie", cookie.toString());


        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "OK:" + accessToken //Access토큰 출력
        ), HttpStatus.OK);


    }

}
