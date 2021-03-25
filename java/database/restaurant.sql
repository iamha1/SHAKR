DROP TABLE IF EXISTS favorite cascade;
DROP TABLE IF EXISTS restaurant cascade;
--DROP TABLE IF EXISTS users CASCADE;

DROP SEQUENCE IF EXISTS seq_account_id;
DROP SEQUENCE IF EXISTS seq_restaurant_id;
DROP SEQUENCE IF EXISTS seq_favorite_id;



CREATE SEQUENCE seq_favorite_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_restaurant_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE TABLE restaurant (
       res_id int NOT NULL,
       city_id int,
       name varchar(50) NOT NULL,
       cuisines varchar(100),
       price_range decimal(13, 2),
       rating float,
       image VARCHAR (200),
       address varchar (200),
       
       CONSTRAINT pk_restaurant PRIMARY KEY (res_id)
       
       
);
  
 
CREATE TABLE favorite (
	user_id serial NOT NULL,
	res_id int NOT NULL,
	
	CONSTRAINT pk_favorite PRIMARY KEY (user_id, res_id),
	
	CONSTRAINT FK_favorite_restaurant FOREIGN KEY (res_id) REFERENCES restaurant (res_id),
	CONSTRAINT fk_favorite_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);

--ALTER TABLE favorite DROP CONSTRAINT pk_favorite;
--ALTER TABLE favorite CONSTRAINT pk_favorite PRIMARY KEY (user_id, restaurant_id);








