create table users(
id int(11) primary key
,name varchar(100)
,email varchar(100)
,password varchar(255))

go
 
create table roles(
id int(11)
,name varchar(255)
)

go

create table users_role(
user_id int(11),
role_id int(11),
foreign key(user_id) references users(id),
foreign key(role_id) references roles(id) 
)
