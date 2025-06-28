CREATE TABLE food_items (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(200),
    category VARCHAR(100),
    quantity INT,
    validated_at DATETIME,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT check_category CHECK (category IN ('FRUITS', 'VEGETABLES', 'MEAT', 'SEAFOOD', 'DAIRY', 'GRAINS', 'SWEETS', 'BEVERAGES', 'FAST_FOOD', 'SNACKS'))
);