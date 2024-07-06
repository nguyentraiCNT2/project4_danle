package org.project4.back_end.service;

import org.project4.back_end.dto.OrderDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {


    public int totalItem();

    public List<OrderDTO> getAllOrders( Pageable pageable);

    public List<OrderDTO> getByUserId(Integer userId, Pageable pageable);
    public List<OrderDTO> getByDeliveryStatus(String deliveryStatus, Pageable pageable);
    public List<OrderDTO> getByOrderStatus(String orderStatus, Pageable pageable);
    public List<OrderDTO> getByPayMentStatus(String payMentStatus, Pageable pageable);

    public OrderDTO getOrderById(Integer id);

    public void saveOrder(OrderDTO orderDTO);

    public void deleteOrder(Integer id);

    public void updateOrder(OrderDTO orderDTO);
}
