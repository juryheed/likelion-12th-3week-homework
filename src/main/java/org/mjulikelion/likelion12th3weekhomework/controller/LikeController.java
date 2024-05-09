package org.mjulikelion.likelion12th3weekhomework.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.request.like.LikeAddDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.ResponseDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.like.LikeListResponseData;
import org.mjulikelion.likelion12th3weekhomework.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("likes")
public class LikeController {

    private final LikeService likeService;

    //좋아요 추가하기
    @PostMapping    //좋아요 누리기니까 Post
    public ResponseEntity<ResponseDto<Void>> addLike(@RequestBody @Valid LikeAddDto likeAddDto) {
        likeService.likeAdd(likeAddDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.CREATED,
                "Success"
        ), HttpStatus.CREATED);
    }

    //좋아요 정보 보기
    @GetMapping//좋아요 조회니까 Get
    public ResponseEntity<ResponseDto<LikeListResponseData>> getLikeListByMemoId(@RequestHeader UUID userId, @RequestHeader UUID memoId) {
        LikeListResponseData likeListResponseData = likeService.getLikeInfo(userId, memoId);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "Success",
                likeListResponseData
        ), HttpStatus.OK);
    }
}
