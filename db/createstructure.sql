create database new;
use new;

create table account_type (type_id int primary key auto_increment, account_type varchar(20) NOT NULL, overdraft decimal(15,2) NOT NULL, transaction_fee decimal (15,2) NOT NULL);

create table account (first_name varchar(20) NOT NULL , last_name varchar(20) NOT NULL, account_number int primary key auto_increment NOT NULL, type_id int NOT NULL, balance decimal(15,2) NOT NULL);

alter table account add constraint fk_account_type_id foreign key(type_id) references account_type (type_id);

INSERT into account_type(account_type NOT NULL, overdraft NOT NULL, transaction_fee NOT NULL) values ("Standard", 500, 0),("Saver", 0, 1),("Premium",3000,0);

