package in.ankeshkr.foodiesapi.service;

import in.ankeshkr.foodiesapi.io.FoodRequest;
import in.ankeshkr.foodiesapi.io.FoodResponse;
import in.ankeshkr.foodiesapi.repository.FoodRepository;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodService {

    String uploadFile(MultipartFile file);

    FoodResponse addFood(FoodRequest request, MultipartFile file);

    List<FoodResponse>readFoods();

    FoodResponse readFood(String id);

    boolean deleteFile(String filname);

    void deleteFood(String id);
}
