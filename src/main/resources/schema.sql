DROP TABLE IF EXISTS USER_ROLES;
DROP TABLE IF EXISTS LOGINSERVICE;
DROP TABLE IF EXISTS ROLE_;
DROP TABLE IF EXISTS USER_;

CREATE TABLE USER_ (
	ID VARCHAR(36) PRIMARY KEY,
	CREATIONDATE TIMESTAMP NOT NULL
);

CREATE TABLE ROLE_ (
	ID VARCHAR(36) PRIMARY KEY,
	NAME VARCHAR(128) NOT NULL,
	CONSTRAINT UNIQUE_ROLE_NAME UNIQUE (NAME)
);

CREATE TABLE USER_ROLES (
	USERID VARCHAR(36) NOT NULL,
	ROLEID VARCHAR(36) NOT NULL,
	CONSTRAINT FK_USER FOREIGN KEY (USERID) REFERENCES USER_(ID),
	CONSTRAINT FK_ROLE FOREIGN KEY (ROLEID) REFERENCES ROLE_(ID),
	CONSTRAINT UNIQUE_USER_ROLE UNIQUE (USERID, ROLEID)
);

CREATE TABLE LOGINSERVICE (
	ID VARCHAR(36) PRIMARY KEY,
	CREATIONDATE TIMESTAMP NOT NULL,
	MODIFICATIONDATE TIMESTAMP NOT NULL,
	USERID VARCHAR(128) NOT NULL,
	EMAILADDRESS VARCHAR(256) NOT NULL,
	PASSWORD VARCHAR(128) NOT NULL,
	CONSTRAINT FK_USER FOREIGN KEY (USERID) REFERENCES USER_(ID),
	CONSTRAINT UNIQUE_EMAIL_ADDRESS UNIQUE (EMAILADDRESS)
);