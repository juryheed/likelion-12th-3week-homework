package org.mjulikelion.likelion12th3weekhomework.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mjulikelion.likelion12th3weekhomework.error.ErrorCode;
import org.mjulikelion.likelion12th3weekhomework.error.exception.CantAccessExeption;
import org.mjulikelion.likelion12th3weekhomework.error.exception.MemoNotFoundException;
import org.mjulikelion.likelion12th3weekhomework.model.Memo;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Getter
@AllArgsConstructor
@Repository
public class MemoRepository {

    //Memo객체를 담는 memoList이름을 가진 리스트를 생성한다.
    private final List<Memo> memoList = new LinkedList<>();


    //메모 작성
    public void addMemo(Memo memo) {
        this.memoList.add(memo);
    }

    //userId가 동일한 메모를 모두 조회
    public List<Memo> getMemoAllByUserId(String userId) {
        List<Memo> result = new LinkedList<>();
        for (Memo m : this.memoList) {
            if (m.getUserId().equals(userId)) {
                result.add(m);
            }
        }
        return result;
    }


    // memoID를 통해 특정 메모만 조회
    public Memo getMemoByMemoId(String userId, int memoId) {
        for (Memo m : this.memoList) {
            if (m.getMemoId() == memoId) {
                if (m.getUserId().equals(userId)) {
                    return m;
                }
                //접근할 수 없는 경우
                throw new CantAccessExeption(ErrorCode.CANT_ACCESS);
            }
        }
        //일치하는 메모 아이디가 없는 경우
        throw new MemoNotFoundException(ErrorCode.MEMO_NOT_FOUND);
    }


    //memoID를 통해서 특정 메모를 삭제
    public void deleteMemoByMemoId(String userId, int memoId) {
        for (Memo m : this.memoList) {
            if (m.getMemoId() == memoId) {
                if (m.getUserId().equals(userId)) {
                    memoList.remove(m);
                }
                //접근할 수 없는 경우
                throw new CantAccessExeption(ErrorCode.CANT_ACCESS);
            }
        }
        //일치하는 메모 아이디가 없는 경우
        throw new MemoNotFoundException(ErrorCode.MEMO_NOT_FOUND);
    }


    //메모ID를 통해서 특정 메모 수정
    public void updateMemoByMemoId(Memo memo) {
        for (Memo m : this.memoList) {
            if (m.getMemoId() == memo.getMemoId()) {
                if (m.getUserId().equals(memo.getUserId())) {
                    memoList.remove(m);
                    memoList.add(memo);
                }
                //접슨할수 없는 경우
                throw new CantAccessExeption(ErrorCode.CANT_ACCESS);
            }
        }
        //일치하는 메모 아이디가 없는 경우
        throw new MemoNotFoundException(ErrorCode.MEMO_NOT_FOUND);
    }
}
