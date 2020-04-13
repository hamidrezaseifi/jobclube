
CREATE TABLE users (
  id integer NOT NULL IDENTITY PRIMARY KEY,
  first_name varchar(45) NOT NULL,
  last_name varchar(45) NOT NULL,
  user_name varchar(45) NOT NULL,
  address varchar(400) NOT NULL,
  photo varchar(400) NOT NULL,
  status smallint NOT NULL DEFAULT 1,
  version integer NOT NULL DEFAULT 1,
  created_at timestamp default 'now',
  updated_at timestamp default 'now'
);
