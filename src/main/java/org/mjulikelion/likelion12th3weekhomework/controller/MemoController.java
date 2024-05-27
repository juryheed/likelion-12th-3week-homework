package org.mjulikelion.likelion12th3weekhomework.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.authentication.AuthenticatedUser;
import org.mjulikelion.likelion12th3weekhomework.dto.request.memo.MemoCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.memo.MemoUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.ResponseDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.like.LikeListResponseData;
import org.mjulikelion.likelion12th3weekhomework.dto.response.memo.MemoListResponseData;
import org.mjulikelion.likelion12th3weekhomework.dto.response.memo.MemoResponseData;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController//컨트롤러 클래스를 정의
@AllArgsConstructor//생성자를 자동으로 생성
@RequestMapping("/memos")
public class MemoController {

    private final MemoService memoService;  //MemoService의 객체를 주입받아 이 클래스에서 사용할수 있게 한다.

    //메모 추가
    @PostMapping
    public ResponseEntity<ResponseDto<Void>> addMemo(@AuthenticatedUser User user, @RequestBody @Valid MemoCreateDto memoCreateDto) {
        memoService.addMemo(user, memoCreateDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.CREATED,
                "메모 작성 완료"
        ), HttpStatus.CREATED);
    }

    //유저가 작성한 메모 전체 조회
    @GetMapping
    public ResponseEntity<ResponseDto<MemoListResponseData>> getMemoAllByUserId(@AuthenticatedUser User user) {
        MemoListResponseData memoListResponseData = memoService.getMemoAllByUserId(user);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "메모 전체 조회",
                memoListResponseData
        ), HttpStatus.OK);
    }

    //특정 메모 조회
    @GetMapping("/{memoid}")
    public ResponseEntity<ResponseDto<MemoResponseData>> getMemoByMemoId(@AuthenticatedUser User user, @PathVariable("memoid") UUID memoId) {
        MemoResponseData memoResponseData = memoService.getMemoByMemoId(user, memoId);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,  //응답 상태 코드
                "메모 조회 완료",      //응답 메세지
                memoResponseData//응답 데이터
        ), HttpStatus.OK);
    }

    //특정 메모 삭제
    @DeleteMapping("/{memoid}")
    public ResponseEntity<ResponseDto<Void>> deleteMemoByMemoId(@AuthenticatedUser User user, @PathVariable("memoid") UUID memoId) {
        memoService.deleteMemoByMemoId(user, memoId);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "메모 삭제 완료"
        ), HttpStatus.OK);
    }

    //특정 메모 수정
    @PatchMapping("/{memoid}")
    public ResponseEntity<ResponseDto<Void>> updateMemoByMemoId(@AuthenticatedUser User user, @PathVariable("memoid") UUID memoId, @RequestBody @Valid MemoUpdateDto memoUpdateDto) {
        memoService.updateMemoByMemoId(user, memoId, memoUpdateDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "메모 수정 완료"
        ), HttpStatus.OK);
    }

    //좋아요 추가하기
    @PostMapping("/{memoid}/likes")
    public ResponseEntity<ResponseDto<Void>> addLike(@AuthenticatedUser User user, @PathVariable("memoid") UUID memoId) {
        memoService.likeAdd(user, memoId);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.CREATED,
                "좋아요 추가 완료"
        ), HttpStatus.CREATED);
    }

    //좋아요 정보 보기
    @GetMapping("/{memoid}/likes")
    public ResponseEntity<ResponseDto<LikeListResponseData>> getLikeListByMemoId(@AuthenticatedUser User user, @PathVariable("memoid") UUID memoId) {
        LikeListResponseData likeListResponseData = memoService.getLikeInfo(user, memoId);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "좋아요 정보 확인",
                likeListResponseData
        ), HttpStatus.OK);
    }
}
