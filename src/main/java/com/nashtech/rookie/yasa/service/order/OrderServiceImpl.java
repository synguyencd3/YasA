package com.nashtech.rookie.yasa.service.order;

import com.nashtech.rookie.yasa.dto.request.CreateOrderDto;
import com.nashtech.rookie.yasa.dto.response.OrderDto;
import com.nashtech.rookie.yasa.entity.Cart;
import com.nashtech.rookie.yasa.entity.Order;
import com.nashtech.rookie.yasa.entity.OrderDetail;
import com.nashtech.rookie.yasa.exceptions.NotFoundException;
import com.nashtech.rookie.yasa.mapper.CartToOrderMapper;
import com.nashtech.rookie.yasa.mapper.OrderMapper;
import com.nashtech.rookie.yasa.repository.CartDetailRepository;
import com.nashtech.rookie.yasa.repository.CartRepository;
import com.nashtech.rookie.yasa.repository.OrderDetailRepository;
import com.nashtech.rookie.yasa.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public OrderDto createOrder(int cartId, CreateOrderDto dto) {


        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Cart not found"));
        Order order = OrderMapper.INSTANCE.toEntity(dto);//new Order();
        order.setUser(cart.getUser());

        //suppose to set other fields

        order.setStatus("pending");
        order.setTotal(cart.getTotal());

        order = orderRepository.save(order);
        Order finalOrder = order;
        Set<OrderDetail> orderDetails = cartDetailRepository.findByCartId(cart.getId())
                .stream().map(orderDetail ->CartToOrderMapper.INSTANCE.convert(orderDetail, finalOrder.getId(), finalOrder)).collect(Collectors.toSet());
        order.setProducts(orderDetails);

        orderDetailRepository.saveAll(orderDetails);
        cartDetailRepository.deleteByCartId(cartId);
        return OrderMapper.INSTANCE.toDto(order);

    }

    @Override
    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream().map(OrderMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrder(int orderId) {
        return orderRepository.findById(orderId).map(OrderMapper.INSTANCE::toDto).orElseThrow(() -> new NotFoundException("Order not found"));
    }
}
