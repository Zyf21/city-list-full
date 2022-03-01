CREATE TABLE IF NOT EXISTS users
(
    id       bigserial primary key NOT NULL,
    username varchar(255)          NOT NULL,
    password varchar(255)          NOT NULL,
    roles    varchar(255)          NOT NULL
);

INSERT INTO users(username, password, roles)
VALUES ('user', '$2a$10$P59BNlKQwJ446n.kTT0VF.4/R7O5.cdHQJZRieKcFsCbbyk/GimQa', 'ROLE_USER'),
       ('admin', '$2a$10$/YHh2fm2pEkRjFAVHL.DL.CWlni3yFiGXjIDaKx.q4w0gNsMBoO1q', 'ROLE_ADMIN');
