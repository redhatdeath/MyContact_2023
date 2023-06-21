package ru.shanin.mycontact.fragments.persons;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import ru.shanin.data.generate.NewData;
import ru.shanin.domain.entity.Person;
import ru.shanin.domain.usecases.AddNewUseCases;
import ru.shanin.domain.usecases.DeleteByIdUseCase;
import ru.shanin.domain.usecases.GetByAllUseCase;

public class PersonsViewModel extends ViewModel {
    private final AddNewUseCases addNew;
    private final DeleteByIdUseCase delete;
    private final GetByAllUseCase getAll;

    public PersonsViewModel(
            AddNewUseCases addNew,
            DeleteByIdUseCase delete,
            GetByAllUseCase getAll
    ) {
        this.addNew = addNew;
        this.delete = delete;
        this.getAll = getAll;
    }

    protected LiveData<ArrayList<Person>> getAll() {
        return getAll.personGetByAll();
    }

    protected void addNew() {
        Person person = NewData.newPerson();
        addNew(person);
    }

    protected void addNew(Person person) {
        addNew.personAddNew(person);
    }

    protected void deletePeople(String id) {
        delete.personDeleteById(id);
    }

}