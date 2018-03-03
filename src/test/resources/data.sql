delete from user_roles;
delete from loginservice;
delete from role_;
delete from user_;

insert into user_ (id, creationdate) values ('6e3640c1-b13c-4858-85a6-0c51ce7551f8', current_timestamp);

insert into role_ (id, name) values ('d2c21143-9ef3-4640-9360-58ab414565cc', 'admin');
insert into role_ (id, name) values ('1feab8b4-634b-4794-8390-9a16b8a446ae', 'user');

insert into user_roles (userid, roleid) values ('6e3640c1-b13c-4858-85a6-0c51ce7551f8', 'd2c21143-9ef3-4640-9360-58ab414565cc');
insert into user_roles (userid, roleid) values ('6e3640c1-b13c-4858-85a6-0c51ce7551f8', '1feab8b4-634b-4794-8390-9a16b8a446ae');

insert into loginservice (id, creationdate, modificationdate, userid, emailaddress, password) values (
	'6e0f8698-42bd-4bde-a17c-01b7ac55f862',
	current_timestamp,
	current_timestamp,
	'6e3640c1-b13c-4858-85a6-0c51ce7551f8',
	'admin@portal.com',
	'$2a$10$XoF2fWA6QrtT23uKzS1XMuEghv0vOnBgxDGsa7Jmi5TG6wOk3.uIW' -- pass1
);
