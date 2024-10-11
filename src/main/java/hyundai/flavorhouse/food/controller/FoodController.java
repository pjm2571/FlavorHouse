package hyundai.flavorhouse.food.controller;

import hyundai.flavorhouse.food.dto.CreateAndEditFoodRequest;
import hyundai.flavorhouse.food.dto.FoodResponse;
import hyundai.flavorhouse.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/food")
    public ResponseEntity<?> postFood(
            @RequestBody CreateAndEditFoodRequest request
    ) {
        FoodResponse response = foodService.postFood(request);

        return ResponseEntity.ok(response);
    }

}
