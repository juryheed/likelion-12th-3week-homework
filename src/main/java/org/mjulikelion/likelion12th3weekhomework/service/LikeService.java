package org.mjulikelion.likelion12th3weekhomework.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.request.like.LikeAddDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.like.LikeListResponseData;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.MemoNotFoundException;
import org.mjulikelion.likelion12th3weekhomework.error.exception.UserNotFoundException;
import org.mjulikelion.likelion12th3weekhomework.model.Memo;
import org.mjulikelion.likelion12th3weekhomework.model.MemoLike;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.repository.MemoLikeRepository;
import org.mjulikelion.likelion12th3weekhomework.repository.MemoRepository;
import org.mjulikelion.likelion12th3weekhomework.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class LikeService {

    private final MemoLikeRepository memolikeRepository;
    private final UserRepository userRepository;
    private final MemoRepository memoRepository;

    //좋아요 추가 기능
    public void likeAdd(LikeAddDto likeAddDto) {
        Memo memo = memoRepository.findById(likeAddDto.getMemoId()).orElseThrow(() -> new MemoNotFoundException(ErrorCode.MEMO_NOT_FOUND));
        User user = userRepository.findById(likeAddDto.getUserId()).orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));

        MemoLike like = MemoLike.builder()
                .memo(memo)
                .user(user)
                .build();

        memolikeRepository.save(like);
    }

    //좋아요 정보 확인
    public LikeListResponseData getLikeInfo(UUID userId, UUID memoId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));

        List<MemoLike> memoLike = memolikeRepository.findAllByUser(user);

        LikeListResponseData likeListResponseData = LikeListResponseData.builder()
                .like(memoLike)
                .build();

        return likeListResponseData;
    }

}
