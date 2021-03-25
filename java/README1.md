## Descriptions

This is where information about users and their favorite restaurants will be stored. The database represents in three tables: users - favorite and restaurant. 

As each user signs up with an user name password, this information will be saved. Once the user  puts in their location and a food type preference in the front-end, restaurant options will show up. They  will then be able to save their favorite restaurant and this data will be save in the favorite table. 


## Setup

For setting up, we need to connect with the database via PGAdmin. The application uses this user to make connections to the database. 

The tables to store data are created in PostgresSQL. User is granted SELECT, INSERT, UPDATE, and DELETE privileges for all database tables and can SELECT from all sequences. The application datasource has been configured to connect using this user. 

The application runs on Spring Boot framework.  

## Table structures:

- USERS: user_id, user name, password_hash, role
- RESTAURANT: restaurant_id, city_id, restaurant_name, cuisine, price_range, rating, image, address
- FAVORITE: restaurant_id, user_id

## Data Connectivity:

The database is accessed via JDBC - an API incorporated in Java, this also allows all the methods and classes to be accessed to.

