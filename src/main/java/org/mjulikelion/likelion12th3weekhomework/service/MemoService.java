package org.mjulikelion.likelion12th3weekhomework.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.MemoCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.MemoUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.MemoListResponseData;
import org.mjulikelion.likelion12th3weekhomework.dto.response.MemoResponseData;
import org.mjulikelion.likelion12th3weekhomework.model.Memo;
import org.mjulikelion.likelion12th3weekhomework.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    //메모 추가
    public void addMemo(MemoCreateDto memoCreateDto, String userId) {
        int memoId = 1;
        Memo newMemo = Memo.builder()
                .memoId(memoId)
                .content(memoCreateDto.getContent())
                .title(memoCreateDto.getTitle())
                .userId(userId).build();

        memoRepository.addMemo(newMemo);
    }

    //유저아이디로 메모 조회
    public MemoListResponseData getMemoAllByUserId(String userId) {
        List<Memo> memoList = memoRepository.getMemoAllByUserId(userId);
        MemoListResponseData memoListResponseData = MemoListResponseData.builder().memoList(memoList).build();
        return memoListResponseData;
    }

    //메모 아이디로 메모 조회
    public MemoResponseData getMemoByMemoId(String userId, int memoId) {
        Memo memo = memoRepository.getMemoByMemoId(userId, memoId);
        MemoResponseData memoResponseData = MemoResponseData.builder().memo(memo).build();
        return memoResponseData;
    }

    //메모 아이디로메모 삭제
    public void deleteMemoByMemoId(String userId, int memoId) {
        memoRepository.deleteMemoByMemoId(userId, memoId);
    }

    //메모아이디로 메모 업데이트
    public void updateMemoByMemoId(String userId, int memoId, MemoUpdateDto memoUpdateDto) {

        Memo newMemo = Memo.builder()
                .userId(userId)
                .memoId(memoId)
                .content(memoUpdateDto.getContent())
                .build();

        memoRepository.updateMemoByMemoId(newMemo);
    }
}
