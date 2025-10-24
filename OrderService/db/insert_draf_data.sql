-- Insert đơn hàng
INSERT INTO orders DEFAULT VALUES;  -- id = 1
INSERT INTO orders DEFAULT VALUES;  -- id = 2
INSERT INTO orders DEFAULT VALUES;  -- id = 3

-- Insert chi tiết đơn hàng 1
INSERT INTO order_detail (order_id, product_id, quantity, price) VALUES (1, 101, 2, 500.0);
INSERT INTO order_detail (order_id, product_id, quantity, price) VALUES (1, 102, 1, 300.0);
INSERT INTO order_detail (order_id, product_id, quantity, price) VALUES (1, 103, 5, 150.0);

-- Insert chi tiết đơn hàng 2
INSERT INTO order_detail (order_id, product_id, quantity, price) VALUES (2, 104, 3, 200.0);
INSERT INTO order_detail (order_id, product_id, quantity, price) VALUES (2, 105, 1, 1200.0);

-- Insert chi tiết đơn hàng 3
INSERT INTO order_detail (order_id, product_id, quantity, price) VALUES (3, 106, 10, 50.0);
INSERT INTO order_detail (order_id, product_id, quantity, price) VALUES (3, 107, 2, 800.0);


select * from orders with(nolock);
select * from order_detail with(nolock);

