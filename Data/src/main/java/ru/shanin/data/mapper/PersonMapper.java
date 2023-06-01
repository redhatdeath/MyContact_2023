package ru.shanin.data.mapper;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ru.shanin.data.db_room.entity.PersonRoom;
import ru.shanin.domain.entity.Person;
import ru.shanin.domain.entity.PersonInfo;

public class PersonMapper {
    public static PersonRoom mapEntityToDbModel(
            Person person
    ) {
        return new PersonRoom(
                person.getId() + "",
                person.getPersonInfo().getLastName() + "",
                person.getPersonInfo().getFirstName() + "",
                person.getPersonInfo().getSecondName() + "",
                person.getPersonInfo().getDate().getTime() + 0,
                person.getPersonInfo().getPhone() + "",
                person.getPersonInfo().getEmail() + "",
                (new Gson()).toJson(person.getPersonInfo().getListOfKnowledge()) + ""
        );
    }

    public static Person mapDbModelToEntity(
            PersonRoom personRoom
    ) {
        return new Person(
                personRoom.getPersonId() + "",
                new PersonInfo(
                        personRoom.getLastName() + "",
                        personRoom.getFirstName() + "",
                        personRoom.getSecondName() + "",
                        new Date(personRoom.getDate()),
                        personRoom.getEmail() + "",
                        personRoom.getPhone() + "",
                        (new Gson()).fromJson(
                                personRoom.getListOfKnowledge(),
                                new TypeToken<ArrayList<String>>() {
                                }.getType())));
    }

    public static ArrayList<Person> mapListDbModelToListEntity(
            @NonNull List<PersonRoom> list
    ) {
        return list
                .stream()
                .map(PersonMapper::mapDbModelToEntity)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}