CREATE SCHEMA catalogue_db;
USE catalogue_db;

CREATE TABLE Book(  
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary Key',
    title VARCHAR(255) NOT NULL COMMENT 'Book Title',
    author VARCHAR(255) NOT NULL COMMENT 'Book Author',
    publication_date VARCHAR(255) NOT NULL COMMENT 'Publication Date',
    isbn VARCHAR(20) NOT NULL COMMENT 'ISBN Number',
    category VARCHAR(255) COMMENT 'Book Category',
    visibility BOOLEAN DEFAULT TRUE COMMENT 'Book Visibility',
    create_time DATETIME COMMENT 'Create Time',    
) COMMENT '';