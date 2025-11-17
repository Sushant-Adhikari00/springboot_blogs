-- liquibase formatted sql
-- changeset sushant.adhikari:comments-create-v1 context:dev
-- preconditions onFail:CONTINUE onError:HALT

CREATE TABLE IF NOT EXISTS `comments` (
    id              INT         PRIMARY KEY AUTO_INCREMENT,
    content         TEXT        NOT NULL,
    post_id         INT         NOT NULL,
    author_id       INT         NOT NULL,
    created_at      TIMESTAMP   NOT NULL,
    FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
    );
