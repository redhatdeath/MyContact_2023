package ru.shanin.domain.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import ru.shanin.domain.entity.Person;

public interface RepositoryWork {

    void addPerson(Person person);

    void editPerson(Person person);

    void removePerson(String _id);

    LiveData<ArrayList<Person>> getAllPersons();

    LiveData<Person> getOnePerson(String _id);

}
