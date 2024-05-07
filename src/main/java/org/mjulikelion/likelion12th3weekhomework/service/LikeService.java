package org.mjulikelion.likelion12th3weekhomework.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.LikeAddDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.LikeListResponseData;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.UserNotFoundException;
import org.mjulikelion.likelion12th3weekhomework.model.MemoLike;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.mjulikelion.likelion12th3weekhomework.repository.MemoLikeRepository;
import org.mjulikelion.likelion12th3weekhomework.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class LikeService {

    private final MemoLikeRepository memolikeRepository;
    private final UserRepository userRepository;

    //좋아요 추가 기능
    public void likeAdd(LikeAddDto likeAddDto) {
        MemoLike like = MemoLike.builder()
                .memo(likeAddDto.getMemo())
                .user(likeAddDto.getUser())
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
