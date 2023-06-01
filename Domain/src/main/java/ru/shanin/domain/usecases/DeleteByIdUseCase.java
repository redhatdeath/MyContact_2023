package ru.shanin.domain.usecases;

import ru.shanin.domain.repository.RepositoryWork;

public class DeleteByIdUseCase {
    private final RepositoryWork repository;

    public DeleteByIdUseCase(RepositoryWork repository) {
        this.repository = repository;
    }

    public void personDeleteById(String _id) {
        repository.removePerson(_id);
    }
}