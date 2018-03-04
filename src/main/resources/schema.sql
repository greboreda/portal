/*
drop table if exists user_roles;
drop table if exists loginservice;
drop table if exists role_;
drop table if exists user_;
*/

create table user_ (
	id varchar(36) primary key,
	creationdate timestamp not null
);

create table role_ (
	id varchar(36) primary key,
	name varchar(128) not null,
	constraint unique_role_name unique (name)
);

create table user_roles (
	userid varchar(36) not null,
	roleid varchar(36) not null,
	constraint fk_user foreign key (userid) references user_(id),
	constraint fk_role foreign key (roleid) references role_(id),
	constraint unique_user_role unique (userid, roleid)
);

create table loginservice (
	id varchar(36) primary key,
	creationdate timestamp not null,
	modificationdate timestamp not null,
	userid varchar(128) not null,
	emailaddress varchar(256) not null,
	password varchar(128) not null,
	constraint fk_user foreign key (userid) references user_(id),
	constraint unique_user unique (userId),
	constraint unique_email_address unique (emailaddress)
);