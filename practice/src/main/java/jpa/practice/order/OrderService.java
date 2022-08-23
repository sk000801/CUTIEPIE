package jpa.practice.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void join(Order order) {
        orderRepository.join(order);
    }

    public Order findId(String id) {
        return orderRepository.findId(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }
}
