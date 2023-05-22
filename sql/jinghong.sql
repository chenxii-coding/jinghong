create schema if not exists public;

set time zone "Asia/Shanghai";

create extension if not exists "uuid-ossp";

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


-- 商品目录表
drop table if exists public.category;
create table if not exists public.category
(
    id              varchar(36)  not null default uuid_generate_v4(),
    category_no     varchar(50)  not null,
    category_name   varchar(100) not null,
    parent_category varchar(50),
    level           int,
    created_by      varchar(50)  not null default 'postgres',
    created_time    timestamp    not null default current_timestamp,
    updated_by      varchar(50)  not null default 'postgres',
    updated_time    timestamp    not null default current_timestamp
);


insert into category(category_no, category_name, parent_category, level)
values ('CATEGORY_0001', '文具', null, 1),
       ('CATEGORY_0002', '食品', null, 1),
       ('CATEGORY_0003', '男装', null, 1),
       ('CATEGORY_0004', '女装', null, 1),
       ('CATEGORY_0005', '电子产品', null, 1),
       ('CATEGORY_0006', '书籍', null, 1),
       ('CATEGORY_0007', '海陆空运载器', null, 1),
       ('CATEGORY_0008', '体育器材', null, 1),
       ('CATEGORY_0009', '钢笔', 'CATEGORY_0001', 2),
       ('CATEGORY_0010', '铅笔', 'CATEGORY_0001', 2),
       ('CATEGORY_0011', '签字笔', 'CATEGORY_0001', 2),
       ('CATEGORY_0012', '水果', 'CATEGORY_0002', 2),
       ('CATEGORY_0013', '蔬菜', 'CATEGORY_0002', 2),
       ('CATEGORY_0014', '饮料', 'CATEGORY_0002', 2),
       ('CATEGORY_0015', 'T恤', 'CATEGORY_0003', 2),
       ('CATEGORY_0016', '牛仔裤', 'CATEGORY_0003', 2),
       ('CATEGORY_0017', '背心', 'CATEGORY_0003', 2),
       ('CATEGORY_0018', '运动鞋', 'CATEGORY_0003', 2),
       ('CATEGORY_0019', '内衣', 'CATEGORY_0004', 2),
       ('CATEGORY_0020', '连衣裙', 'CATEGORY_0004', 2),
       ('CATEGORY_0021', '丝袜', 'CATEGORY_0004', 2),
       ('CATEGORY_0022', '手机', 'CATEGORY_0005', 2),
       ('CATEGORY_0023', '笔记本电脑', 'CATEGORY_0005', 2),
       ('CATEGORY_0024', '平板电脑', 'CATEGORY_0005', 2),
       ('CATEGORY_0025', '小说', 'CATEGORY_0006', 2),
       ('CATEGORY_0026', '科普', 'CATEGORY_0006', 2),
       ('CATEGORY_0027', '任务传记', 'CATEGORY_0006', 2),
       ('CATEGORY_0028', '飞机', 'CATEGORY_0007', 2),
       ('CATEGORY_0029', '轮船', 'CATEGORY_0007', 2),
       ('CATEGORY_0030', '汽车', 'CATEGORY_0007', 2),
       ('CATEGORY_0031', '自行车', 'CATEGORY_0007', 2),
       ('CATEGORY_0032', '飞碟', 'CATEGORY_0007', 2),
       ('CATEGORY_0033', '篮球', 'CATEGORY_0008', 2),
       ('CATEGORY_0034', '足球', 'CATEGORY_0008', 2),
       ('CATEGORY_0035', '羽毛球', 'CATEGORY_0008', 2);



select a.category_no,
       a.category_name,
       b.category_name as parent_category_name,
       a.level
from category a
         left join category b
                   on a.parent_category = b.category_no
order by a.category_no;


