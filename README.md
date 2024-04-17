비즈니스 로직
1.모든 메모 조회
2.메모 작성
3.자신이 작성한 메모를 전체 조회
4.메모 ID를 통해 조회
5.자신이 작성한 메모를 메모 ID를 통해 삭제
6.자신이 작성한 메모를 메모 ID를 통해 수정할 수 있다

API설계
1. 모든 메모 조회
	a.모든 메모를 조회니까 URI는 /memo
	b.조회니까 HTTP메서드는 GET

2.메모 작성
	a.메모를 작성하니까 URI는 /memo
	b.작성이니까 HTTP메서드는 POST

3. 자신이 작성한 메모를 userId를 통해 모두 조회
	a.자신이 작성한 모든 메모를 조회니까 URI는 memo/user/{userId}
	b.조회니까 HTTP메서드는 GET

4. 자신이 작성한 메모를  memoID를 통해 조회
	a. 메모ID를 통해 조회니까 URI는 /memo/{userId}/{memoId} 
	b.조회니까 HTTP메서드는 GET

5. 작성한 메모를 memoID를 통해 삭제
	a.ID를 통해서 삭제니까 URI는 /memo/{userId}/{memoId}
	b.삭제니까 HTTP메서드는 DELETE

6. 자신이 작성한 메모를 메모 ID를 통해 수정
	a.메모 아이디를 통해 수정이니까 URI는 /memo/{memoId}
	b.수정이니까 HTTP는 PETCH
