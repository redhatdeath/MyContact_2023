package ru.shanin.data.db_room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = PersonRoom.NAME_TABLE,
        indices = {@Index(PersonRoom.COL_PERSON_ID)}
)
public class PersonRoom {
    public static final String NAME_TABLE = "Persons";
    public static final String COL_PERSON_ID = "Person_id";
    public static final String COL_LAST_NAME = "Last_name";
    public static final String COL_FIRST_NAME = "First_name";
    public static final String COL_SECOND_NAME = "Second_name";
    public static final String COL_DATE = "Date";
    public static final String COL_PHONE = "Phone";
    public static final String COL_EMAIL = "Email";
    public static final String COL_LIST_OF_KNOWLEDGE = "List_of_knowledge";

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COL_PERSON_ID)
    private final String personId;

    @ColumnInfo(name = COL_LAST_NAME)
    private final String lastName;
    @ColumnInfo(name = COL_FIRST_NAME)
    private final String firstName;

    @ColumnInfo(name = COL_SECOND_NAME)
    private final String secondName;

    @ColumnInfo(name = COL_DATE)
    private final long date;

    @ColumnInfo(name = COL_PHONE)
    private final String phone;

    @ColumnInfo(name = COL_EMAIL)
    private final String email;

    @ColumnInfo(name = COL_LIST_OF_KNOWLEDGE)
    private final String listOfKnowledge;

    public PersonRoom(
            @NonNull String personId,
            String lastName,
            String firstName,
            String secondName,
            long date,
            String phone,
            String email,
            String listOfKnowledge
    ) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.date = date;
        this.phone = phone;
        this.email = email;
        this.listOfKnowledge = listOfKnowledge;
        this.personId = personId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public long getDate() {
        return date;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getListOfKnowledge() {
        return listOfKnowledge;
    }
}