package org.mjulikelion.likelion12th3weekhomework.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.UserDuplicationException;
import org.mjulikelion.likelion12th3weekhomework.error.exception.UserNotFoundException;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Getter
@AllArgsConstructor
@Repository
public class UserRepository {
    private final List<User> userList = new LinkedList<>();

    public void userAdd(User user) {
        for (User u : this.userList) {
            if (u.getUserId().equals(user.getUserId())) {
                //이미 존재하는 이이디인 경우
                throw new UserDuplicationException(ErrorCode.USER_DUPLICATION);
            }
        }
        userList.add(user);
    }

    //유저 정보 업데이트
    public void userUpdate(String userId, User user) {
        for (User u : this.userList) {
            if (u.getUserId().equals(userId)) {
                userList.remove(user);
                userList.add(user);
            }
            //아이디끼리 일치하는게 없는 경우
            //throw new IllegalArgumentException("일치하는 유저 ID가 존재하지 않습니다.");
            throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND);
        }
    }

    //유저 삭제
    public void userDelete(User user) {
        for (User u : this.userList) {
            if (u.getUserId().equals(user.getUserId())) {
                userList.remove(user);
            }
            //일치하는 아이디가 없는 경우
            throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND);
        }
    }
}
