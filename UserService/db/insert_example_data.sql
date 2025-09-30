use master
CREATE DATABASE UserDB;
GO

use UserDB

INSERT INTO Users (email, password) VALUES
('alice@example.com', 'password123'),
('bob@example.com', 'securePass!'),
('charlie@example.com', 'charlie@2025');

select * from Users;