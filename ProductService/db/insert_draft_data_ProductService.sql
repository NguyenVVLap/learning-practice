-- Category
INSERT INTO category (name) VALUES
('Electronics'),
('Books'),
('Clothing');

-- Product
INSERT INTO product (name, price, quantity, category_id) VALUES
('Smartphone', 500.00, 10, 1),
('Laptop', 1200.00, 5, 1),
('Headphones', 80.00, 20, 1),

('Novel: Java Basics', 15.50, 30, 2),
('Spring Boot Guide', 25.00, 15, 2),

('T-Shirt', 10.00, 50, 3),
('Jeans', 40.00, 25, 3);


DECLARE @i INT = 1;

WHILE @i <= 50
BEGIN
    INSERT INTO product (name, price, quantity, category_id)
    VALUES (
        CONCAT('Product ', @i),
        RAND() * (2000 - 100) + 100,  -- random giá từ 100 -> 2000
        FLOOR(RAND() * 100 + 1),      -- random số lượng 1 -> 100
        1
    );
    SET @i = @i + 1;
END;

SELECT * FROM product;
SELECT * FROM category;
