package org.project4.back_end.service;

import org.project4.back_end.dto.OrderDetailDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderDetailsService {
    public int totalItem();

    public List<OrderDetailDTO> getAllOrderDetails(Pageable pageable);
    public List<OrderDetailDTO> getByOrderId(Integer orderId , Pageable pageable);

    public OrderDetailDTO getOrderDetailsById(Integer id);

    public void saveOrderDetail(OrderDetailDTO orderDetailDTO);

    public void deleteOrderDetail(Integer id);

    public void updateOrderDetail(OrderDetailDTO orderDetailDTO);
}
