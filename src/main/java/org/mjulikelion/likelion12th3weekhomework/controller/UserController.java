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
    @PostMapping("/add")
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
            , @RequestHeader("email") String email
            , @RequestHeader("password") String password
            , HttpServletResponse response) {
        
        String payload = userId.toString();
        String accessToken = jwtTokenProvider.createToken(payload);

        //쿠키 만드는거
        ResponseCookie cookie = ResponseCookie.from("AccessToken", "Bearer+" + accessToken) //인코딩, 쿠키이름과 쿠키값을 지정한다
                .maxAge(Duration.ofMillis(1800000)) //쿠키의 만료시간
                .path("/")  //모든 경로에서 쿠키 전달 가능
                .build();
        response.addHeader("set-cookie", cookie.toString());//set-cookie로 헤더에 쿠키를 출력함

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "OK:" + accessToken //Access토큰 출력
        ), HttpStatus.OK);
    }

}
