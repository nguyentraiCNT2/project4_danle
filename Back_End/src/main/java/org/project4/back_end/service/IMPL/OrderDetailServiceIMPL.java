package org.project4.back_end.service.IMPL;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.project4.back_end.Entity.OrderDetailEntity;
import org.project4.back_end.Entity.OrderEntity;
import org.project4.back_end.Entity.ProductEntity;
import org.project4.back_end.dto.OrderDetailDTO;
import org.project4.back_end.repository.OrderDetailRepository;
import org.project4.back_end.repository.OrderRepository;
import org.project4.back_end.repository.ProductRepository;
import org.project4.back_end.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceIMPL implements OrderDetailsService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository   productRepository;
    @Override
    public int totalItem() {
        return (int) orderDetailRepository.count();
    }

    @Override
    public List<OrderDetailDTO> getAllOrderDetails(Pageable pageable) {
        List<OrderDetailDTO> results = new ArrayList<>();
        List<OrderDetailEntity> orderDetailEntities = orderDetailRepository.findAll(pageable).getContent();
        for (OrderDetailEntity item: orderDetailEntities
        ) {
            OrderDetailDTO DTO = modelMapper.map(item,OrderDetailDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public List<OrderDetailDTO> getByOrderId(Integer orderId, Pageable pageable) {
        List<OrderDetailDTO> results = new ArrayList<>();
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        List<OrderDetailEntity> orderDetailEntities = orderDetailRepository.findByOrder(orderEntity,pageable);
        for (OrderDetailEntity item: orderDetailEntities
        ) {
            OrderDetailDTO DTO = modelMapper.map(item,OrderDetailDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public OrderDetailDTO getOrderDetailsById(Integer id) {
        try {
            OrderDetailEntity orderDetail = orderDetailRepository.findByOrderDetailId(id);
            if (orderDetail == null) {
                throw new EntityNotFoundException("Category not found");
            }
            return modelMapper.map(orderDetail, OrderDetailDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public void saveOrderDetail(OrderDetailDTO orderDetailDTO) {
        if (orderDetailDTO != null){
            OrderEntity orderEntity = orderRepository.findById(orderDetailDTO.getOrder().getOrderId()).get();
            ProductEntity productEntity = productRepository.findById(orderDetailDTO.getProduct().getProductId()).get();
            OrderDetailEntity orderDetailEntity = modelMapper.map(orderDetailDTO,OrderDetailEntity.class);
            orderDetailEntity.setOrder(orderEntity);
            orderDetailEntity.setProduct(productEntity);
            orderDetailRepository.save(orderDetailEntity);
        }
    }

    @Override
    public void deleteOrderDetail(Integer id) {
    orderDetailRepository.deleteByOrderDetailId(id);
    }

    @Override
    public void updateOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetailEntity orderDetailEntity = orderDetailRepository.findByOrderDetailId(orderDetailDTO.getOrderDetailId());
        modelMapper.map(orderDetailDTO, orderDetailEntity);
            orderDetailRepository.save(orderDetailEntity);

    }
}
