package org.mjulikelion.likelion12th3weekhomework.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.dto.LikeAddDto;
import org.mjulikelion.likelion12th3weekhomework.dto.response.LikeListResponseData;
import org.mjulikelion.likelion12th3weekhomework.model.Like;
import org.mjulikelion.likelion12th3weekhomework.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    //좋아요 추가 기능
    public void likeAdd(LikeAddDto likeAddDto) {

        LocalDateTime nowTime = LocalDateTime.now();
        Like like = Like.builder()
                .name(likeAddDto.getName())
                .memoId(likeAddDto.getMemoId())
                .time(nowTime)
                .build();

        likeRepository.likeAdd(like);
    }

    //좋아요 정보 확인
    public LikeListResponseData getLikeInfo(int memoId) {
        List<Like> result = LikeRepository.likeInfo(memoId);
        LikeListResponseData likeListResponseData = LikeListResponseData.builder().like(result).build();
        return new LikeListResponseData(result);
    }

}
