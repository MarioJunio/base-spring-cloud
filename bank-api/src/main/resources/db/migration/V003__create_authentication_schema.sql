create table user (
    id bigint primary key auto_increment,
    username varchar(60) not null,
    password varchar(60) not null,
    role varchar(60)
);