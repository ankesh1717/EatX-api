package in.ankeshkr.foodiesapi.service;

import in.ankeshkr.foodiesapi.io.CartRequest;
import in.ankeshkr.foodiesapi.io.CartResponse;

public interface CartService {

    CartResponse addToCart(CartRequest request);

    CartResponse getCart();

    void clearCart();

    CartResponse removeFromCart(CartRequest cartRequest);
}
