drop table if exists items;
create table items (
  id BIGINT PRIMARY KEY,
  name VARCHAR(256),
  spec VARCHAR(256),
  price DECIMAL(20, 2)
);