create table author
(
    id          int       not null unique AUTO_INCREMENT PRIMARY KEY,
    name        varchar   not null,
    email       varchar   not null unique ,
    description varchar   not null,
    instance    timestamp not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;