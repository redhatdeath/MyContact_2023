package ru.shanin.data.mapper;

import androidx.annotation.NonNull;

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
                person.getId(),
                person.getPersonInfo().getLastName(),
                person.getPersonInfo().getFirstName(),
                person.getPersonInfo().getSecondName(),
                person.getPersonInfo().getDate().getTime(),
                person.getPersonInfo().getPhone() ,
                person.getPersonInfo().getEmail(),
                person.getPersonInfo().getKnowledge()
        );
    }

    public static Person mapDbModelToEntity(
            PersonRoom personRoom
    ) {
        return new Person(
                personRoom.getPersonId(),
                new PersonInfo(
                        personRoom.getLastName(),
                        personRoom.getFirstName(),
                        personRoom.getSecondName(),
                        new Date(personRoom.getDate()),
                        personRoom.getEmail(),
                        personRoom.getPhone(),
                        personRoom.getListOfKnowledge()
                )
        );
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