begin;

drop schema if exists chat cascade;
create schema chat;

drop table if exists chat.user;

create table chat.user(
    id    serial            not null,
    login VARCHAR           not null,
    primary key (id)
);

drop table if exists chat.message;

create table chat.message(
    id          serial          not null,
    "user_from"    serial          references chat.user on delete cascade,
    "user_to"  serial          references chat.user on delete cascade,
    time        timestamp       not null,
    value       VARCHAR(140)    not null,
    primary key (id)
);
select * from chat.user;
commit;