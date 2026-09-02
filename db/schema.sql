CREATE TABLE product (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  product_name VARCHAR(255) NOT NULL,
  created_by VARCHAR(100) NOT NULL,
  created_on TIMESTAMP NOT NULL,
  modified_by VARCHAR(100),
  modified_on TIMESTAMP,
  INDEX idx_product_name (product_name),
  INDEX idx_product_created_on (created_on)
);
CREATE TABLE item (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  product_id BIGINT NOT NULL,
  quantity INT NOT NULL,
  INDEX idx_item_product_id (product_id),
  CONSTRAINT fk_item_product FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);
