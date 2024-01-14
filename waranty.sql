create database warranty;

create table warranty(
customer_name varchar(100),
product varchar (100),
Warranty_st_date varchar(20),
Warranty_period varchar (20),
Warranty_ed_date varchar (20),
discription varchar(200)
);

create table settle(
ord_id int,
mob_num int,
customer varchar(100),
settle_date varchar(20),
settle_Amount double
);

create table orderdetail (
ord_id int auto_increment primary key,
customer varchar(100),
mob_num int,
order_date varchar(20),
ord_detail varchar(200),
total double,
settle double
);

create table remain(
ord_id int auto_increment primary key,
mob_num int(11),
customer varchar(100),
rem_tot double
);

create table users(
us_id int auto_increment primary key,
username varchar(20),
password varchar(20),
state varchar(20),
A_key varchar(30),
Act_date varchar(20),
End_date varchar(20)
);
drop table users;

insert into users(username,password,state,A_key,Act_date,End_date) 
values
("admin","1234","active","123456789acvetfhtrjf","2022-10-20","2025-12-12"),
("amal","1234","active","123456789acvetfhtrjf","2022-10-20","2025-12-12");

create table active_key(
A_id int auto_increment primary key,
A_key varchar(30),
state varchar(20),
Act_date varchar(20),
end_date varchar(20)
);

drop table active_key;

