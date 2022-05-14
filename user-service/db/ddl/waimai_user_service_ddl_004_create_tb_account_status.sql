-- 创建用户信息表

drop table if exists tb_account_status;

create table if not exists tb_account_status
(
    id                  varchar(32) not null comment '主键id',
    account_status_code varchar(50) not null comment '账户状态code',
    account_status_name varchar(50) not null comment '账户状态名称',
    created_by          varchar(50) not null comment '创建人',
    created_time        timestamp   not null default current_timestamp comment '创建时间',
    updated_by          varchar(50) not null comment '更新人',
    updated_time        timestamp   not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (id)
) engine = InnoDB
  default charset = utf8 comment '账户状态常量表';



drop trigger if exists `trigger_generate_tb_account_status_id`;

create trigger `trigger_generate_tb_account_status_id`
    before insert
    on `tb_account_status`
    for each row
begin
    if new.id is null then
        set new.id = replace(uuid(), '-', '');
    end if;
end;
