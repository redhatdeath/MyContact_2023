package ru.shanin.domain.usecases;

import ru.shanin.domain.entity.Person;
import ru.shanin.domain.repository.RepositoryWork;

public class AddNewUseCases {
    private final RepositoryWork repository;

    public AddNewUseCases(RepositoryWork repository) {
        this.repository = repository;
    }

    public void personAddNew(Person person) {
        repository.addPerson(person);
    }
}