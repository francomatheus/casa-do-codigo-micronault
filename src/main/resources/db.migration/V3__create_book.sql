create table book(
     id int not null unique primary key,
     title varchar not null unique,
     resume varchar not null,
     summary varchar ,
     price decimal not null,
     number_of_page int not null ,
     isbn varchar not null unique ,
     publication_date date not null,
     category_id int not null,
     author_id int not null,
     foreign key (category_id) references category(id),
     foreign key (author_id) references author(id)
);