create table state(
    id int not null unique primary key,
    name varchar not null unique,
    country_id int not null,
    foreign key (country_id) references country(id)
);