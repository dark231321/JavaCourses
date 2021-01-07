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
    "userTo"    serial          references chat.user on delete cascade,
    "userFrom"  serial          references chat.user on delete cascade,
    time        timestamp       not null,
    value       VARCHAR(140)    not null,

    primary key (id)
);

select
    m.time, u.login _FROM, u1.login _TO, m.value
from chat.message as m
    JOIN chat.user u on u.id = m."userFrom"
    JOIN chat.user u1 on u1.id = m."userTo"
ORDER BY m.time;

commit;