
CREATE TABLE IF NOT EXISTS user (
    id INTEGER UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'user id',
    username VARCHAR(64) NOT NULL UNIQUE COMMENT 'username',
    password VARCHAR(128) NOT NULL COMMENT 'password',
    avatar VARCHAR(256) NOT NULL COMMENT '',
    address VARCHAR(256) NOT NULL COMMENT '',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''
) ;