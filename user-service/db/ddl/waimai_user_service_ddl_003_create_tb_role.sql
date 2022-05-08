-- 创建用户信息表
drop table if exists tb_role;

create table if not exists tb_role
(
    id           int(11)     not null auto_increment comment '主键id',
    role_code    varchar(35) not null comment '角色编码',
    role_name    varchar(50) not null comment '角色名称',
    created_by   varchar(50) not null comment '创建人',
    created_time timestamp   not null default current_timestamp comment '创建时间',
    updated_by   varchar(50) not null comment '更新人',
    updated_time timestamp   not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id),
    unique key (role_code)
) engine = InnoDB
  default charset = utf8 comment '角色信息表';

alter table tb_role
    auto_increment = 1000;
