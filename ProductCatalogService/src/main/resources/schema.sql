-- ProductCatalogService Database Schema
-- MySQL 8.0+

-- Create Database
CREATE DATABASE IF NOT EXISTS productcatalogdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE productcatalogdb;

-- Categories Table
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE KEY uk_name,
    description LONGTEXT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    state VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    INDEX idx_name (name),
    INDEX idx_state (state)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Products Table
CREATE TABLE IF NOT EXISTS products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    description LONGTEXT,
    image_url VARCHAR(500),
    category_id BIGINT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    state VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL,
    INDEX idx_title (title),
    INDEX idx_price (price),
    INDEX idx_category_id (category_id),
    INDEX idx_state (state),
    UNIQUE KEY uk_title (title)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insert Sample Categories
INSERT INTO categories (name, description, state) VALUES
('Electronics', 'Electronic devices and gadgets', 'ACTIVE'),
('Clothing', 'Apparel and accessories', 'ACTIVE'),
('Books', 'Books and publications', 'ACTIVE'),
('Home & Garden', 'Home and garden items', 'ACTIVE'),
('Sports', 'Sports equipment and gear', 'ACTIVE');

-- Verify Schema
SHOW TABLES;
DESCRIBE categories;
DESCRIBE products;

