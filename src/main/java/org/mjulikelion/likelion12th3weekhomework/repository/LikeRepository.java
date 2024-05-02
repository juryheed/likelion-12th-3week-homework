package org.mjulikelion.likelion12th3weekhomework.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mjulikelion.likelion12th3weekhomework.model.Like;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Getter
@Repository
@AllArgsConstructor
public class LikeRepository {
    //좋아요 리스트
    private static final List<Like> likeList = new LinkedList<>();

    //좋아요 확인 기능
    public static List<Like> likeInfo(int memoId) {
        List<Like> result = new LinkedList<>();
        for (Like li : likeList) {
            if (li.getMemoId() == memoId) {
                result.add(li);
            }
        }
        System.out.println("해당 메모의 누적 좋아요 수: " + result.size());
        return result;
    }

    //좋아요 추가기능
    public void likeAdd(Like like) {
        likeList.add(like);
    }


}
