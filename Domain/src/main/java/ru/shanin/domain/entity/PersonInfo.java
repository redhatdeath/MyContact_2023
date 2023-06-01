package ru.shanin.domain.entity;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

public class PersonInfo {
    private final String lastName;
    private final String firstName;
    private final String secondName;
    private final Date date;
    private final String email;
    private final String phone;
    private final ArrayList<String> listOfKnowledge;

    public PersonInfo(
            String lastName,
            String firstName,
            String secondName,
            Date date,
            String email,
            String phone,
            ArrayList<String> listOfKnowledge
    ) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.date = date;
        this.email = email;
        this.phone = phone;
        this.listOfKnowledge = listOfKnowledge;
    }

    @NonNull
    @Override
    public String toString() {
        return lastName + ", " + firstName + " " + secondName;
    }

    public String getToSHA256() {
        return (new Gson()).toJson(this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || object.getClass() != getClass()) return false;
        PersonInfo peopleInfo = (PersonInfo) object;
        return
                this.firstName.equals(peopleInfo.getFirstName()) &&
                        this.secondName.equals(peopleInfo.getSecondName()) &&
                        this.email.equals(peopleInfo.getEmail()) &&
                        this.phone.equals(peopleInfo.getPhone()) &&
                        this.date == peopleInfo.getDate() && (
                        this.listOfKnowledge == peopleInfo.getListOfKnowledge() ||
                                this.listOfKnowledge.stream()
                                        .collect(groupingBy(k -> k, counting()))
                                        .equals(peopleInfo.getListOfKnowledge().stream()
                                                .collect(groupingBy(k -> k, counting()))));
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Date getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<String> getListOfKnowledge() {
        return listOfKnowledge;
    }

}