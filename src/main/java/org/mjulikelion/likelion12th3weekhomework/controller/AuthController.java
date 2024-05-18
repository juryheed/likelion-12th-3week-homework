package org.mjulikelion.likelion12th3weekhomework.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.authentication.AuthenticatedUser;
import org.mjulikelion.likelion12th3weekhomework.authentication.JwtTokenProvider;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.LoginDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.user.UserCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.ResponseDto;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private static final String TOKEN_COOKIE_NAME = "AccessToken";
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    //로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<Void>> login(@RequestBody @Valid LoginDto loginDto //유저 정보를 Body로 받음
            , HttpServletResponse response) {

        userService.login(loginDto);

        String payload = userService.findUser(loginDto).toString();
        String accessToken = jwtTokenProvider.createToken(payload);

        //쿠키 만드는거
        ResponseCookie cookie = ResponseCookie.from("AccessToken", "Bearer+" + accessToken) //인코딩, 쿠키이름과 쿠키값을 지정한다
                .maxAge(Duration.ofMillis(1800000)) //쿠키의 만료시간
                .path("/")  //모든 경로에서 쿠키 전달 가능
                .build();
        response.addHeader("set-cookie", cookie.toString());//set-cookie로 헤더에 쿠키를 출력함

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "로그인 완료:"
        ), HttpStatus.OK);
    }


    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<ResponseDto<Void>> logout(final HttpServletResponse response, @AuthenticatedUser User user) {
        ResponseCookie cookie = ResponseCookie.from("AccessToken", null) //인코딩, 쿠키이름과 쿠키값을 지정한다
                .maxAge(0) //쿠키의 만료시간
                .path("/")  //모든 경로에서 쿠키 전달 가능
                .build();
        response.addHeader("set-cookie", cookie.toString());//set-cookie로 헤더에 쿠키를 출력함

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "로그 아웃 완료"
        ), HttpStatus.OK);
    }


    //회원 가입
    @PostMapping("/add")
    public ResponseEntity<ResponseDto<Void>> addUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        userService.userAdd(userCreateDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.CREATED,
                "회원 가입 완료"
        ), HttpStatus.CREATED);
    }

}
