package ru.shanin.mycontact.fragments.about_person;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.shanin.domain.usecases.GetByIdUseCase;

public class AboutPersonViewModelFactory implements ViewModelProvider.Factory {
    private final GetByIdUseCase _getById;

    public AboutPersonViewModelFactory(GetByIdUseCase getById) {
        this._getById = getById;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new AboutPersonViewModel(_getById);
    }
}

