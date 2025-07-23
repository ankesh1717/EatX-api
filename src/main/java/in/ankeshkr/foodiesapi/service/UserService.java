package in.ankeshkr.foodiesapi.service;

import in.ankeshkr.foodiesapi.io.UserRequest;
import in.ankeshkr.foodiesapi.io.UserResponse;

public interface UserService {

    UserResponse registerUser(UserRequest request);

    String findByUserId();
}
