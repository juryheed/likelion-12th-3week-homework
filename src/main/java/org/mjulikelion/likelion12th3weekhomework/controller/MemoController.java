package org.mjulikelion.likelion12th3weekhomework.controller;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.memo.Memo;
import org.mjulikelion.likelion12th3weekhomework.service.MemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//컨트롤러 클래스를 정의
@AllArgsConstructor//생성자를 자동으로 생성
public class MemoController {

    private final MemoService memoService;  //MemoService의 객체를 주입받아 이 클래스에서 사용할수 있게 한다.

    //모든 메모 조회
    @GetMapping("/memo")    //조회니까 GET
    public List<Memo> getAllMemo(){ //
        return memoService.getAllMemo();
    }

    //메모 작성
    @PostMapping("/memo")
    public void addMemo(@RequestBody Memo memo){    //HTTP요청의 본분에 있는데이터를 메소드의 파라미터로 메핑
        memoService.addMemo(memo);

    }

    //자신이 작성한 메모를 전체 조회
    @GetMapping("/memo/user/{userId}")
    public List<Memo> getMemoAllByUserId(@PathVariable String userId){  //HTTP경로에서 추출한 값을 메서드 파라미터로 전달
        return memoService.getMemoAllByUserId(userId);
    }

    //자신이 작성한 메모를 메모 ID를 통해서 조회
    @GetMapping("/memo/{userId}/{memoId}")      //조회니까GET
    public List<Memo> getMemoByMemoId(@PathVariable String userId,@PathVariable int memoId){
        return memoService.getMemoByMemoId(userId,memoId);
    }

    //자신이 작성한 메모를 메모 ID를 통해서a 삭제
    @DeleteMapping("/memo/{userId}/{memoId}")   //삭제니까 DELETE
    public void deleteMemoByMemoId(@PathVariable String userId,@PathVariable int memoId){
        memoService.deleteMemoByMemoId(userId,memoId);
    }

    //자신이 작성한 메모를 메모 ID를 통해 수정
    @PatchMapping("/memo/{userId}/{memoId}")  //수정이니까 PATCH
    public void updateMemoByMemoId(@PathVariable String userId,@PathVariable int memoId,@RequestBody Memo memo ){
        memoService.updateMemoByMemoId(userId,memoId,memo);
    }


}
