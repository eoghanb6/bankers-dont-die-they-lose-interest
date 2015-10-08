create database new;
use new;

create table account_type (type_id int primary key auto_increment, account_type varchar(20) NOT NULL, overdraft decimal(15,2) NOT NULL, transaction_fee decimal (15,2) NOT NULL);

create table account (account_number int primary key auto_increment, first_name varchar(20) NOT NULL , last_name varchar(20) NOT NULL, type_id int NOT NULL, balance decimal(15,2) NOT NULL);

alter table account add constraint fk_account_type_id foreign key(type_id) references account_type (type_id);

INSERT into account_type(account_type, overdraft, transaction_fee) values ("Standard", 500, 0),("Saver", 0, 1),("Premium",3000,0);

Create view beatifulview1 AS
SELECT account.account_number,
        account.first_name,
       account.last_name,  
        account_type.account_type,
        account.balance
FROM    account 
        INNER JOIN account_type 
            ON account.type_id = account_type.type_id ;

Create view beatifulview2 AS
SELECT account.account_number,
        account.first_name,
       account.last_name,  
        account_type.account_type,
        account.balance,
	account_type.overdraft,
	account_type.transaction_fee
FROM    account 
        INNER JOIN account_type
            ON account.type_id = account_type.type_id ;
 	




        
