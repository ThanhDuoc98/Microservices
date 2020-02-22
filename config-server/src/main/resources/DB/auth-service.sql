set search_path to microservices;
DROP TABLE IF EXISTS users CASCADE ;
CREATE TABLE users (
    user_id VARCHAR PRIMARY KEY ,
    username VARCHAR NOT NULL UNIQUE ,
    password VARCHAR NOT NULL ,
    activated BOOLEAN NOT NULL ,
    created_by VARCHAR ,
    created_date TIMESTAMP ,
    modified_by VARCHAR ,
    modified_date TIMESTAMP
);
CREATE INDEX idx_username ON users(username);
ALTER TABLE users ADD CONSTRAINT fk_createdby_user FOREIGN KEY (created_by) REFERENCES users(user_id) ON DELETE CASCADE ;
ALTER TABLE users ADD CONSTRAINT fk_modifiedby_user FOREIGN KEY (modified_by) REFERENCES users(user_id) ON DELETE CASCADE ;

DROP TABLE IF EXISTS role CASCADE ;
CREATE TABLE role (
    role_id VARCHAR PRIMARY KEY ,
    role VARCHAR NOT NULL UNIQUE ,
    description VARCHAR NOT NULL ,
    create_date TIMESTAMP ,
    modified_date TIMESTAMP
);

DROP TABLE IF EXISTS users_roles CASCADE ;
CREATE TABLE users_roles (
    id VARCHAR PRIMARY KEY ,
    users VARCHAR NOT NULL ,
    role VARCHAR NOT NULL
);

ALTER TABLE users_roles ADD CONSTRAINT fk_user_user FOREIGN KEY (users) REFERENCES users(user_id) ON DELETE CASCADE ;
ALTER TABLE users_roles ADD CONSTRAINT fk_role_role FOREIGN KEY (role) REFERENCES role(role_id) ON DELETE CASCADE ;