-- 商品表
drop table if exists public.goods;
create table if not exists public.goods
(
    id           varchar(36)    not null default uuid_generate_v4(),
    goods_no     varchar(50)    not null,
    goods_name   varchar(100)   not null,
    category_no  varchar(50)    not null,
    brand        varchar(50)    not null,
    price        numeric(10, 2) not null default 99999999.99,
    image        varchar(500),
    tags         varchar(150),
    is_on_sale   bool           not null default true,
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
    index            int          not null default 1,
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
    paid_by      varchar(10),
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
    id           varchar(36) not null default uuid_generate_v4(),
    uid          varchar(20) not null,
    goods_no     varchar(50) not null,
    created_by   varchar(50) not null default 'postgres',
    created_time timestamp   not null default current_timestamp,
    updated_by   varchar(50) not null default 'postgres',
    updated_time timestamp   not null default current_timestamp
);

create unique index uidx_favorite on favorite (uid, goods_no);


-- 编号生成
drop table if exists public.auto_no;
create table if not exists public.auto_no
(
    id           varchar(36) not null default uuid_generate_v4(),
    type         varchar(50) not null,
    no           int         not null default 0,
    created_by   varchar(50) not null default 'postgres',
    created_time timestamp   not null default current_timestamp,
    updated_by   varchar(50) not null default 'postgres',
    updated_time timestamp   not null default current_timestamp
);



select *
from category
where level = 2;


