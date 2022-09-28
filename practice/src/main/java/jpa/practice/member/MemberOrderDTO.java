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
    public MemberOrderDTO(String id, OrderService orderService) {
        this.memberOrders = orderService.memberOrderList(id);
    }
}
