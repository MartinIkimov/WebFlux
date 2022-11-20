create table users (
    id bigint auto_increment,
    name varchar(50),
    balance int,
    primary key (id)
);

create table user_transaction(
    id bigint auto_increment,
    user_id bigint,
    amount int,
    transaction_date timestamp,
    foreign key (user_id) references users(id) on delete cascade
);

insert into users
    (name, balance)
    values
    ('Sam', 1000),
    ('Mike', 1200),
    ('Jake', 800),
    ('Marshal', 2000);