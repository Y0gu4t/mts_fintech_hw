alter table animals.animals_habitats
    add constraint id_animal_type_pk foreign key (id_animal_type) references animals.animal_type(id_type),
    add constraint id_area foreign key (id_area) references animals.habitat(id_area);