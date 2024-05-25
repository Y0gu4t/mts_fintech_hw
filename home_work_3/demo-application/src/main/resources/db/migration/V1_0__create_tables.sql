create schema if not exists animals;

create table if not exists animals.animal_type
(
    id_type int generated by default as identity primary key,
    type    varchar(50),
    is_wild bool
);

create table if not exists animals.breed
(
    id_breed int generated by default as identity primary key,
    breed    varchar(50)
);

create table if not exists animals.animal
(
    id_animal bigserial primary key,
    name      text,
    character varchar(50),
    cost      numeric(7, 2),
    birthday  date,
    type_id   int references animals.animal_type (id_type) on delete set null,
    breed_id  int references animals.breed (id_breed) on delete set null
);

create table if not exists animals.habitat
(
    id_habitat int generated by default as identity primary key,
    area    varchar(50)
);

create table if not exists animals.provider
(
    id_provider int generated by default as identity primary key,
    name        text,
    phone       varchar(50)
);

create table if not exists animals.animals_providers
(
    id_animal   bigserial references animals.animal (id_animal) on delete set null on update cascade,
    id_provider int references animals.provider (id_provider) on delete cascade on update cascade,
    primary key (id_animal, id_provider)
);

create table if not exists animals.animals_habitats
(
    id_animal bigserial references animals.animal (id_animal) on delete set null on update cascade,
    id_habitat   int references animals.habitat (id_habitat) on delete cascade on update cascade,
    primary key (id_animal, id_habitat)
);