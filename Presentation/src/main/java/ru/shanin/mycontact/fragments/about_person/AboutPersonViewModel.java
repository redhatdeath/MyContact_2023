package ru.shanin.mycontact.fragments.about_person;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import ru.shanin.domain.entity.Person;
import ru.shanin.domain.usecases.GetByIdUseCase;

public class AboutPersonViewModel extends ViewModel {
    private final GetByIdUseCase getById;

    public AboutPersonViewModel(GetByIdUseCase getById) {
        this.getById = getById;
    }

    protected LiveData<Person> getPerson(String peopleId) {
        return getById.personGetById(peopleId);
    }
}