package org.mjulikelion.likelion12th3weekhomework.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.request.memo.MemoCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.memo.MemoUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.ResponseDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.memo.MemoListResponseData;
import org.mjulikelion.likelion12th3weekhomework.dto.response.memo.MemoResponseData;
import org.mjulikelion.likelion12th3weekhomework.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController//컨트롤러 클래스를 정의
@AllArgsConstructor//생성자를 자동으로 생성
@RequestMapping("memos")
public class MemoController {

    private final MemoService memoService;  //MemoService의 객체를 주입받아 이 클래스에서 사용할수 있게 한다.

    //메모 작성
    @PostMapping
    public ResponseEntity<ResponseDto<Void>> addMemo(@RequestBody @Valid MemoCreateDto memoCreateDto, @RequestHeader("userId") UUID userId) {//HTTP요청의 본분에 있는데이터를 메소드의 파라미터로 메핑
        memoService.addMemo(memoCreateDto, userId);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.CREATED,
                "Success"
        ), HttpStatus.CREATED);
    }

    //유저 아이디로 메모 전체 조회
    @GetMapping
    public ResponseEntity<ResponseDto<MemoListResponseData>> getMemoAllByUserId(@RequestHeader("userId") UUID userId) {  //UserId를 Header로 받기
        MemoListResponseData memoListResponseData = memoService.getMemoAllByUserId(userId);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "Success",
                memoListResponseData
        ), HttpStatus.OK);

    }

    //자신이 작성한 메모를 메모 ID를 통해서 조회
    @GetMapping("/{id}")      //조회니까GET
    public ResponseEntity<ResponseDto<MemoResponseData>> getMemoByMemoId(@RequestHeader("userId") UUID userId, @RequestHeader("memoId") UUID memoId) {
        MemoResponseData memoResponseData = memoService.getMemoByMemoId(userId, memoId);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,  //응답 상태 코드
                "Success",      //응답 메세지
                memoResponseData//응답 데이터
        ), HttpStatus.OK);
    }

    //자신이 작성한 메모를 메모 ID를 통해서 삭제
    @DeleteMapping  //삭제니까 DELETE
    public ResponseEntity<ResponseDto<Void>> deleteMemoByMemoId(@RequestHeader("uerId") UUID userId, @RequestHeader("memoId") UUID memoId) {
        memoService.deleteMemoByMemoId(userId, memoId);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "Success"//응답 데이터가 없음
        ), HttpStatus.OK);
    }

    //자신이 작성한 메모를 메모 ID를 통해 수정
    @PatchMapping("/{id}")  //수정이니까 PATCH
    public ResponseEntity<ResponseDto<Void>> updateMemoByMemoId(@RequestHeader("userId") UUID userId, @RequestHeader("memoId") UUID memoId, @RequestBody @Valid MemoUpdateDto memoUpdateDto) {
        memoService.updateMemoByMemoId(userId, memoId, memoUpdateDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "Success"//응답 데이터 없음
        ), HttpStatus.OK);
    }


}
