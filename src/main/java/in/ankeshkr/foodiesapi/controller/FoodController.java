package in.ankeshkr.foodiesapi.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.ankeshkr.foodiesapi.io.FoodRequest;
import in.ankeshkr.foodiesapi.io.FoodResponse;
import in.ankeshkr.foodiesapi.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequestMapping("/api/foods")
@AllArgsConstructor
@CrossOrigin("*")
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    public FoodResponse addFood(@RequestPart("food") String foodString,
                                @RequestPart("file")MultipartFile file){

        ObjectMapper objectMapper = new ObjectMapper();
        FoodRequest request = null;
        try{
            request = objectMapper.readValue(foodString, FoodRequest.class);
        }catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid JSON Format");
        }
        FoodResponse response = foodService.addFood(request, file);
        return response;
    }


    @GetMapping
    public List<FoodResponse> readFoods(){
        return foodService.readFoods();
    }

    @GetMapping("/{id}")
    public FoodResponse readFood(@PathVariable String id){
         return foodService.readFood(id);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFood(@PathVariable String id){
        foodService.deleteFood(id);
    }
}
