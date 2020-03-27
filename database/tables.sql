
CREATE SEQUENCE posts_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE users_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE jobs_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE ads_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;


CREATE TABLE users (
  id bigint NOT NULL PRIMARY KEY DEFAULT nextval('users_id_sequence'),
  first_name varchar(45) NOT NULL,
  last_name varchar(45) NOT NULL,
  user_name varchar(45) NOT NULL,
  address varchar(400) NOT NULL,
  photo varchar(400) NOT NULL,
  status smallint NOT NULL DEFAULT 1,
  version integer NOT NULL DEFAULT 1,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc')
);

CREATE TABLE posts (
  id bigint NOT NULL PRIMARY KEY DEFAULT nextval('posts_id_sequence'),
  title varchar(45) NOT NULL,
  description varchar(45) NOT NULL,
  photo varchar(400) NOT NULL,
  status smallint NOT NULL DEFAULT 1,
  version integer NOT NULL DEFAULT 1,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc')
);

CREATE TABLE jobs (
  id bigint NOT NULL PRIMARY KEY DEFAULT nextval('jobs_id_sequence'),
  title varchar(45) NOT NULL,
  description varchar(45) NOT NULL,
  user_id bigint NOT NULL,
  cat_id bigint NOT NULL,
  subcat_id bigint NOT NULL,
  status smallint NOT NULL DEFAULT 1,
  version integer NOT NULL DEFAULT 1,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc')
);

CREATE TABLE ads (
  id bigint NOT NULL PRIMARY KEY DEFAULT nextval('ads_id_sequence'),
  title varchar(45) NOT NULL,
  description varchar(45) NOT NULL,
  photo varchar(400) NOT NULL,
  status smallint NOT NULL DEFAULT 1,
  version integer NOT NULL DEFAULT 1,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc')
);

