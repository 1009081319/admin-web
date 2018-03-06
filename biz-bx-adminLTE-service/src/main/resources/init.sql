
drop table if exists sys_user;

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   int(11) not null auto_increment comment '主键',
   open_id              varchar(100) not null comment 'openId',
   member_id            int(11) not null comment '会员id',
   phone                varchar(20) not null comment '手机号',
   nick_name            varchar(100) not null comment '昵称',
   delete_flag          int(1) not null default 0 comment '删除标记',
   last_login_time      datetime not null comment '最后登录时间',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   update_time          datetime not null default CURRENT_TIMESTAMP comment '更新时间',
   remarks              varchar(100) comment '备注',
   primary key (id)
);

alter table sys_user comment 'sys_user[用户表]';

/*==============================================================*/
/* Index: member_id                                             */
/*==============================================================*/
create unique index member_id on sys_user
(

);


drop table if exists sys_role;

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   int(11) not null auto_increment comment '主键',
   cname                varchar(100) not null comment '中文名称',
   ename                varchar(100) not null comment '英文名称',
   delete_flag          int(1) not null default 0 comment '删除标记(0:正常;1:删除:)',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   update_time          datetime not null default CURRENT_TIMESTAMP comment '更新时间',
   primary key (id)
);

alter table sys_role comment 'sys_role[用户角色表]';


drop table if exists sys_permission;

/*==============================================================*/
/* Table: sys_permission                                        */
/*==============================================================*/
create table sys_permission
(
   id                   int(11) not null auto_increment comment '主键',
   parent_id            int(11) not null comment '父节点ID',
   display_type         int(1) not null default 1 comment '类型(1:菜单;2:操作)',
   cname                varchar(100) not null comment '中文名称',
   ename                varchar(100) not null comment '英文名称',
   url                  varchar(200) comment '链接',
   delete_flag          int(1) not null default 0 comment '删除标记',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   update_time          datetime not null default CURRENT_TIMESTAMP comment '更新时间',
   remarks              varchar(100) comment '备注',
   primary key (id)
);

alter table sys_permission comment 'sys_permission[系统权限表]';


drop table if exists sys_user_role;

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   id                   int(11) not null auto_increment comment 'id',
   user_id              int(11) not null comment '用户id',
   role_id              int(11) not null comment '角色id',
   primary key (id)
);

alter table sys_user_role comment 'sys_user_role[用户和角色关联表]';

alter table sys_user_role add constraint FK_USER_ROLE_ROLEID foreign key (role_id)
      references sys_role (id);

alter table sys_user_role add constraint FK_fk_user_role_userId foreign key (user_id)
      references sys_user (id);


drop table if exists sys_role_permission;

/*==============================================================*/
/* Table: sys_role_permission                                   */
/*==============================================================*/
create table sys_role_permission
(
   id                   int(11) not null auto_increment comment '主键',
   role_id              int(11) not null comment '角色id',
   permission_id        int(11) not null comment '权限id',
   primary key (id)
);

alter table sys_role_permission comment 'sys_role_permission[用户角色和权限关联表]';

alter table sys_role_permission add constraint FK_ROLE_PERMISSION_PERMISSIONID foreign key (permission_id)
      references sys_permission (id);

alter table sys_role_permission add constraint FK_ROLE_PERMISSION_ROLEID foreign key (role_id)
      references sys_role (id);

