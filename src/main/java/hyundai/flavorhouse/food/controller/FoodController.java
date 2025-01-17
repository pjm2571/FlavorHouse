package hyundai.flavorhouse.food.controller;

import hyundai.flavorhouse.food.dto.CreateAndEditFoodRequest;
import hyundai.flavorhouse.food.dto.CreateAndEditFoodResponse;
import hyundai.flavorhouse.food.dto.FoodDeleteResponse;
import hyundai.flavorhouse.food.dto.FoodDetailViewResponse;
import hyundai.flavorhouse.food.dto.FoodViewResponse;
import hyundai.flavorhouse.food.service.FoodService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/foods")
    public ResponseEntity<?> getFoods() {
        List<FoodViewResponse> response = foodService.getFoods();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/food/{foodId}")
    public ResponseEntity<?> getFoodById(
            @PathVariable Long foodId
    ) {
        FoodDetailViewResponse response = foodService.getFoodById(foodId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/food")
    public ResponseEntity<CreateAndEditFoodResponse> postFood(
            @RequestBody CreateAndEditFoodRequest request
    ) {
        CreateAndEditFoodResponse response = foodService.postFood(request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/food/{foodId}")
    public ResponseEntity<CreateAndEditFoodResponse> editFood(
            @PathVariable Long foodId,
            @RequestBody CreateAndEditFoodRequest request
    ) {
        CreateAndEditFoodResponse response = foodService.editFood(foodId, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/food/{foodId}")
    public ResponseEntity<?> deleteFood(
            @PathVariable Long foodId
    ) {
        foodService.deleteFood(foodId);

        return ResponseEntity.ok(FoodDeleteResponse.of(String.format("#%d 삭제 완료.", foodId)));
    }

}
