package ru.shanin.domain.usecases;

import ru.shanin.domain.entity.Person;
import ru.shanin.domain.repository.RepositoryWork;

public class EditByIdUseCase {
    private RepositoryWork repository;

    public EditByIdUseCase(RepositoryWork repository) {
        this.repository = repository;
    }

    public void personEditById(Person person) {
        repository.editPerson(person);
    }
}