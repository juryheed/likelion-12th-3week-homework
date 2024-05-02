package org.mjulikelion.likelion12th3weekhomework.controller;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.LikeAddDto;
import org.mjulikelion.likelion12th3weekhomework.dto.ResponseDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.LikeListResponseData;
import org.mjulikelion.likelion12th3weekhomework.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("like")
public class LikeController {

    private final LikeService likeService;

    @PostMapping    //좋아요 누리기니까 Post
    public ResponseEntity<ResponseDto<Void>> addLike(@RequestBody LikeAddDto likeAddDto) {
        likeService.likeAdd(likeAddDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.CREATED,
                "Success"
        ), HttpStatus.CREATED);
    }

    @GetMapping("/{Id}")//좋아요 조회니까 Get
    public ResponseEntity<ResponseDto<LikeListResponseData>> getLikeListByMemoId(@PathVariable int memoId) {
        likeService.getLikeInfo(memoId);
        LikeListResponseData likeListResponseData = likeService.getLikeInfo(memoId);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "Success",
                likeListResponseData
        ), HttpStatus.OK);
    }
}
