insert into user(id,email,password,first_name,last_name) values (0,'cz@example.com','password','CZ','User');
insert into user(id,email,password,first_name,last_name) values (1,'joe@example.com','password','Joe','Other');
insert into user(id,email,password,first_name,last_name) values (2,'eve@example.com','password','Eve','Another');

update user set password = '$2a$10$KJaw7shE7NIKBycuo4NSkugZqqht7SwjI3DQhsJeUNERkZR0fY02e';
