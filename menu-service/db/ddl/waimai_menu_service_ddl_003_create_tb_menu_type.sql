 -- 菜品信息表
drop table if exists tb_menu;
create table if not exists tb_menu
(
    id           int(11)       not null auto_increment comment '主键id',
    name         varchar(50)   not null comment '菜品名称',
    price        decimal(4, 2) not null comment '菜品单价',
    created_by   varchar(30)   not null comment '创建人',
    created_time timestamp     not null default current_timestamp comment '创建时间',
    updated_by   varchar(30)   not null comment '创建人',
    updated_time timestamp     not null default current_timestamp on update current_timestamp comment '创建时间',
    primary key (id)
) engine = InnoDB
  default charset = utf8 comment '菜品信息表';
