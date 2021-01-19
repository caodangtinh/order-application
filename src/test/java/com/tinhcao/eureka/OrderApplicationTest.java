package com.tinhcao.eureka;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.tinhcao.order.OrderApplication;
import com.tinhcao.order.OrderController;
import com.tinhcao.order.PurchaseOrder;
import com.tinhcao.order.PurchaseOrderPosition;
import com.tinhcao.order.PurchaseOrderState;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class OrderApplicationTest {

	@Autowired
	private OrderController orderController;

	@Test
	@Transactional
	@Commit
	public void testConfirmedOrder() throws JsonProcessingException {
		PurchaseOrder order = new PurchaseOrder();
		order.setCustomerName("Tinh Cao");
		order.setAmount(50D);
		order.setState(PurchaseOrderState.PENDING);
		
		Set<PurchaseOrderPosition> positions = new HashSet<>();
		PurchaseOrderPosition p = new PurchaseOrderPosition();
		p.setBookId(1L);
		p.setQuantity(1);	
		p.setOrder(order);
		positions.add(p);
		order.setPositions(positions);

		orderController.placeOrder(order);
	}

	@Test
	@Transactional
	@Commit
	public void testRejectedOrderOutOfStock() throws JsonProcessingException {
		PurchaseOrder order = new PurchaseOrder();
		order.setCustomerName("Tinh Cao");
		order.setAmount(50D);
		order.setState(PurchaseOrderState.PENDING);
		
		Set<PurchaseOrderPosition> positions = new HashSet<>();
		PurchaseOrderPosition p = new PurchaseOrderPosition();
		p.setBookId(2L);
		p.setQuantity(1);
		p.setOrder(order);
		positions.add(p);
		order.setPositions(positions);

		orderController.placeOrder(order);
	}

	@Test
	@Transactional
	@Commit
	public void testRejectedOrderUnknown() throws JsonProcessingException {
		PurchaseOrder order = new PurchaseOrder();
		order.setCustomerName("Tinh Cao");
		order.setAmount(50D);
		order.setState(PurchaseOrderState.PENDING);
		
		Set<PurchaseOrderPosition> positions = new HashSet<>();
		PurchaseOrderPosition p = new PurchaseOrderPosition();
		p.setBookId(3L);
		p.setQuantity(1);
		p.setOrder(order);
		positions.add(p);
		order.setPositions(positions);

		orderController.placeOrder(order);
	}

	@Test
	@Transactional
	@Commit
	public void testPaymentFailed() throws JsonProcessingException {
		PurchaseOrder order = new PurchaseOrder();
		order.setCustomerName("Tinh Cao");
		order.setAmount(150D);
		order.setState(PurchaseOrderState.PENDING);
		
		Set<PurchaseOrderPosition> positions = new HashSet<>();
		PurchaseOrderPosition p = new PurchaseOrderPosition();
		p.setBookId(1L);
		p.setQuantity(1);	
		p.setOrder(order);
		positions.add(p);
		order.setPositions(positions);

		orderController.placeOrder(order);
	}

}
