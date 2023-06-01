package ru.shanin.data.repositoryImpl;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import ru.shanin.data.db_room.dao.PersonDao;
import ru.shanin.data.db_room.entity.PersonRoom;
import ru.shanin.data.mapper.PersonMapper;
import ru.shanin.domain.entity.Person;
import ru.shanin.domain.repository.RepositoryWork;


public class RepositoryWorkImpl implements RepositoryWork {
    private final PersonDao personDao;
    private final MutableLiveData<ArrayList<Person>> personsListLiveData;
    private final MutableLiveData<Person> personLiveData;

    public RepositoryWorkImpl(
            PersonDao personDao
    ) {
        this.personDao = personDao;
        personsListLiveData = new MutableLiveData<>();
        personLiveData = new MutableLiveData<>();
        updatePersonListAsyncTask();
    }

    @Override
    public void addPerson(Person person) {
        AsyncTask.execute(() -> {
            PersonRoom personRoom = PersonMapper.mapEntityToDbModel(person);
            synchronized (personDao) {
                personDao._dbAddNew(personRoom);
            }
            updatePersonListAsyncTask();
        });
    }

    @Override
    public void editPerson(Person person) {
        Person person_new = new Person(person.getPersonInfo());
        removePerson(person.getId());
        addPerson(person_new);
    }

    @Override
    public void removePerson(String _id) {
        AsyncTask.execute(() -> {
            synchronized (personDao) {
                personDao._dbRemove(_id);
            }
            updatePersonListAsyncTask();
        });
    }

    @Override
    public LiveData<ArrayList<Person>> getAllPersons() {
        return personsListLiveData;
    }

    @Override
    public LiveData<Person> getOnePerson(String _id) {
        findPersonById(_id);
        return personLiveData;
    }

    private void findPersonById(String _id) {
        AsyncTask.execute(() -> {
            PersonRoom personRoom;
            synchronized (personDao) {
                personRoom = personDao._dbGetOne(_id);
            }
            Person person = PersonMapper.mapDbModelToEntity(personRoom);
            personLiveData.postValue(person);
        });
    }

    private void updatePersonListAsyncTask() {
        AsyncTask.execute(() -> {
            List<PersonRoom> list;
            synchronized (personDao) {
                list = personDao._dbGetAll();
            }
            ArrayList<Person> persons = PersonMapper.mapListDbModelToListEntity(list);
            personsListLiveData.postValue(new ArrayList<>(persons));
        });
    }
}