package in.ankeshkr.foodiesapi.service;

import in.ankeshkr.foodiesapi.entity.CartEntity;
import in.ankeshkr.foodiesapi.io.CartRequest;
import in.ankeshkr.foodiesapi.io.CartResponse;
import in.ankeshkr.foodiesapi.repository.CartRepository;
import in.ankeshkr.foodiesapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Override
    public CartResponse addToCart(CartRequest request) {
        String userId = userService.findByUserId(); // assuming it gives current user's ID
        CartEntity cart = cartRepository.findByUserId(userId)
                .orElse(new CartEntity(userId, new HashMap<>()));

        String foodId = request.getFoodId();
        cart.getItems().put(foodId, cart.getItems().getOrDefault(foodId, 0) + 1);

        cartRepository.save(cart); // ✅ Save to MongoDB

        return new CartResponse(cart.getId(), cart.getUserId(), cart.getItems());
    }

    @Override
    public CartResponse getCart() {
        String userId = userService.findByUserId();
        CartEntity cart = cartRepository.findByUserId(userId)
                .orElse(new CartEntity(userId, new HashMap<>()));
        return new CartResponse(cart.getId(), cart.getUserId(), cart.getItems());
    }

    @Override
    public void clearCart() {
        String userId = userService.findByUserId();
        cartRepository.deleteByUserId(userId);
    }

    @Override
    public CartResponse removeFromCart(CartRequest request) {
        String userId = userService.findByUserId();
        CartEntity cart = cartRepository.findByUserId(userId)
                .orElse(new CartEntity(userId, new HashMap<>()));

        String foodId = request.getFoodId();
        Map<String, Integer> items = cart.getItems();
        if (items.containsKey(foodId)) {
            int qty = items.get(foodId);
            if (qty <= 1) {
                items.remove(foodId);
            } else {
                items.put(foodId, qty - 1);
            }
        }

        cartRepository.save(cart); // ✅ Save after removal
        return new CartResponse(cart.getId(), cart.getUserId(), cart.getItems());
    }
}
