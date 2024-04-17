package org.mjulikelion.likelion12th3weekhomework.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mjulikelion.likelion12th3weekhomework.memo.Memo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
@Repository
public class MemoRepository {

    //Memo객체를 담는 memoList이름을 가진 리스트를 생성한다.
    private final List<Memo> memoList = new ArrayList<>();

    //모든 메모 조회
    public List<Memo> getAllMemo(){
        return memoList;   //메모리스트 전체 조회
    }

    //메모 작성
    public void addMemo(Memo memo){
        this.memoList.add(memo);
    }




    //userId가 동일한 메모를 모두 조회
    public List<Memo> getMemoAllByUserId(String userId) {
        List<Memo> resultMemo=
                memoList.stream().filter(memo-> memo.getUserId().equals(userId))
                        .collect(Collectors.toList());
        if(resultMemo.isEmpty()){
            new IllegalArgumentException("없어용...");
        }
        return resultMemo;
    }



    // memoID를 통해 특정 메모만 조회
    public List<Memo> getMemoByMemoId(String userId,int memoId){
        List<Memo> resultMemo=
                memoList.stream().filter(memo -> memo.getMemoId()==memoId)
                    .collect(Collectors.toList());

        for (Memo m : resultMemo) {
            if (!(m.getUserId().equals(userId))) {
                throw new IllegalArgumentException("접근 제한");
            }
        }

        return resultMemo;
    }



    //memoID를 통해서 특정 메모를 삭제
    public void deleteMemoByMemoId(String userId,int memoId){
        for (Memo m : this.memoList) {    //리스트의 모든 객체에 대해서 반복, 객체를 m에 반환한다.
            if (m.getMemoId() == memoId) { //m의 MemoId가 입력받은 id와 동일하다면 memoList에서 삭제한다.
                this.memoList.remove(m);

                if (!(m.getUserId().equals(userId))) {
                    throw new IllegalArgumentException("접근 제한");
                }
                return;
            }
        }
    }


    //메모ID를 통해서 특정 메모 수정
    public void updateMemoByMemoId(String userId,int memoId,Memo memo){
        for (Memo m : this.memoList) {
            if (m.getMemoId() == memoId) {
                this.memoList.remove(m);
                this.memoList.add(memo);

                if (!(m.getUserId().equals(userId))) {
                    throw new IllegalArgumentException("접근 제한");
                }

                return;
            }
        }
    }
}
