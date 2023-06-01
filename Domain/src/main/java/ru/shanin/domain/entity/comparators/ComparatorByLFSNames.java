package ru.shanin.domain.entity.comparators;

import java.util.Comparator;

import ru.shanin.domain.entity.Person;

public class ComparatorByLFSNames implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        String ln1 = person1.getPersonInfo().getLastName();
        String ln2 = person2.getPersonInfo().getLastName();
        String fn1 = person1.getPersonInfo().getFirstName();
        String fn2 = person2.getPersonInfo().getFirstName();
        String sn1 = person1.getPersonInfo().getSecondName();
        String sn2 = person2.getPersonInfo().getSecondName();
        int result;
        result = ln1.compareTo(ln2);
        if (result != 0) return result;
        result = fn1.compareTo(fn2);
        if (result != 0) return result;
        else return sn1.compareTo(sn2);
    }
}