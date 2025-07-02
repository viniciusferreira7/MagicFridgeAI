package dev.java.MagicFridgeAI.controller;

import dev.java.MagicFridgeAI.dto.FoodItemDTO;
import dev.java.MagicFridgeAI.service.FoodItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodItemController {
    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodService) {
        this.foodItemService = foodService;
    }

    @Operation(summary = "Create register of food")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Food was successfully registered")
    })
    @PostMapping
    public ResponseEntity<Void> createFoodItem(@RequestBody FoodItemDTO foodItem) {
        foodItemService.create(foodItem);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all food items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of food items retrieved successfully")
    })

    @GetMapping
    public ResponseEntity<List<FoodItemDTO>> getAllFoodItems() {
        List<FoodItemDTO> foodItems = foodItemService.getAll();
        return ResponseEntity.ok(foodItems);
    }

    @Operation(summary = "Get a food item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Food item retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Food item not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FoodItemDTO> getFoodItemById(@PathVariable Long id) {
        FoodItemDTO foodItem = foodItemService.getById(id);

        if (foodItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(foodItem);
    }


    @Operation(summary = "Update a food item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Food was successfully updated"),
            @ApiResponse(responseCode = "404", description = "Food not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<FoodItemDTO> updateFoodItem(@PathVariable Long id, @RequestBody FoodItemDTO updatedDTO) {
        FoodItemDTO updatedItem = foodItemService.updateById(id, updatedDTO);

        if (updatedItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(updatedItem);
    }

    @Operation(summary = "Delete a food item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Food was successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Food not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodItem(@PathVariable Long id) {
        boolean deleted = foodItemService.deleteById(id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.noContent().build();
    }
}