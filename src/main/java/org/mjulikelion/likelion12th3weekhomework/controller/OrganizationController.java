package org.mjulikelion.likelion12th3weekhomework.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.request.organizaion.OrganizationCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.ResponseDto;
import org.mjulikelion.likelion12th3weekhomework.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;


    //조직 생성
    @PostMapping
    public ResponseEntity<ResponseDto<Void>> make(@RequestHeader("userId") UUID userId, @RequestBody @Valid OrganizationCreateDto organizationCreateDto) {
        organizationService.make(userId, organizationCreateDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.CREATED,
                "Susccess"
        ), HttpStatus.CREATED);
    }

    //조직 가입
    @PostMapping("/join")
    public ResponseEntity<ResponseDto<Void>> join(@RequestHeader("organizationId") UUID organizationId, @RequestHeader("userId") UUID userId) {
        organizationService.join(organizationId, userId);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.CREATED,
                "Susccess"
        ), HttpStatus.CREATED);
    }

    //조직 탈퇴
    @DeleteMapping("/exit")
    public ResponseEntity<ResponseDto<Void>> exit(@RequestHeader("organizationId") UUID organizationId, @RequestHeader("userId") UUID userId) {
        organizationService.exit(organizationId, userId);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "Susccess"
        ), HttpStatus.OK);
    }
}
