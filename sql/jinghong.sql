drop schema if exists public;

create schema if not exists public;

set time zone "Asia/Shanghai";

create extension "uuid-ossp";

-- 用户表
drop table if exists public.user;
create table if not exists public.user
(
    id              varchar(36) not null default uuid_generate_v4(),
    uid             varchar(20) not null,
    username        varchar(50) not null,
    password        varchar(30) not null,
    sex             varchar(2)  not null default '保密',
    birthday        varchar(10) not null default '2000-01-01',
    last_login_time timestamp,
    status          varchar(10) not null default '正常',
    created_by      varchar(50) not null default 'postgres',
    created_time    timestamp   not null default current_timestamp,
    updated_by      varchar(50) not null default 'postgres',
    updated_time    timestamp   not null default current_timestamp
);


truncate table public.user;
insert into public.user(uid, username, password)
values ('U0001', 'user001', '12345'),
       ('U0002', 'user002', '12345'),
       ('U0003', 'user003', '12345');


select *
from public.user
order by username;


-- 商品目录表
drop table if exists public.category;
create table if not exists public.category
(
    id           varchar(36) not null default uuid_generate_v4(),
    category_1   varchar(50),
    category_2   varchar(50),
    category_3   varchar(50),
    created_by   varchar(50) not null default 'postgres',
    created_time timestamp   not null default current_timestamp,
    updated_by   varchar(50) not null default 'postgres',
    updated_time timestamp   not null default current_timestamp
);


-- 商品表
drop table if exists public.goods;
create table if not exists public.goods
(
    id           varchar(36)    not null default uuid_generate_v4(),
    goods_no     varchar(50)    not null,
    goods_name   varchar(100)   not null,
    category     varchar(50)    not null,
    brand        varchar(50)    not null,
    price        numeric(10, 2) not null default 9999999999.99,
    image        varchar(500),
    tags         varchar(150),
    is_on_sale   varchar(1)     not null,
    created_by   varchar(50)    not null default 'postgres',
    created_time timestamp      not null default current_timestamp,
    updated_by   varchar(50)    not null default 'postgres',
    updated_time timestamp      not null default current_timestamp
);


-- 商品描述
drop table if exists public.goods_detail;
create table if not exists public.goods_detail
(
    id               varchar(36)  not null default uuid_generate_v4(),
    goods_no         varchar(50)  not null,
    description_item varchar(50)  not null, -- 规格名称
    description      varchar(150) not null, -- 规格
    created_by       varchar(50)  not null default 'postgres',
    created_time     timestamp    not null default current_timestamp,
    updated_by       varchar(50)  not null default 'postgres',
    updated_time     timestamp    not null default current_timestamp
);


-- 评价表
drop table if exists public.comment;
create table if not exists public.comment
(
    id           varchar(36)   not null default uuid_generate_v4(),
    uid          varchar(20)   not null,
    order_no     varchar(50)   not null,
    goods_no     varchar(100)  not null,
    comment      varchar(500)  not null,
    rate         numeric(1, 1) not null,
    image        varchar(500),
    created_by   varchar(50)   not null default 'postgres',
    created_time timestamp     not null default current_timestamp,
    updated_by   varchar(50)   not null default 'postgres',
    updated_time timestamp     not null default current_timestamp
);


-- 库存表
drop table if exists public.inventory;
create table if not exists public.inventory
(
    id           varchar(36) not null default uuid_generate_v4(),
    goods_no     varchar(50) not null,
    count        int         not null default 0,
    created_by   varchar(50) not null default 'postgres',
    created_time timestamp   not null default current_timestamp,
    updated_by   varchar(50) not null default 'postgres',
    updated_time timestamp   not null default current_timestamp
);


-- 库存记录
drop table if exists public.inventory_log;
create table if not exists public.inventory_log
(
    id           varchar(36) not null default uuid_generate_v4(),
    goods_no     varchar(50) not null,
    old_count    int         not null default 0,
    variation    int         not null default 1,
    type         varchar(2)  not null default '减少',
    created_by   varchar(50) not null default 'postgres',
    created_time timestamp   not null default current_timestamp,
    updated_by   varchar(50) not null default 'postgres',
    updated_time timestamp   not null default current_timestamp
);


-- 订单表
drop table if exists public.order;
create table if not exists public.order
(
    id           varchar(36)    not null default uuid_generate_v4(),
    uid          varchar(20)    not null,
    order_no     varchar(50)    not null,
    amount       numeric(10, 2) not null default 0.00,
    paid_by      varchar(10)    not null,
    status       varchar(10)    not null,
    created_by   varchar(50)    not null default 'postgres',
    created_time timestamp      not null default current_timestamp,
    updated_by   varchar(50)    not null default 'postgres',
    updated_time timestamp      not null default current_timestamp
);


-- 订单表
drop table if exists public.order_detail;
create table if not exists public.order_detail
(
    id           varchar(36)    not null default uuid_generate_v4(),
    order_no     varchar(50)    not null,
    goods_no     varchar(50)    not null,
    price        numeric(10, 2) not null default 0.00,
    count        int            not null default 1,
    created_by   varchar(50)    not null default 'postgres',
    created_time timestamp      not null default current_timestamp,
    updated_by   varchar(50)    not null default 'postgres',
    updated_time timestamp      not null default current_timestamp
);


-- 物流信息表
drop table if exists public.delivery;
create table if not exists public.delivery
(
    id            varchar(36)  not null default uuid_generate_v4(),
    delivery_no   varchar(50)  not null,
    order_no      varchar(50)  not null,
    destination   varchar(150) not null,
    addressee     varchar(30)  not null,
    receiver_name varchar(50)  not null,
    mobile_phone  varchar(11)  not null,
    status        varchar(10)  not null,
    created_by    varchar(50)  not null default 'postgres',
    created_time  timestamp    not null default current_timestamp,
    updated_by    varchar(50)  not null default 'postgres',
    updated_time  timestamp    not null default current_timestamp
);


-- 商品收藏
drop table if exists public.favorite;
create table if not exists public.favorite
(
    id       varchar(36) not null default uuid_generate_v4(),
    uid      varchar(20) not null,
    goods_no varchar(50) not null,
    created_by    varchar(50)  not null default 'postgres',
    created_time  timestamp    not null default current_timestamp,
    updated_by    varchar(50)  not null default 'postgres',
    updated_time  timestamp    not null default current_timestamp
);
