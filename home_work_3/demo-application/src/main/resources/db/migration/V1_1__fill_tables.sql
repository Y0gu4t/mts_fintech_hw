begin;
truncate table animals.animal_type restart identity cascade;
insert into animals.animal_type (is_wild, type)
values (true, 'Fox'),
       (true, 'Bear'),
       (false, 'Cat'),
       (false, 'Fish');

truncate table animals.breed restart identity cascade;
insert into animals.breed (breed)
values ('Rare'),
       ('Common'),
       ('Uncommon'),
       ('Extinct');

truncate table animals.animal restart identity cascade;
insert into animals.animal (birthday, character, cost, name, type_id, breed_id)
values ('01-01-2010'::date + random() * (now()::date - '01-01-2010'::date)::integer * interval '1 day', 'Wise', random() * 10, 'Nemo', 4, 2),
       ('01-01-2010'::date + random() * (now()::date - '01-01-2010'::date)::integer * interval '1 day', 'Sly', random() * 10, 'Baron', 1, 3),
       ('01-01-2010'::date + random() * (now()::date - '01-01-2010'::date)::integer * interval '1 day', 'Fearless', random() * 10, 'Barsik', 3, 4),
       ('01-01-2010'::date + random() * (now()::date - '01-01-2010'::date)::integer * interval '1 day', 'Playful', random() * 10, 'Druzhok', 2, 1),
       ('01-01-2010'::date + random() * (now()::date - '01-01-2010'::date)::integer * interval '1 day', 'Gentle', random() * 10, 'Vasya', 3, 2);

truncate table animals.provider restart identity cascade;
insert into animals.provider (name, phone)
values ('Petty Store', '952-954-24-54'),
       ('Zoo', '924-452-65-62'),
       ('Zavr', '976-256-11-36'),
       ('Best Pets', '945-591-05-81');

truncate table animals.habitat restart identity cascade;
insert into animals.habitat (area)
values ('Sea'),
       ('Forest'),
       ('Home'),
       ('Jungle');

truncate table animals.animals_habitats restart identity cascade;
insert into animals.animals_habitats (id_animal, id_habitat)
values (1, 2),
       (1, 3),
       (2, 2),
       (2, 4),
       (3, 2),
       (3, 3),
       (3, 4),
       (4, 1);

truncate table animals.animals_providers restart identity cascade;
insert into animals.animals_providers (id_animal, id_provider)
values (1, 2),
       (1, 4),
       (2, 1),
       (2, 2),
       (2, 4),
       (3, 1),
       (3, 3),
       (4, 3),
       (4, 4);
end;