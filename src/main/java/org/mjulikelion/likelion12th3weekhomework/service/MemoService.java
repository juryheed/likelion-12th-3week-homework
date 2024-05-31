package org.mjulikelion.likelion12th3weekhomework.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.request.memo.MemoCreateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.request.memo.MemoUpdateDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.like.LikeListResponseData;
import org.mjulikelion.likelion12th3weekhomework.dto.response.like.LikeResponseData;
import org.mjulikelion.likelion12th3weekhomework.dto.response.memo.MemoListResponseData;
import org.mjulikelion.likelion12th3weekhomework.dto.response.memo.MemoResponseData;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.ConflictException;
import org.mjulikelion.likelion12th3weekhomework.error.exception.ForbiddenException;
import org.mjulikelion.likelion12th3weekhomework.error.exception.NotFoundException;
import org.mjulikelion.likelion12th3weekhomework.model.Memo;
import org.mjulikelion.likelion12th3weekhomework.model.MemoLike;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.repository.MemoLikeRepository;
import org.mjulikelion.likelion12th3weekhomework.repository.MemoRepository;
import org.mjulikelion.likelion12th3weekhomework.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.mjulikelion.likelion12th3weekhomework.error.ErrorCode.ALREADY_LIKE;

@Service
@AllArgsConstructor
public class MemoService {

    private final MemoLikeRepository memolikeRepository;
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final MemoLikeRepository memoLikeRepository;

    //메모 추가
    public void addMemo(User user, MemoCreateDto memoCreateDto) {
        Memo newMemo = Memo.builder()
                .content(memoCreateDto.getContent())
                .title(memoCreateDto.getTitle())
                .user(user)
                .build();

        memoRepository.save(newMemo);
    }

    //자신의 메모 전체 조회
    public MemoListResponseData getMemoAllByUserId(User user) {
        List<Memo> memoList = memoRepository.findAllByUser(user);

        List<MemoResponseData> memolist = new LinkedList<>();
        for (Memo m : memoList) {
            MemoResponseData memoResponseData = MemoResponseData.builder()
                    .title(m.getTitle())
                    .content(m.getContent())
                    .build();
            memolist.add(memoResponseData);
        }

        MemoListResponseData memoListResponseData = MemoListResponseData.builder()
                .memoList(memolist)
                .build();

        return memoListResponseData;
    }

    //메모 아이디로 메모 조회
    public MemoResponseData getMemoByMemoId(User user, UUID memoId) {
        Memo memo = findExistMemo(memoId);
        MemoResponseData memoResponseData = MemoResponseData.builder()
                .title(memo.getTitle())
                .content(memo.getContent())
                .build();

        return memoResponseData;
    }


    //메모 아이디로 메모 삭제
    public void deleteMemoByMemoId(User user, UUID memoId) {
        Memo memo = findExistMemo(memoId);
        findAccess(user, memo);

        memoRepository.delete(memo);
    }

    //메모아이디로 메모 업데이트
    public void updateMemoByMemoId(User user, UUID memoId, MemoUpdateDto memoUpdateDto) {
        Memo memo = findExistMemo(memoId);
        findAccess(user, memo);

        memo.setContent(memoUpdateDto.getContent());
        memoRepository.save(memo);
    }

    //좋아요 추가 기능
    public void likeAdd(User user, UUID memoId) {
        Memo memo = findExistMemo(memoId);
        MemoLike memoLike = memoLikeRepository.findByUserAndMemo(user, memo);
        //이미 좋아요를 눌렀는지 검사
        if (memoLike != null) {
            throw new ConflictException(ALREADY_LIKE);
        }

        MemoLike like = MemoLike.builder()
                .memo(memo)
                .user(user)
                .build();
        memolikeRepository.save(like);
    }

    //memoId로 좋아요 정보 확인
    public LikeListResponseData getLikeInfo(User user, UUID memoId) {
        Memo memo = findExistMemo(memoId);

        List<MemoLike> memoList = memolikeRepository.findAllByMemo(memo);   //이 메모에 대한 모든 좋아요를 불러옴

        List<LikeResponseData> likeList = new LinkedList<>();
        for (MemoLike l : memoList) {
            LikeResponseData likeResponseData = LikeResponseData.builder()
                    .name(l.getUser().getUserName())  //userId로 유저를 찾고 그 유조의 이름을 추출
                    .build();
            likeList.add(likeResponseData);
        }

        LikeListResponseData likeListResponseData = LikeListResponseData.builder()
                .likeList(likeList)
                .build();

        return likeListResponseData;
    }

    //memoId로 memo찾기
    private Memo findExistMemo(UUID memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(() -> new NotFoundException(ErrorCode.MEMO_NOT_FOUND));
        return memo;
    }

    //헤더로 받은 유저아이디와 접근할 메모의 작성자 아이디가 동일한지 검사
    private void findAccess(User user, Memo memo) {
        if (!(user.getId().equals(memo.getUser().getId()))) {
            throw new ForbiddenException(ErrorCode.CANT_ACCESS);
        }
    }
}
