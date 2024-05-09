package org.mjulikelion.likelion12th3weekhomework.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.request.memo.MemoCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.memo.MemoUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.memo.MemoListResponseData;
import org.mjulikelion.likelion12th3weekhomework.dto.response.memo.MemoResponseData;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.MemoNotFoundException;
import org.mjulikelion.likelion12th3weekhomework.error.exception.UserNotFoundException;
import org.mjulikelion.likelion12th3weekhomework.model.Memo;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.repository.MemoRepository;
import org.mjulikelion.likelion12th3weekhomework.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final UserRepository userRepository;

    //메모 추가
    public void addMemo(MemoCreateDto memoCreateDto, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
        Memo newMemo = Memo.builder()
                .content(memoCreateDto.getContent())
                .title(memoCreateDto.getTitle())
                .user(user)
                .build();

        memoRepository.save(newMemo);
    }

    //유저아이디로 메모 조회
    public MemoListResponseData getMemoAllByUserId(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
        List<Memo> memoList = memoRepository.findAllByUser(user);

        MemoListResponseData memoListResponseData = MemoListResponseData.builder()
                .memoList(memoList)
                .build();

        return memoListResponseData;
    }

    //메모 아이디로 메모 조회
    public MemoResponseData getMemoByMemoId(UUID userId, UUID memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(() -> new MemoNotFoundException(ErrorCode.MEMO_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));

        MemoResponseData memoResponseData = MemoResponseData.builder()
                .memo(memo)
                .build();

        if (!(userId.equals(memo.getUser().getId()))) {
            throw new UserNotFoundException(ErrorCode.MEMO_NOT_FOUND);
        }
        return memoResponseData;
    }


    //메모 아이디로메모 삭제
    public void deleteMemoByMemoId(UUID userId, UUID memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(() -> new MemoNotFoundException(ErrorCode.MEMO_NOT_FOUND));

        if (!(userId.equals(memo.getUser().getId()))) {
            throw new UserNotFoundException(ErrorCode.MEMO_NOT_FOUND);
        }
        memoRepository.delete(memo);
    }

    //메모아이디로 메모 업데이트
    public void updateMemoByMemoId(UUID userId, UUID memoId, MemoUpdateDto memoUpdateDto) {

        Memo memo = memoRepository.findById(memoId).orElseThrow(() -> new MemoNotFoundException(ErrorCode.MEMO_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));

        Memo newMemo = Memo.builder()
                .content(memoUpdateDto.getContent())
                .title(memo.getTitle())
                .user(user)
                .build();

        memoRepository.save(newMemo);
    }
}
