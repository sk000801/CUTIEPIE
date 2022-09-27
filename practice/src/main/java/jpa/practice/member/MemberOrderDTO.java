package jpa.practice.member;

import jpa.practice.order.Order;
import jpa.practice.order.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class MemberOrderDTO {
    private final List<Order> memberOrders;
    public MemberOrderDTO(Member member, OrderService orderService) {
        this.memberOrders = orderService.memberOrderList(member);
    }
}
