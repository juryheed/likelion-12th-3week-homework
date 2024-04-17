package org.mjulikelion.likelion12th3weekhomework.service;

import lombok.AllArgsConstructor;
import org.mjulikelion.likelion12th3weekhomework.memo.Memo;
import org.mjulikelion.likelion12th3weekhomework.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    public List<Memo> getAllMemo(){
        return memoRepository.getAllMemo();
    }

    public void addMemo(Memo memo){
        memoRepository.addMemo(memo);
    }

    public List<Memo> getMemoAllByUserId(String userId){
        //memoRepository.check(userId);
        return memoRepository.getMemoAllByUserId(userId);
    }

    public List<Memo> getMemoByMemoId(String userId,int memoId){
        //memoRepository.check(userId);
        return memoRepository.getMemoByMemoId(userId,memoId);
    }

    public void deleteMemoByMemoId(String userId,int memoId){
        //memoRepository.check(userId);
        memoRepository.deleteMemoByMemoId(userId,memoId);
    }

    public void updateMemoByMemoId(String userId,int memoId,Memo memo){
        //memoRepository.check(userId);
        memoRepository.updateMemoByMemoId(userId,memoId,memo);
    }
}
