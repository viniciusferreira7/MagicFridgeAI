ALTER TABLE food_items DROP PRIMARY KEY;

ALTER TABLE food_items ALTER COLUMN id BIGINT AUTO_INCREMENT;

ALTER TABLE food_items ADD PRIMARY KEY (id);