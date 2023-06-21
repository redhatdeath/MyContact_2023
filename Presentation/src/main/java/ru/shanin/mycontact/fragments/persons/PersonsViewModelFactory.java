package ru.shanin.mycontact.fragments.persons;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.shanin.domain.usecases.AddNewUseCases;
import ru.shanin.domain.usecases.DeleteByIdUseCase;
import ru.shanin.domain.usecases.GetByAllUseCase;

public class PersonsViewModelFactory implements ViewModelProvider.Factory {
    private final AddNewUseCases _addNew;
    private final DeleteByIdUseCase _delete;
    private final GetByAllUseCase _getAll;

    public PersonsViewModelFactory(
            AddNewUseCases addNew,
            DeleteByIdUseCase delete,
            GetByAllUseCase getAll
    ) {
        this._delete = delete;
        this._addNew = addNew;
        this._getAll = getAll;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PersonsViewModel(_addNew, _delete, _getAll);
    }
}