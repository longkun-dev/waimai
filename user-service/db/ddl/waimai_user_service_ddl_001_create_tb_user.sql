-- 创建用户信息表
drop table if exists tb_user;

create table if not exists tb_user
(
    id           int(11)     not null auto_increment comment '主键id',
    username     varchar(50) not null comment '用户名',
    password     varchar(30) not null comment '密码',
    role_code    varchar(35) not null comment '角色',
    created_by   varchar(50) not null comment '创建人',
    created_time timestamp   not null default current_timestamp comment '创建时间',
    updated_by   varchar(50) not null comment '更新人',
    updated_time timestamp   not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id),
    unique key (username)
) engine = InnoDB
  default charset = utf8 comment '用户信息表';
