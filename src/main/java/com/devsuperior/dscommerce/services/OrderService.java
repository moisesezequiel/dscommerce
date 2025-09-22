package com.devsuperior.dscommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.dto.checkout.OrderDTO;
import com.devsuperior.dscommerce.dto.checkout.OrderItemDTO;
import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.entities.OrderItem;
import com.devsuperior.dscommerce.entities.OrderStatus;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.repositories.OrderItemRepository;
import com.devsuperior.dscommerce.repositories.OrderRepository;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true) 
    public OrderDTO findById(Long id){
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order);

    }

    @Transactional
	public OrderDTO insert(OrderDTO dto) {
    	Order order = new Order();
    	
    	order.setMoment(Instant.now());
    	order.setStatus(OrderStatus.WAINTTNG_PAYMENT);
    	
    	User user = userService.authenticated();
    	order.setClient(user);
    	
    	
    	for(OrderItemDTO itemdto : dto.getItems()) {
    		Product product = productRepository.getReferenceById(itemdto.getProductId());
    		OrderItem item = new OrderItem(order, product, itemdto.getQuantity(), product.getPrice()); 
    		order.getItems().add(item);
    	}
    		repository.save(order);
    		orderItemRepository.saveAll(order.getItems());
    		
		return new OrderDTO(order);
	}

}
