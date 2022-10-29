#### CUTIEPIE 대표 밑거름들 (┬┬﹏┬┬)

1)

![이미지](./image/끄적-3.jpg)

:boom: 이게 너무 힘들었다

```java
        em.merge(product)
```
이 코드 한 줄만 수정했으면 끝날 일이었다...  
<br>
물론 새로운 상품을 등록할 때는 merge함수가 좋지 않지만 여기서는
imageStore의 내용을 옮기기 위해 미리 생성해놓은 product 빈 생성자에 내용을 set시켜놓고
join함수를 사용하려다가 발생한 문제이므로....

그 죽일놈의
```java
A different object with the same identifier value was already associated with the session
```
오류 때문에........
<br>
이 친구는 블로그에도 한번 정리할 계획이다.. 다양한 오류들을 많이 만나서..

<br>
<br>

2. 다양한 매핑 관계들
   :boom: 일대일 단방향 매핑 관계
```java
@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
@JoinColumn(name="image_id")
```
단방향이기 때문에 굳이 mappedBy를 써주지는 않았다.
<br>
다만 joincolumn에 사용되는 컬럼이 되도록이면 primary key를 사용하는 것 같다.


<br>

3. 이미지 form에서 입력받고 google cloud storage에 저장하여 url 남기기
<br>
왜 로컬에다 저장을 해야만 사진을 넘길 수 있냐고!!!!!!!!! 아직도 의문!!!!!

<br>

4. 이미지 서버에서 저장하고 배포하기
