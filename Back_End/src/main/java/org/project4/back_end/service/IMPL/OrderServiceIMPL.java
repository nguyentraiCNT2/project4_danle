package org.project4.back_end.service.IMPL;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.project4.back_end.Entity.OrderEntity;
import org.project4.back_end.dto.OrderDTO;
import org.project4.back_end.repository.OrderRepository;
import org.project4.back_end.repository.UsersRepository;
import org.project4.back_end.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public int totalItem() {
        return (int) orderRepository.count();
    }

    @Override
    public List<OrderDTO> getAllOrders(Pageable pageable) {
        return List.of();
    }

    @Override
    public List<OrderDTO> getByUserId(Integer userId, Pageable pageable) {
        List<OrderDTO> results = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.findAll(pageable).getContent();
        for (OrderEntity item: orderEntities
        ) {
            OrderDTO DTO = modelMapper.map(item,OrderDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public List<OrderDTO> getByDeliveryStatus(String deliveryStatus, Pageable pageable) {
        List<OrderDTO> results = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.findByDeliveryStatus(deliveryStatus,pageable);
        for (OrderEntity item: orderEntities
        ) {
            OrderDTO DTO = modelMapper.map(item,OrderDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public List<OrderDTO> getByOrderStatus(String orderStatus, Pageable pageable) {
        List<OrderDTO> results = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.findByOrderStatus(orderStatus,pageable);
        for (OrderEntity item: orderEntities
        ) {
            OrderDTO DTO = modelMapper.map(item,OrderDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public List<OrderDTO> getByPayMentStatus(String payMentStatus, Pageable pageable) {
        List<OrderDTO> results = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.findByPayMentStatus(payMentStatus,pageable);
        for (OrderEntity item: orderEntities
        ) {
            OrderDTO DTO = modelMapper.map(item,OrderDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public OrderDTO getOrderById(Integer id) {
        try {
            OrderEntity order = orderRepository.findByOrderId(id);
            if (order == null) {
                throw new EntityNotFoundException("Category not found");
            }
            return modelMapper.map(order, OrderDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public void saveOrder(OrderDTO orderDTO) {
        if ( orderDTO != null) {
            LocalDate currentDate = LocalDate.now();
            OrderEntity order= modelMapper.map(orderDTO, OrderEntity.class);
            if (orderDTO.getOrderUser() != null) {
                order.setOrderUser(usersRepository.findByUserId(orderDTO.getOrderUser().getUserId()));
            }
            order.setOrderDateAdd(Date.valueOf(currentDate));
            if (order != null) {
                orderRepository.save(order);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu của Entity");
            }
        }
    }

    @Override
    public void deleteOrder(Integer id) {
    orderRepository.deleteById(id);
    }

    @Override
    public void updateOrder(OrderDTO orderDTO) {
        if (orderDTO != null) {
            OrderEntity order = orderRepository.findByOrderId(orderDTO.getOrderId());
            modelMapper.map(orderDTO, order);
            if (order != null) {
                orderRepository.save(order);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu của Entity");
            }
        }
    }
}
