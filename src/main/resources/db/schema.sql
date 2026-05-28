CREATE SCHEMA catalogue_db;
USE catalogue_db;

CREATE TABLE book(  
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary Key',
    title VARCHAR(255) NOT NULL COMMENT 'Book Title',
    author VARCHAR(255) NOT NULL COMMENT 'Book Author',
    publication_date DATE NOT NULL COMMENT 'Publication Date',
    isbn VARCHAR(20) NOT NULL COMMENT 'ISBN Number',
    category VARCHAR(255) COMMENT 'Book Category',
    visibility BOOLEAN DEFAULT TRUE COMMENT 'Book Visibility',
    unit_price DECIMAL(10, 2) NOT NULL COMMENT 'Unit Price',
    stock INT NOT NULL COMMENT 'Stock Quantity',
    rating FLOAT COMMENT 'Average Rating',
    create_time DATETIME COMMENT 'Create Time'    
) COMMENT '';