package in.ankeshkr.foodiesapi.service;

import com.razorpay.RazorpayException;
import in.ankeshkr.foodiesapi.io.OrderRequest;
import in.ankeshkr.foodiesapi.io.OrderResponse;

import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderResponse createOrderWithPayment(OrderRequest request) throws RazorpayException;

    void verifyPayment(Map<String, String> paymentData, String status);

    List<OrderResponse>getUserOrders();

    void removeOrder(String orderId);

    List<OrderResponse> getOrdersOfAllUsers();

    void updateOrderStatus(String orderId, String status);


}
