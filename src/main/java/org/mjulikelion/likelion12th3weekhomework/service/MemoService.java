package org.mjulikelion.likelion12th3weekhomework.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.request.memo.MemoCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.memo.MemoUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.like.LikeListResponseData;
import org.mjulikelion.likelion12th3weekhomework.dto.response.memo.MemoListResponseData;
import org.mjulikelion.likelion12th3weekhomework.dto.response.memo.MemoResponseData;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.CantAccessExeption;
import org.mjulikelion.likelion12th3weekhomework.error.exception.DuplicationException;
import org.mjulikelion.likelion12th3weekhomework.error.exception.NotFoundException;
import org.mjulikelion.likelion12th3weekhomework.model.Memo;
import org.mjulikelion.likelion12th3weekhomework.model.MemoLike;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.repository.MemoLikeRepository;
import org.mjulikelion.likelion12th3weekhomework.repository.MemoRepository;
import org.mjulikelion.likelion12th3weekhomework.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.mjulikelion.likelion12th3weekhomework.error.ErrorCode.USER_DUPLICATION;

@Service
@AllArgsConstructor
public class MemoService {

    private final MemoLikeRepository memolikeRepository;
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final MemoLikeRepository memoLikeRepository;

    //메모 추가
    public void addMemo(MemoCreateDto memoCreateDto, UUID userId) {
        User user = findExistUser(userId);

        Memo newMemo = Memo.builder()
                .content(memoCreateDto.getContent())
                .title(memoCreateDto.getTitle())
                .user(user)
                .build();

        memoRepository.save(newMemo);
    }

    //유저아이디로 메모 조회
    public MemoListResponseData getMemoAllByUserId(UUID userId) {
        User user = findExistUser(userId);

        List<Memo> memoList = memoRepository.findAllByUser(user);

        MemoListResponseData memoListResponseData = MemoListResponseData.builder()
                .memoList(memoList)
                .build();

        return memoListResponseData;
    }

    //메모 아이디로 메모 조회
    public MemoResponseData getMemoByMemoId(UUID userId, UUID memoId) {
        User user = findExistUser(userId);
        Memo memo = findExistMemo(memoId);

        findAccess(userId, memo);

        MemoResponseData memoResponseData = MemoResponseData.builder()
                .memo(memo)
                .build();

        return memoResponseData;
    }


    //메모 아이디로 메모 삭제
    public void deleteMemoByMemoId(UUID userId, UUID memoId) {
        Memo memo = findExistMemo(memoId);

        findAccess(userId, memo);

        memoRepository.delete(memo);
    }

    //메모아이디로 메모 업데이트
    public void updateMemoByMemoId(UUID userId, UUID memoId, MemoUpdateDto memoUpdateDto) {
        User user = findExistUser(userId);
        Memo memo = findExistMemo(memoId);

        findAccess(userId, memo);

        Memo newMemo = Memo.builder()
                .content(memoUpdateDto.getContent())
                .title(memo.getTitle())
                .user(user)
                .build();

        memoRepository.save(newMemo);
    }

    //좋아요 추가 기능
    public void likeAdd(UUID userId, UUID memoId) {
        User user = findExistUser(userId);
        Memo memo = findExistMemo(memoId);

        //이미 좋아요를 눌렀는지 검사
        if (memoLikeRepository.findByUserAndMemo(user, memo)) {
            throw new DuplicationException(USER_DUPLICATION);
        }
        MemoLike like = MemoLike.builder()
                .memo(memo)
                .user(user)
                .build();

        memolikeRepository.save(like);
    }

    //좋아요 정보 확인
    public LikeListResponseData getLikeInfo(UUID userId, UUID memoId) {
        User user = findExistUser(userId);
        List<MemoLike> memoLike = memolikeRepository.findAllByUser(user);

        LikeListResponseData likeListResponseData = LikeListResponseData.builder()
                .like(memoLike)
                .build();

        return likeListResponseData;
    }

    //userid로 user찾기
    private User findExistUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        return user;
    }

    //memoId로 memo찾기
    private Memo findExistMemo(UUID memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(() -> new NotFoundException(ErrorCode.MEMO_NOT_FOUND));
        return memo;
    }

    //헤더로 받은 유저아이디와 접근할 메모의 작성자 아이디가 동일한지 검사
    private void findAccess(UUID userId, Memo memo) {
        if (!(userId.equals(memo.getId()))) {
            new CantAccessExeption(ErrorCode.CANT_ACCESS);
        }
    }


}
