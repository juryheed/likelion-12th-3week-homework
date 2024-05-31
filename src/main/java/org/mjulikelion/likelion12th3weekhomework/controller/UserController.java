package org.mjulikelion.likelion12th3weekhomework.controller;

import jakarta.servlet.http.HttpServletResponse;
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
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    //유저 이름 수정
    @PatchMapping("/rename")
    public ResponseEntity<ResponseDto<Void>> userUpdate(@AuthenticatedUser User user, @RequestBody @Valid UserUpdateDto userUpdateDto) {
        userService.userUpdate(user, userUpdateDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "이름 수정 완료"
        ), HttpStatus.OK);
    }

    //비밀번호 변경
    @PatchMapping("/repassword")
    public ResponseEntity<ResponseDto<Void>> passwordUpdate(@AuthenticatedUser User user, @RequestBody @Valid PasswordUpdateDto passwordUpdateDto) {
        userService.passwordUpdate(user, passwordUpdateDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "비밀번호 수정 완료"
        ), HttpStatus.OK);
    }

    //회원 탈퇴
    @DeleteMapping
    public ResponseEntity<ResponseDto<Void>> userDelete(@AuthenticatedUser User user, HttpServletResponse response) {
        userService.userDelete(user);

        ResponseCookie cookie = ResponseCookie.from("AccessToken", null) //인코딩, 쿠키이름과 쿠키값을 지정한다
                .maxAge(0)
                .path("/")
                .build();
        response.addHeader("set-cookie", cookie.toString());//set-cookie로 헤더에 쿠키를 출력함


        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "회원 탈퇴 완료"
        ), HttpStatus.OK);
    }
}
