alter table animals.animals_providers
    add constraint id_animal_type_pk foreign key (id_animal_type) references animals.animal_type(id_type),
    add constraint id_provider_pk foreign key (id_provider) references animals.provider(id_provider);