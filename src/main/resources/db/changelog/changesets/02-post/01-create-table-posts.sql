-- liquibase formatted sql
-- changeset sushant.adhikari:posts-create-v1 context:dev
-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `posts` (
    id          INT                 PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(255)        NOT NULL,
    content     TEXT                NOT NULL,
    slug        VARCHAR(255)        UNIQUE NOT NULL,
    author_id   INT                 NOT NULL,
    created_at  TIMESTAMP           NOT NULL,
    updated_at  TIMESTAMP           NOT NULL,
    is_deleted  BOOLEAN             DEFAULT false,
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
);
