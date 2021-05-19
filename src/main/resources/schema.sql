DROP TABLE IF EXISTS clients;

CREATE TABLE clients (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  cpfCnpj VARCHAR(18) NOT NULL,
  deleted_at DATETIME
);

DROP TABLE IF EXISTS products;

CREATE TABLE products (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  price DECIMAL(8, 2) NOT NULL,
  deleted_at DATETIME
);

DROP TABLE IF EXISTS sells;

CREATE TABLE sells (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  client_id INT NOT NULL,
  product_id INT NOT NULL,
  date DATETIME NOT NULL,
  deleted_at DATETIME,
  FOREIGN KEY (client_id) references clients(id),
  FOREIGN KEY (product_id) references products(id)
);