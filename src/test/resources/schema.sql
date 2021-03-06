drop table if exists acc_account CASCADE;
drop table if exists acc_account_activity CASCADE ;
drop table if exists acc_money_transfer CASCADE ;
drop table if exists crd_credit_card CASCADE ;
drop table if exists crd_credit_card_activity CASCADE ;
drop table if exists cus_customer CASCADE ;
drop sequence if exists acc_account_activity_id_seq;
drop sequence if exists acc_account_id_seq;
drop sequence if exists acc_money_transfer_id_seq;
drop sequence if exists crd_credit_card_activty_id_seq;
drop sequence if exists crd_credit_card_id_seq;
drop sequence if exists cus_customer_id_seq;
create sequence acc_account_activity_id_seq start with 1 increment by 50;
create sequence acc_account_id_seq start with 1 increment by 50;
create sequence acc_money_transfer_id_seq start with 1 increment by 50;
create sequence crd_credit_card_activty_id_seq start with 1 increment by 50;
create sequence crd_credit_card_id_seq start with 1 increment by 50;
create sequence cus_customer_id_seq start with 1 increment by 50;
create table acc_account (id bigint not null, create_date timestamp, created_by bigint, update_date timestamp, updated_by bigint, cancel_date timestamp, currency_type varchar(30), current_balance numeric(19,2), account_type varchar(30), iban_no varchar(30), status varchar(30), id_cus_customer bigint, primary key (id));
create table acc_account_activity (id bigint not null, create_date timestamp, created_by bigint, update_date timestamp, updated_by bigint, account_activity_type varchar(30), amount numeric(19,2), current_balance numeric(19,2), transaction_date timestamp, id_acc_account bigint, primary key (id));
create table acc_money_transfer (id bigint not null, create_date timestamp, created_by bigint, update_date timestamp, updated_by bigint, amount numeric(19,2), description varchar(100), money_transfer_type varchar(30), transfer_date timestamp, id_acc_account_from bigint, id_acc_account_to bigint, primary key (id));
create table crd_credit_card (id bigint not null, create_date timestamp, created_by bigint, update_date timestamp, updated_by bigint, available_card_limit numeric(19,2), cancel_date timestamp, card_no bigint, current_debt numeric(19,2), cutoff_date date, cvv_no bigint, due_date date, expire_date date, minimum_payment_amount numeric(19,2), status varchar(30), total_limit numeric(19,2), id_cus_customer bigint, primary key (id));
create table crd_credit_card_activity (id bigint not null, create_date timestamp, created_by bigint, update_date timestamp, updated_by bigint, amount numeric(19,2), credit_card_activity_type varchar(30), description varchar(100), transaction_date timestamp, id_crd_credit_card bigint, primary key (id));
create table cus_customer (id bigint not null, create_date timestamp, created_by bigint, update_date timestamp, updated_by bigint, identity_no bigint not null, name varchar(100) not null, password varchar(255) not null, surname varchar(100) not null, primary key (id));
alter table acc_account add constraint FKt7wc5sqflovgfyu8joo6rg1x7 foreign key (id_cus_customer) references cus_customer;
alter table acc_account_activity add constraint FKcp83pikeshampmydep05v04gv foreign key (id_acc_account) references acc_account;
alter table acc_money_transfer add constraint FK17fm3i6706i1elj53xncwy5gk foreign key (id_acc_account_from) references acc_account;
alter table acc_money_transfer add constraint FKfld198dik1382lv4my2gug4h9 foreign key (id_acc_account_to) references acc_account;
alter table crd_credit_card add constraint FKdo4ageiwucvjh9f0i1as2slv2 foreign key (id_cus_customer) references cus_customer;
alter table crd_credit_card_activity add constraint FK4fke06ctxvru26m4oyjraue8a foreign key (id_crd_credit_card) references crd_credit_card;