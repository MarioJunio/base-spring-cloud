create table account(
    id bigint primary key auto_increment,
    name varchar(60) not null,
    balance decimal(10,2) default 0
);

create table activity(
    id bigint primary key auto_increment,
    owner_account bigint,
    source_account bigint,
    target_account bigint,
    type varchar(30),
    value decimal(10,2),
    balance decimal(10,2),
    date timestamp,
    constraint fk_owner_account foreign key (source_account) references account(id),
    constraint fk_source_account foreign key (source_account) references account(id),
    constraint fk_target_account foreign key (target_account) references account(id)
);