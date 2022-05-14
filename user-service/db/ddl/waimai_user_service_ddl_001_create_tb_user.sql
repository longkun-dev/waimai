-- 创建用户信息表

drop table if exists tb_user;

create table if not exists tb_user
(
    id                  varchar(32) not null comment '主键id',
    uid                 varchar(50) not null comment '用户id',
    username            varchar(50) not null comment '用户名',
    `password`          varchar(30) not null comment '密码',
    phone_number        varchar(20)          default '' comment '联系方式',
    role_code           varchar(35) not null comment '角色',
    account_status_code varchar(50) not null comment '账号状态',
    created_by          varchar(50) not null comment '创建人',
    created_time        timestamp   not null default current_timestamp comment '创建时间',
    updated_by          varchar(50) not null comment '更新人',
    updated_time        timestamp   not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) engine = InnoDB
  default charset = utf8 comment '用户信息表';



drop trigger if exists `trigger_generate_tb_user_id`;

create trigger `trigger_generate_tb_user_id`
    before insert
    on `tb_user`
    for each row
begin
    if new.id is null then
        set new.id = replace(uuid(), '-', '');
    end if;
end;
