CREATE TABLE roles (
  id serial PRIMARY KEY,
  name varchar(50) NOT NULL
);

CREATE TABLE users (
  id serial PRIMARY KEY,
  name varchar(150),
  email varchar(150) UNIQUE NOT NULL,
  password varchar(255) NOT NULL,
  enabled boolean DEFAULT true
);

CREATE TABLE user_roles (
  user_id int REFERENCES users(id),
  role_id int REFERENCES roles(id),
  PRIMARY KEY (user_id, role_id)
);

CREATE TABLE card_brands (
  id serial PRIMARY KEY,
  name varchar(100) NOT NULL
);

CREATE TABLE programs (
  id serial PRIMARY KEY,
  name varchar(150) NOT NULL,
  description text
);

CREATE TABLE cards (
  id serial PRIMARY KEY,
  alias varchar(150),
  card_number_masked varchar(50),
  type varchar(50),
  brand_id int REFERENCES card_brands(id),
  program_id int REFERENCES programs(id),
  user_id int REFERENCES users(id),
  program_balance bigint DEFAULT 0
);

CREATE TABLE purchases (
  id serial PRIMARY KEY,
  user_id int REFERENCES users(id),
  card_id int REFERENCES cards(id),
  amount numeric(12,2),
  purchase_date timestamp,
  expected_credit_date timestamp,
  expected_points bigint,
  credited_points bigint DEFAULT 0,
  credited boolean DEFAULT false
);

CREATE TABLE attachments (
  id serial PRIMARY KEY,
  file_name varchar(255),
  file_type varchar(50),
  url text,
  purchase_id int REFERENCES purchases(id)
);
