package jpa.practice.member;

import jpa.practice.order.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class MemberOrderDTO {

    private final List<Order> memberList;

    public MemberOrderDTO(Member member) {
        this.memberList = member.getPostOrder();
    }
}
