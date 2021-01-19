package com.tinhcao.order;

import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    Logger logger = Logger.getLogger(OrderController.class.getSimpleName());

    private PurchaseOrderRepository orderRepo;

    public OrderController(PurchaseOrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @PostMapping
    public PurchaseOrder placeOrder(PurchaseOrder order) {
        logger.info("Order " + order);
        order = this.orderRepo.save(order);
        return order;
    }

    @GetMapping(path = "/{id}")
    public PurchaseOrder getOrder(@PathVariable("id") Long id) {
        logger.info("Order ID " + id);
        PurchaseOrder order = this.orderRepo.getOrderWithPositions(id);
        if (order == null) {
            throw new NoResultException();
        }
        return order;
    }
}