package in.nikam.creatorstore.controllers;

import in.nikam.creatorstore.dto.OrderRequest;
import in.nikam.creatorstore.entities.Order;
import in.nikam.creatorstore.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    //Get all orders
    public List<Order> getAllOrders() {
        return null;
    }

    //Get Order by id
    public Order getOrderById() {
        return null;
    }

}
