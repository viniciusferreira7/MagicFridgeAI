package dev.java.MagicFridgeAI.service;

import dev.java.MagicFridgeAI.dto.FoodItemDTO;
import dev.java.MagicFridgeAI.mapper.FoodItemMapper;
import dev.java.MagicFridgeAI.model.FoodItem;
import dev.java.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {
    private final FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItemDTO create(FoodItemDTO foodItemDTO) {
        FoodItem foodItem = FoodItemMapper.map(foodItemDTO);
        this.foodItemRepository.save(foodItem);
        return FoodItemMapper.map(foodItem);
    }

    public List<FoodItemDTO> getAll() {
        List<FoodItem> foodItemList = this.foodItemRepository.findAll();
        return foodItemList.stream().map(FoodItemMapper::map).toList();
    }

    public FoodItemDTO getById(Long id) {
        Optional<FoodItem> foodItem = this.foodItemRepository.findById(id);
        return foodItem.map(FoodItemMapper::map).orElse(null);
    }

    public FoodItemDTO updateById(Long id, FoodItemDTO updatedDTO) {
        Optional<FoodItem> optionalFoodItem = this.foodItemRepository.findById(id);

        if (optionalFoodItem.isEmpty()) {
            return null;
        }

        FoodItem existingFoodItem = optionalFoodItem.get();

        existingFoodItem.setName(updatedDTO.getName());
        existingFoodItem.setQuantity(updatedDTO.getQuantity());
        existingFoodItem.setValidatedAt(updatedDTO.getValidatedAt());

        FoodItem savedItem = this.foodItemRepository.save(existingFoodItem);
        return FoodItemMapper.map(savedItem);
    }

    public boolean deleteById(Long id) {
        Optional<FoodItem> optionalFoodItem = this.foodItemRepository.findById(id);

        if (optionalFoodItem.isEmpty()) {
            return false;
        }

        this.foodItemRepository.deleteById(id);
        return true;
    }
}