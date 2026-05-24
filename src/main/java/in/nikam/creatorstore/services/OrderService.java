package in.nikam.creatorstore.services;

import in.nikam.creatorstore.dto.OrderItemRequest;
import in.nikam.creatorstore.dto.OrderRequest;
import in.nikam.creatorstore.entities.Order;
import in.nikam.creatorstore.entities.OrderItem;
import in.nikam.creatorstore.entities.Product;
import in.nikam.creatorstore.repositories.OrderRepository;
import in.nikam.creatorstore.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    //this creates a new record in the orders table, & we have a linking in the
    //order items & there is an update to the stock in the products table so for all
    //these sequence of events, which is more than one s ql statements , so we group
    //them into one single transaction so if anything goes wrong we roll back or commit if good
    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        Order order = new Order();
        order.setCustomerName(orderRequest.getCustomerName());
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setStatus("CONFIRMED");

        for(OrderItemRequest itemRequest : orderRequest.getItems()){
            Product product = productRepository.findById(
                    itemRequest.getProductId()
            ).orElseThrow(() -> new RuntimeException(
                    "product not found with" + itemRequest.getProductId()
            ));

            //Check the product stock
            if (product.getStockQuantity() < itemRequest.getQuantity()){
                throw new RuntimeException("Not enough stock for " + itemRequest.getProductId());
            }

            // Calculate Total Price
            BigDecimal priceOfItem = product.getPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            totalPrice = totalPrice.add(priceOfItem);

            // Update the product table with latest stock quantity
            product.setStockQuantity(product.getStockQuantity() - itemRequest.getQuantity()
            );
            productRepository.save(product);

            // Builder pattern to make obj
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .priceAtPurchase(product.getPrice())
                    .build();

            orderItems.add(orderItem);
        }

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);

        return orderRepository.save(order);
    }
}