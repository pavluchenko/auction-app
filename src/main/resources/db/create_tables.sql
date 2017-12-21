CREATE TABLE users
(
  id                    BIGINT  AUTO_INCREMENT PRIMARY KEY,
  email                 VARCHAR(255) UNIQUE,
  password_hash         VARCHAR(255),
  rating                VARCHAR(255),
  disable               BOOLEAN DEFAULT FALSE,
  password_hash_confirm VARCHAR(255)
);

CREATE TABLE role
(
  id   BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE users_role
(
  id      BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id INT REFERENCES role (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  role_id INT REFERENCES users (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE categories
(
  id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
  name               VARCHAR(255),
  parent_category_id BIGINT REFERENCES categories (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE lot
(
  id           BIGINT  AUTO_INCREMENT PRIMARY KEY,
  name         VARCHAR(255),
  description  VARCHAR(255),
  bayout_price DOUBLE PRECISION,
  photo        VARCHAR(255),
  min_price    DOUBLE PRECISION,
  max_price    DOUBLE PRECISION,
  disable      BOOLEAN DEFAULT FALSE,
  category_id  INT REFERENCES categories (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  user_id      INT REFERENCES users (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE rate
(
  id      BIGINT AUTO_INCREMENT PRIMARY KEY,
  price   DOUBLE PRECISION NOT NULL,
  date    DATE,
  lot_id  INT REFERENCES lot (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  user_id INT REFERENCES users (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE feature
(
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(255),
  description VARCHAR(255),
  lot_id      INT REFERENCES lot (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE subscription
(
  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
  date       DATE,
  user_id    INT REFERENCES users (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  creator_id INT REFERENCES users (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
