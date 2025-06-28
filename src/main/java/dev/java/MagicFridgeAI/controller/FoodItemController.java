package dev.java.MagicFridgeAI.controller;

import dev.java.MagicFridgeAI.dto.FoodItemDTO;
import dev.java.MagicFridgeAI.model.FoodItem;
import dev.java.MagicFridgeAI.service.FoodItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create register of food")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "201", description = "Food was successfully registered")
    })
    @PostMapping
    public ResponseEntity<Void> createFoodItem(@RequestBody FoodItemDTO foodItem){
        this.foodService.create(foodItem);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
