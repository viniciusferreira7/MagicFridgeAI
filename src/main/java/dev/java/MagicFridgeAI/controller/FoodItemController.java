package dev.java.MagicFridgeAI.controller;

import dev.java.MagicFridgeAI.model.FoodItem;
import dev.java.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food")
public class FoodItemController {
    final private FoodItemService foodService;

    public FoodItemController(FoodItemService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public ResponseEntity<Void> createFoodItem(@RequestBody FoodItem foodItem){
        this.foodService.create(foodItem);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
