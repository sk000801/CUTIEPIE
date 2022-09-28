package jpa.practice.member;

import jpa.practice.order.Order;
import jpa.practice.order.OrderService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MemberOrderDTO {
    private List<Order> memberOrders;
    public MemberOrderDTO(Member member, OrderService orderService) {
        this.memberOrders = orderService.memberOrderList(member.getId());
    }
}
