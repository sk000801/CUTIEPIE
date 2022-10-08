## 협업을 위해 작성해보는 API 명세서(?)

#### Member
```(java)
- Post (/members/add)
  //form(member의 id, pw, 이름(닉네임))을 받아와야함  

- Get  (/member/orders)
// 멤버의 주문 목록
```
#### Product
```(java)
- Post (/admins/pManage/join) ※boom※
  //form(상품이름, 재고, 가격, 카테고리, 상세정보, 이미지)을 받아와야함 
  
- Get (/products/list)
  //전체 상품 리스트 목록

- Get  (/products/image/{이미지id->상품dB에 저장돼있음})
//상품 이미지를 직접 반환


//추후 추가 예정 (나머지 기능이 다시 보니 빈약)
```
#### Order
```(java)
- Post (/orders/join/{상품id})
// 상품 상세 페이지에서 바로 주문으로 넘어갈 때

- Post (/orders/join/basket)
//멤버의 장바구니에 들어있는 상품들을 한꺼번에 주문할 때
```
#### MemberBasket
```(java)
- Post (/members/basket/{상품id}?count=개수)

- Get  (/members/basket?id=멤버장바구니꺼)
```
