# Android Ninja - Class No: 12
Date: 13 October, 2017

## Topics ##
- SQLite Database implementation with abstraction layer
	- Create Database
	- Create Table
	- Write a record (row) into table
	- Read all records from table
	- Count the number of row in table
	- Search a student by his registration number

No concrete implementation for database query. I implement here an abstraction layer for query. Activity class just call a method of an `interface` and implement a `callback` for query result.