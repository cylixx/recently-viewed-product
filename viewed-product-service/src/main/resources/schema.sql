
CREATE TABLE IF NOT EXISTS  
    customer 
    ( 
        customer_id bigint NOT NULL, 
        address     VARCHAR(255), 
        name        VARCHAR(255), 
        PRIMARY KEY (customer_id) 
    ) 
    ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_0900_ai_ci;
    
CREATE TABLE IF NOT EXISTS 
    product 
    ( 
        product_id bigint NOT NULL, 
        name       VARCHAR(255), 
        price DOUBLE NOT NULL, 
        quantity INT NOT NULL, 
        PRIMARY KEY (product_id) 
    ) 
    ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS 
    product_most_viewed 
    ( 
        id            bigint NOT NULL, 
        number_visits bigint, 
        product_id    bigint NOT NULL, 
        PRIMARY KEY (id), 
        INDEX FK5kby6kihb89dunouu3ul9rhbv (product_id) 
    ) 
    ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS 
    product_viewed 
    ( 
        id          bigint NOT NULL, 
        customer_id bigint NOT NULL, 
        last_viewed TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
        product_id  bigint NOT NULL, 
        PRIMARY KEY (id), 
        CONSTRAINT UK_t8wk5tdxv91rh5nh4p5v6qshy UNIQUE (product_id), 
        INDEX FK97x4fwq3v057vaxmctgwaj79t (customer_id) 
    ) 
    ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_0900_ai_ci;
    
ALTER TABLE product_viewed ADD CONSTRAINT FK_productViewed_customer FOREIGN KEY (customer_id) REFERENCES `customer`(customer_id);
ALTER TABLE product_viewed ADD CONSTRAINT FK_productViewed_product FOREIGN KEY (product_id) REFERENCES `product`(product_id);
ALTER TABLE product_most_viewed ADD CONSTRAINT FK_productMostViewed_product FOREIGN KEY (product_id) REFERENCES `product`(product_id);
