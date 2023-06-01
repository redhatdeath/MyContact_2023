package ru.shanin.domain.usecases;

import androidx.lifecycle.LiveData;

import ru.shanin.domain.entity.Person;
import ru.shanin.domain.repository.RepositoryWork;

public class GetByIdUseCase {
    private final RepositoryWork repository;

    public GetByIdUseCase(RepositoryWork repository) {
        this.repository = repository;
    }

    public LiveData<Person> personGetById(String _id) {
        return repository.getOnePerson(_id);
    }
}