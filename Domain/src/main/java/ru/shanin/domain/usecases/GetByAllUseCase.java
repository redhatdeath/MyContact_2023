package ru.shanin.domain.usecases;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;

import ru.shanin.domain.entity.Person;
import ru.shanin.domain.repository.RepositoryWork;

public class GetByAllUseCase {
    private final RepositoryWork repository;

    public GetByAllUseCase(RepositoryWork repository) {
        this.repository = repository;
    }

    public LiveData<ArrayList<Person>> personGetByAll() {
        return repository.getAllPersons();
    }
}