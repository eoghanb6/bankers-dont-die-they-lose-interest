create table name(name_id int primary key auto_increment, first_name varchar(20), last_name varchar(20));

create table account_type (type_id int primary key auto_increment, account_type varchar(20), overdraft decimal(15,2), transaction_fee decimal (15,2));

create table account (account_number int primary key auto_increment, name_id int ,type_id int, balance decimal(15,2));

alter table account add constraint fk_name_id foreign key(name_id) references name (name_id);
alter table account add constraint fk_account_type_id foreign key(type_id) references account_type (type_id);

