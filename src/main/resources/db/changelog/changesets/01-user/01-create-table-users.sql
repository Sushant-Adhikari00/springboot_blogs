-- liquibase formatted sql
-- changeset sushant.adhikari:users-create-v1 context:dev
-- preconditions onFail:CONTINUE onError:HALT

CREATE TABLE IF NOT EXISTS `users`
(
    id              INT                 PRIMARY KEY AUTO_INCREMENT,
    unique_id       VARCHAR(255)        UNIQUE NOT NULL,
    full_name       VARCHAR(100)        NOT NULL ,
    email           VARCHAR(150)        NOT NULL UNIQUE,
    password_hash   VARCHAR(255)        NOT NULL,
    created_at      TIMESTAMP           NOT NULL,
    is_deleted      BOOLEAN             DEFAULT false
)

-- changeset sushant.adhikari:users-create-v2 context:dev
-- preconditions onFail:CONTINUE onError:HALT

ALTER TABLE users
ADD COLUMN profile_picture VARCHAR(255) NOT NULL;