insert into goods(goods_no, goods_name, category_no, brand, image, tags, price)
values ('GOODS_0001', '钢笔1', 'CATEGORY_0009', '钢笔品牌1', 'gangbi1', '文具#钢笔', 12.20),
       ('GOODS_0002', '钢笔2', 'CATEGORY_0009', '钢笔品牌2', 'gangbi2', '文具#钢笔', 14.40),
       ('GOODS_0003', '钢笔3', 'CATEGORY_0009', '钢笔品牌3', 'gangbi3', '文具#钢笔', 16.60),
       ('GOODS_0004', '铅笔1', 'CATEGORY_0010', '铅笔品牌1', 'qianbi1', '文具#铅笔', 18.80),
       ('GOODS_0005', '铅笔2', 'CATEGORY_0010', '铅笔品牌2', 'qianbi2', '文具#铅笔', 20.90),
       ('GOODS_0006', '铅笔3', 'CATEGORY_0010', '铅笔品牌3', 'qianbi3', '文具#铅笔', 21.10),
       ('GOODS_0007', '签字笔1', 'CATEGORY_0011', '签字笔品牌1', 'qianzibi1', '文具#签字笔', 11.50),
       ('GOODS_0008', '签字笔2', 'CATEGORY_0011', '签字笔品牌1', 'qianzibi2', '文具#签字笔', 13.10),
       ('GOODS_0009', '签字笔3', 'CATEGORY_0011', '签字笔品牌1', 'qianzibi3', '文具#签字笔', 21.55),
       ('GOODS_0010', '水果1', 'CATEGORY_0012', '水果品牌1', 'shuiguo1', '食品#水果', 20.10),
       ('GOODS_0011', '水果2', 'CATEGORY_0012', '水果品牌2', 'shuiguo2', '食品#水果', 32.10),
       ('GOODS_0012', '水果3', 'CATEGORY_0012', '水果品牌3', 'shuiguo3', '食品#水果', 8.65),
       ('GOODS_0013', '蔬菜1', 'CATEGORY_0013', '蔬菜品牌1', 'shucai1', '食品#蔬菜', 4.10),
       ('GOODS_0014', '蔬菜2', 'CATEGORY_0013', '蔬菜品牌2', 'shucai2', '食品#蔬菜', 8.02),
       ('GOODS_0015', '蔬菜3', 'CATEGORY_0013', '蔬菜品牌3', 'shucai3', '食品#蔬菜', 3.50),
       ('GOODS_0016', '饮料1', 'CATEGORY_0014', '饮料品牌1', 'yinliao1', '食品#饮料', 3.15),
       ('GOODS_0017', '饮料2', 'CATEGORY_0014', '饮料品牌2', 'yinliao2', '食品#饮料', 4.10),
       ('GOODS_0018', '饮料3', 'CATEGORY_0014', '饮料品牌3', 'yinliao3', '食品#饮料', 5.65),
       ('GOODS_0019', 'T恤1', 'CATEGORY_0015', 'T恤品牌1', 'txu1', '男装#T恤', 110.50),
       ('GOODS_0020', 'T恤2', 'CATEGORY_0015', 'T恤品牌2', 'txu2', '男装#T恤', 150.10),
       ('GOODS_0021', 'T恤3', 'CATEGORY_0015', 'T恤品牌3', 'txu3', '男装#T恤', 199.99),
       ('GOODS_0022', '牛仔裤1', 'CATEGORY_0016', '牛仔裤品牌1', 'niuzaiku1', '男装#牛仔裤', 45.90),
       ('GOODS_0023', '牛仔裤2', 'CATEGORY_0016', '牛仔裤品牌2', 'niuzaiku2', '男装#牛仔裤', 39.00),
       ('GOODS_0024', '牛仔裤3', 'CATEGORY_0016', '牛仔裤品牌3', 'niuzaiku3', '男装#牛仔裤', 45.50),
       ('GOODS_0025', '背心1', 'CATEGORY_0017', '背心品牌1', 'beixin1', '男装#背心', 30.00),
       ('GOODS_0026', '背心2', 'CATEGORY_0017', '背心品牌2', 'beixin2', '男装#背心', 34.00),
       ('GOODS_0027', '背心3', 'CATEGORY_0017', '背心品牌3', 'beixin3', '男装#背心', 21.00),
       ('GOODS_0028', '运动鞋1', 'CATEGORY_0018', '运动鞋品牌1', 'yundongxie1', '男装#运动鞋', 15.00),
       ('GOODS_0029', '运动鞋2', 'CATEGORY_0018', '运动鞋品牌2', 'yundongxie2', '男装#运动鞋', 99.90),
       ('GOODS_0030', '运动鞋3', 'CATEGORY_0018', '运动鞋品牌3', 'yundongxie3', '男装#运动鞋', 98.00),
       ('GOODS_0031', '内衣1', 'CATEGORY_0019', '内衣品牌1', 'neiyi1', '女装#内衣', 21.00),
       ('GOODS_0032', '内衣2', 'CATEGORY_0019', '内衣品牌2', 'neiyi2', '女装#内衣', 25.00),
       ('GOODS_0033', '内衣3', 'CATEGORY_0019', '内衣品牌3', 'neiyi3', '女装#内衣', 50.00),
       ('GOODS_0034', '连衣裙1', 'CATEGORY_0020', '连衣裙品牌1', 'lianyiqun1', '女装#连衣裙', 100.00),
       ('GOODS_0035', '连衣裙2', 'CATEGORY_0020', '连衣裙品牌2', 'lianyiqun2', '女装#连衣裙', 105.00),
       ('GOODS_0036', '连衣裙3', 'CATEGORY_0020', '连衣裙品牌3', 'lianyiqun3', '女装#连衣裙', 110.00),
       ('GOODS_0037', '丝袜1', 'CATEGORY_0021', '丝袜品牌1', 'siwa1', '女装#丝袜', 50.00),
       ('GOODS_0038', '丝袜2', 'CATEGORY_0021', '丝袜品牌2', 'siwa2', '女装#丝袜', 55.00),
       ('GOODS_0039', '丝袜3', 'CATEGORY_0021', '丝袜品牌3', 'siwa3', '女装#丝袜', 60.00),
       ('GOODS_0040', '手机1', 'CATEGORY_0022', '手机品牌1', 'shouji1', '电子产品#手机', 65.00),
       ('GOODS_0041', '手机2', 'CATEGORY_0022', '手机品牌2', 'shouji2', '电子产品#手机', 56.50),
       ('GOODS_0042', '手机3', 'CATEGORY_0022', '手机品牌3', 'shouji3', '电子产品#手机', 2199.00),
       ('GOODS_0043', '笔记本电脑1', 'CATEGORY_0023', '笔记本电脑品牌1', 'bijiben1', '电子产品#笔记本电脑', 5199.00),
       ('GOODS_0044', '笔记本电脑2', 'CATEGORY_0023', '笔记本电脑品牌2', 'bijiben2', '电子产品#笔记本电脑', 4000.00),
       ('GOODS_0045', '笔记本电脑3', 'CATEGORY_0023', '笔记本电脑品牌3', 'bijiben3', '电子产品#笔记本电脑', 3500.00),
       ('GOODS_0046', '平板电脑1', 'CATEGORY_0024', '平板电脑品牌1', 'pingban1', '电子产品#平板电脑', 2800.00),
       ('GOODS_0047', '平板电脑2', 'CATEGORY_0024', '平板电脑品牌2', 'pingban2', '电子产品#平板电脑', 2900.00),
       ('GOODS_0048', '平板电脑3', 'CATEGORY_0024', '平板电脑品牌3', 'pingban3', '电子产品#平板电脑', 2000.00),
       ('GOODS_0049', '小说1', 'CATEGORY_0025', '小说品牌1', 'xiaoshuo1', '书籍#小说', 7.90),
       ('GOODS_0050', '小说2', 'CATEGORY_0025', '小说品牌2', 'xiaoshuo2', '书籍#小说', 8.10),
       ('GOODS_0051', '小说3', 'CATEGORY_0025', '小说品牌3', 'xiaoshuo3', '书籍#小说', 9.50),
       ('GOODS_0052', '科普1', 'CATEGORY_0026', '科普品牌1', 'kepu1', '书籍#科普', 20.00),
       ('GOODS_0053', '科普2', 'CATEGORY_0026', '科普品牌2', 'kepu2', '书籍#科普', 22.00),
       ('GOODS_0054', '科普3', 'CATEGORY_0026', '科普品牌3', 'kepu3', '书籍#科普', 23.00),
       ('GOODS_0055', '人物传记1', 'CATEGORY_0027', '人物传记品牌1', 'renwuzhuanji1', '书籍#人物传记', 19.00),
       ('GOODS_0056', '人物传记2', 'CATEGORY_0027', '人物传记品牌2', 'renwuzhuanji2', '书籍#人物传记', 18.00),
       ('GOODS_0057', '人物传记3', 'CATEGORY_0027', '人物传记品牌3', 'renwuzhuanji3', '书籍#人物传记', 16.50),
       ('GOODS_0058', '飞机1', 'CATEGORY_0028', '飞机品牌1', 'feiji1', '海陆空运载器#飞机', 1500.00),
       ('GOODS_0059', '飞机2', 'CATEGORY_0028', '飞机品牌2', 'feiji2', '海陆空运载器#飞机', 4300.00),
       ('GOODS_0060', '飞机3', 'CATEGORY_0028', '飞机品牌3', 'feiji3', '海陆空运载器#飞机', 3100.00),
       ('GOODS_0061', '轮船1', 'CATEGORY_0029', '轮船品牌1', 'lunchuan1', '海陆空运载器#轮船', 750.00),
       ('GOODS_0062', '轮船2', 'CATEGORY_0029', '轮船品牌2', 'lunchuan2', '海陆空运载器#轮船', 990.00),
       ('GOODS_0063', '轮船3', 'CATEGORY_0029', '轮船品牌3', 'lunchuan3', '海陆空运载器#轮船', 1000.00),
       ('GOODS_0064', '汽车1', 'CATEGORY_0030', '汽车品牌1', 'qiche1', '海陆空运载器#汽车', 3400.00),
       ('GOODS_0065', '汽车2', 'CATEGORY_0030', '汽车品牌2', 'qiche2', '海陆空运载器#汽车', 2300.00),
       ('GOODS_0066', '汽车3', 'CATEGORY_0030', '汽车品牌3', 'qiche3', '海陆空运载器#汽车', 2100.00),
       ('GOODS_0067', '自行车1', 'CATEGORY_0031', '自行车品牌1', 'zixingche1', '海陆空运载器#自行车', 6500.00),
       ('GOODS_0068', '自行车2', 'CATEGORY_0031', '自行车品牌2', 'zixingche2', '海陆空运载器#自行车', 8100.00),
       ('GOODS_0069', '自行车3', 'CATEGORY_0031', '自行车品牌3', 'zixingche3', '海陆空运载器#自行车', 5100.00),
       ('GOODS_0070', '飞碟1', 'CATEGORY_0032', '飞碟品牌1', 'feidie1', '海陆空运载器#飞碟', 9000.00),
       ('GOODS_0071', '飞碟2', 'CATEGORY_0032', '飞碟品牌2', 'feidie2', '海陆空运载器#飞碟', 7000.00),
       ('GOODS_0072', '飞碟3', 'CATEGORY_0032', '飞碟品牌3', 'feidie3', '海陆空运载器#飞碟', 6000.00),
       ('GOODS_0073', '篮球1', 'CATEGORY_0033', '篮球品牌1', 'lanqiu1', '体育器材#篮球', 48.80),
       ('GOODS_0074', '篮球2', 'CATEGORY_0033', '篮球品牌2', 'lanqiu2', '体育器材#篮球', 210.00),
       ('GOODS_0075', '篮球3', 'CATEGORY_0033', '篮球品牌3', 'lanqiu3', '体育器材#篮球', 45.00),
       ('GOODS_0076', '足球1', 'CATEGORY_0034', '足球品牌1', 'zuqiu1', '体育器材#足球', 299.95),
       ('GOODS_0077', '足球2', 'CATEGORY_0034', '足球品牌2', 'zuqiu2', '体育器材#足球', 340.00),
       ('GOODS_0078', '足球3', 'CATEGORY_0034', '足球品牌3', 'zuqiu3', '体育器材#足球', 245.00),
       ('GOODS_0079', '羽毛球1', 'CATEGORY_0035', '羽毛球品牌3', 'yumaoqiu1', '体育器材#羽毛球', 6.20),
       ('GOODS_0080', '羽毛球2', 'CATEGORY_0035', '羽毛球品牌3', 'yumaoqiu2', '体育器材#羽毛球', 5.10),
       ('GOODS_0081', '羽毛球3', 'CATEGORY_0035', '羽毛球品牌3', 'yumaoqiu3', '体育器材#羽毛球', 5.90);


-- 购物车
drop table if exists cart;
create table if not exists cart
(
    id           varchar(36) not null default uuid_generate_v4(),
    uid          varchar(20) not null,
    goods_no     varchar(50) not null,
    count        int         not null default 1,
    created_by   varchar(50) not null default 'postgres',
    created_time timestamp   not null default current_timestamp,
    updated_by   varchar(50) not null default 'postgres',
    updated_time timestamp   not null default current_timestamp
);

create unique index if not exists uidx_cart on cart (uid, goods_no);


-- 评分表
drop table if exists rate;
create table if not exists rate
(
    id           varchar(36) not null default uuid_generate_v4(),
    uid          varchar(20) not null,
    goods_no     varchar(50) not null,
    rate         int         not null default 0,
    created_by   varchar(50) not null default 'postgres',
    created_time timestamp   not null default current_timestamp,
    updated_by   varchar(50) not null default 'postgres',
    updated_time timestamp   not null default current_timestamp
);

create unique index if not exists uidx_rate on rate (uid, goods_no);



select *
from rate;
