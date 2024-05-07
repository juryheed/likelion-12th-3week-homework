package org.mjulikelion.likelion12th3weekhomework.controller;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.JoinDto;
import org.mjulikelion.likelion12th3weekhomework.dto.ResponseDto;
import org.mjulikelion.likelion12th3weekhomework.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("organizations")
public class OrganizationController {

    private final OrganizationService organizationService;


    @PostMapping
    public ResponseEntity<ResponseDto<Void>> make(@RequestHeader UUID userId, @RequestBody JoinDto joinDto) {
        organizationService.make(userId, joinDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.CREATED,
                "Susccess"
        ), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> join(@PathVariable UUID id, @RequestHeader UUID userId) {
        organizationService.join(id, userId);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.CREATED,
                "Susccess"
        ), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> exit(@PathVariable UUID id, @RequestHeader UUID userId) {
        organizationService.exit(id, userId);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "Susccess"
        ), HttpStatus.OK);
    }
}
