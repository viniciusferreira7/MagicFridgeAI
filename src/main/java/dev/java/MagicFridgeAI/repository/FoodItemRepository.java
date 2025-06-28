package dev.java.MagicFridgeAI.repository;

import dev.java.MagicFridgeAI.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, String> {
}
